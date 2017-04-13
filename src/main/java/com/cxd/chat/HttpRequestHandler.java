package com.cxd.chat;

import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.stream.ChunkedNioFile;

import java.io.File;
import java.io.RandomAccessFile;

/**
 * Created by childe on 16/5/16.
 */
public class HttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private final String wsUri;
    private static final File INDEX;

    static {
//        URL location = System.getProperty("user.dir");
//        try {
            String path = System.getProperty("html_locate") +"/chatClient.html";
            path = !path.contains("file:") ? path : path.substring(5);
            INDEX = new File(path);
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//            throw new IllegalStateException("Unable to locate WebsocketChatClient.html", e);
//        }
    }

    public HttpRequestHandler(String wsUri) {
        this.wsUri = wsUri;
    }

    @Override
    protected void channelRead0(final ChannelHandlerContext ctx, final FullHttpRequest request) throws Exception {
        if (wsUri.equalsIgnoreCase(request.getUri())) {
            ctx.fireChannelRead(request.retain());                 //2
        } else {
            WebSocketServerHandshakerFactory webSocketServerHandshakerFactory = new WebSocketServerHandshakerFactory("http://localhost:8080/ws",null,false);
            WebSocketServerHandshaker handshaker = webSocketServerHandshakerFactory.newHandshaker(request);
            if (handshaker == null) {
                webSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
            } else {
                handleHttpRequest(ctx,request);
            }
        }

    }

    private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        RandomAccessFile file = new RandomAccessFile(INDEX, "r");//4

        HttpResponse response = new DefaultHttpResponse(request.getProtocolVersion(), HttpResponseStatus.OK);
        response.headers().set(HttpHeaders.Names.CONTENT_TYPE, "text/html; charset=UTF-8");

        boolean keepAlive = HttpHeaders.isKeepAlive(request);

        if (keepAlive) {                                        //5
            response.headers().set(HttpHeaders.Names.CONTENT_LENGTH, file.length());
            response.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
        }
        ctx.write(response);                    //6

        if (ctx.pipeline().get(SslHandler.class) == null) {     //7
            ctx.write(new DefaultFileRegion(file.getChannel(), 0, file.length()));
        } else {
            ctx.write(new ChunkedNioFile(file.getChannel()));
        }
        ChannelFuture future = ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);           //8
        if (!keepAlive) {
            future.addListener(ChannelFutureListener.CLOSE);        //9
        }

        file.close();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        Channel incoming = ctx.channel();
        System.out.println("Client:"+incoming.remoteAddress()+"异常");
        // 当出现异常就关闭连接
        System.out.println(cause.getMessage());
        ctx.close();
    }
}
