package com.cxd.guava.event;

import com.google.common.eventbus.EventBus;

/**
 * Created by childe on 2017/4/10.
 */
public class BusPark {

    public static EventBus getBus() {
        Object o = new TimeOverListener();
        EventBus eventBus = new EventBus();
        eventBus.register(o);
        return eventBus;
    }

    public static void main(String[] args) {
        EventBus bus = getBus();
        Object event = new TimeOverEvent("time o");
        bus.post(event);
    }
}
