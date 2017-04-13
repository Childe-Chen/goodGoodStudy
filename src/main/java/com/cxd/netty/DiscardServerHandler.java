package com.cxd.netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by childe on 16/5/13.
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        /*ByteBuf message = (ByteBuf) msg;
        if (message.isReadable()) {
            System.out.println(message.toString(Charset.forName("UTF-8")));
            String res = message.toString(Charset.forName("UTF-8"));
            ctx.writeAndFlush(Unpooled.copiedBuffer(res.getBytes()));
        }*/
        System.out.println(msg);
        ctx.writeAndFlush(Unpooled.copiedBuffer(msg.toString().getBytes()));

    }

    @Override
    public void channelActive(final ChannelHandlerContext ctx) throws Exception {
//        final ByteBuf time = ctx.alloc().buffer(4);
//        time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));

        final ChannelFuture f = ctx.writeAndFlush(Unpooled.copiedBuffer("active".getBytes()));
        f.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                assert f == future;
//                ctx.close();
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
