package com.cxd.Sell.module;

import com.cxd.Sell.event.Event;
import com.cxd.Sell.event.EventHandler;

/**
 * 钱包
 * Created by childe on 2017/6/21.
 */
public class Wallet implements EventHandler,Module {
    @Override
    public void handle(Event e) {
        //增加或减少钱包金额
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
