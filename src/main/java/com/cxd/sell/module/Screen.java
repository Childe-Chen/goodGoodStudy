package com.cxd.sell.module;

import com.cxd.sell.event.Event;
import com.cxd.sell.event.EventEnum;
import com.cxd.sell.event.EventHandler;

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
    public EventEnum[] getInterestEvent() {
        return new EventEnum[]{EventEnum.COMMDITY_SELECTED};
    }

    @Override
    public void on() {

    }

    @Override
    public void off() {

    }

    @Override
    public void commit() {

    }
}
