package com.cxd.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by childe on 16/5/13.
 */
public class TimeClient {
    public static void main(String[] args) {
        int port = 8080;
        String host = "localhost";

        EventLoopGroup worker = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(worker)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE,true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new MessageDecoder(),new TimeClientHandler());
                    }
                });
        try {
            ChannelFuture f = b.connect(host,port).sync();

            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            worker.shutdownGracefully();
        }
    }
}
