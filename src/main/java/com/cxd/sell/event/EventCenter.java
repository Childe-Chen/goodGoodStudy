package com.cxd.sell.event;

import com.cxd.sell.module.Module;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by childe on 2017/6/21.
 */
public class EventCenter implements Module{

    static final Map<EventEnum,List<EventHandler>> handlerMap = new HashMap<>();

    static final Queue<Event> eventQueue = new LinkedBlockingDeque<>();

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
                        for (int i = 0; i < handlerList.size(); i++) {
                            handlerList.get(i).commit();
                        }
                    }catch (Exception e) {

                    }
                }
            }
        });


    public static void publish(Event e) {
        eventQueue.offer(e);
    }

    public static void register(EventHandler handler){
        //启动注册为顺序注册，不存在并发
        EventEnum[] events = handler.getInterestEvent();
        for (int i = 0; i < events.length; i++) {
            EventEnum event = events[i];
            List<EventHandler> list = handlerMap.get(event);
            if (list == null) {
                list = new ArrayList<>();
                handlerMap.put(event,list);
            }
            list.add(handler);

        }
    }

    public static void distribution(){

    }

    @Override
    public void on() {
        consumer.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {

            }
        });
        consumer.start();
    }

    @Override
    public void off() {
        isStop = true;
    }


    static public String print() {
        Set<Map.Entry<EventEnum,List<EventHandler>>> entrySet = handlerMap.entrySet();
        StringBuilder stringBuilder = new StringBuilder("EventCenter");
        for (Map.Entry<EventEnum,List<EventHandler>> entry : entrySet) {
            stringBuilder.append("{").append(entry.getKey().getDescription()).append("---").append(entry.getValue().size()).append("}\n");

        }
        return stringBuilder.toString();
    }
}
