package com.cxd.chat;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import net.sf.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by childe on 16/5/16.
 */
public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    ConcurrentHashMap<String,String> id2name = new ConcurrentHashMap<>();
    String[] name = {"上海青","脑残青","嵩县青","乌栾青","洛阳青","四季青","水叨叨","火叨叨","金叨叨","木叨叨","土叨叨"};

    static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        Channel income = ctx.channel();
        String id = income.remoteAddress().toString();
        id = id.replace(".","").replace("/","").replace(":","");
        for (Channel channel:channelGroup) {
            String v = id2name.get(id);
            String[] vStr = v.split("-");
            if (channel != income) {
                JSONObject msgMap = new JSONObject();
                msgMap.put("isMyself","n");
                msgMap.put("msg",msg.text());
                msgMap.put("date",new SimpleDateFormat("HH:mm:ss").format(new Date()));
                msgMap.put("avatar",vStr[1]);
                msgMap.put("name",vStr[0]);
                channel.writeAndFlush(new TextWebSocketFrame(msgMap.toString()));
            } else {
                String info = msg.text().trim();
                if (info.startsWith("{") && info.endsWith("}")) {
                    String[] splitStr = info.split("&");
                    String tmp = splitStr[0].substring(1);
                    String name = splitStr[1].substring(0,splitStr.length - 1);
                    if (id2name.containsKey(tmp)) {
                        id2name.put(tmp,name);
                    }
                }
                JSONObject msgMap = new JSONObject();
                msgMap.put("isMyself","y");
                msgMap.put("msg",info);
                msgMap.put("avatar",vStr[1]);
                msgMap.put("name",vStr[0]);
                msgMap.put("date",new SimpleDateFormat("HH:mm:ss").format(new Date()));
                channel.writeAndFlush(new TextWebSocketFrame(msgMap.toString()));
            }
        }
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        try {
            Channel income = ctx.channel();

            String id = income.remoteAddress().toString().replace(".","").replace("/","").replace(":","");
            int index = (int) (Math.random() * 11);
            String avater = "http://zhouqing.win/icon/" + index + ".jpg";
            id2name.put(id,name[index] + "-" +  avater);//http://zhouqing.win/icon/1.jpg

            channelGroup.add(income);

            JSONObject msgMap = new JSONObject();
            msgMap.put("msg",name[index] + "-进入脑残的魔法世界");
            msgMap.put("avatar",index);
            msgMap.put("name",name[index]);

            channelGroup.writeAndFlush(new TextWebSocketFrame(msgMap.toString()));


            System.out.println("Client:"+income.remoteAddress() +"add");
        } catch (Exception e) {
            System.out.print(e);
        }

    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        try {
            Channel income = ctx.channel();
            channelGroup.remove(income);
            String id = income.remoteAddress().toString();
            String v = id2name.get(id);
            String[] vStr = v.split("-");
            JSONObject msgMap = new JSONObject();
            msgMap.put("msg", "[SERVER] - " + vStr[0] + " 离开");
            msgMap.put("avatar", vStr[1]);
            for (Channel channel : channelGroup) {
                channel.writeAndFlush(new TextWebSocketFrame(msgMap.toString()));
            }


            System.out.println("Client:" + income.remoteAddress() + "leave");
        } catch (Exception e) {
                System.out.print(e);
            }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        incoming.writeAndFlush(Unpooled.copiedBuffer("1111".getBytes()));
        System.out.println("Client:"+incoming.remoteAddress()+"online");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        channelGroup.remove(incoming);
        String id = incoming.remoteAddress().toString();
        id = id.replace(".","").replace("/","").replace(":","");
        String v = id2name.get(id);
        String[] vStr = v.split("-");
        JSONObject msgMap = new JSONObject();
        msgMap.put("msg","[SERVER] - " + vStr[0] + " 掉线");
        msgMap.put("avatar",vStr[1]);
        msgMap.put("name",vStr[0]);
        for (Channel channel:channelGroup) {
            channel.writeAndFlush(new TextWebSocketFrame(msgMap.toString()));
        }
        System.out.println("Client:"+incoming.remoteAddress()+"offline");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Channel incoming = ctx.channel();
        System.out.println("Client:"+incoming.remoteAddress()+"exception");
        // 当出现异常就关闭连接
        System.out.println(cause.getMessage());
        ctx.close();
    }
}
