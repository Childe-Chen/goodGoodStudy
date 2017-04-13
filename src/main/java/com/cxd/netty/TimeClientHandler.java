package com.cxd.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

/**
 * Created by childe on 16/5/13.
 */
public class TimeClientHandler extends ChannelInboundHandlerAdapter {
    int i = 0;
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (; i < 10; i++) {
            String req = "Iam client [" + i + "]";
            final ChannelFuture f = ctx.writeAndFlush(Unpooled.copiedBuffer(req.getBytes()));

        }

//        ctx.channel().
    }

    @Override
    public void channelRead(final ChannelHandlerContext ctx, Object msg) throws Exception {



        ByteBuf message = (ByteBuf) msg;
        if (message.isReadable()) {
            System.out.println(message.toString(Charset.forName("UTF-8")));



        }


    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
