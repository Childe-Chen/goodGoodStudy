package com.cxd.guava.event;

import com.google.common.eventbus.Subscribe;

/**
 * Created by childe on 2017/4/10.
 */
public class TimeOverListener extends EventListener {
    @Subscribe
    public void sayInfo(TimeOverEvent event) {
        System.out.println(event.getInfo());
    }
}
