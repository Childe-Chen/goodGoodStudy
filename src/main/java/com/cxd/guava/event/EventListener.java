package com.cxd.guava.event;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.Subscribe;

/**
 * Created by childe on 2017/4/10.
 */
public abstract class EventListener {
    @Subscribe
    protected void handlerDeadEvent(DeadEvent event) {
        System.out.println(event.toString());
    }
}
