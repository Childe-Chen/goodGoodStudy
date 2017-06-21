package com.cxd.Sell.event;

import com.cxd.Sell.module.Module;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by childe on 2017/6/21.
 */
public class EventCenter implements Module{

    static Map<EventEnum,List<EventHandler>> handlerMap = new HashMap<>();

    static Queue<Event> eventQueue = new LinkedBlockingDeque<>();

    public static volatile boolean isStop = false;


    //事件处理
    static Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!isStop) {
                    try {

                        Event event = eventQueue.poll();

                        List<EventHandler> handlerList = handlerMap.get(event.getEventType());
                        for (int i = 0; i < handlerList.size(); i++) {
                            handlerList.get(i).handle(event);
                        }
                    }catch (Exception e) {

                    }
                }
            }
        });


    static void publish() {

    }

    static void register(){

    }

    static void distribution(){

    }

    @Override
    public void on() {
        consumer.start();
    }

    @Override
    public void off() {
        isStop = true;
    }
}
