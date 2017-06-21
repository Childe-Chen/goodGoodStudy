package com.cxd.Sell.event;

/**
 * Created by childe on 2017/6/21.
 */
public interface EventHandler {

    void handle(Event e);

    Event[] getInterestEvent();
}
