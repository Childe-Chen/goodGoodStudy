package com.cxd.Sell.module;

import com.cxd.Sell.event.Event;
import com.cxd.Sell.event.EventHandler;
import com.cxd.Sell.module.Module;

/**
 * 显示器
 * Created by childe on 2017/6/21.
 */
public class Screen implements EventHandler,Module {

    @Override
    public void handle(Event e) {
        //显示
    }

    @Override
    public Event[] getInterestEvent() {
        return new Event[0];
    }

    @Override
    public void on() {

    }

    @Override
    public void off() {

    }
}
