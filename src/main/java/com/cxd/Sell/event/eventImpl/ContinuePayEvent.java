package com.cxd.Sell.event.eventImpl;

import com.cxd.Sell.event.Event;
import com.cxd.Sell.event.EventEnum;

/**
 * Created by childe on 2017/6/21.
 */
public class ContinuePayEvent extends Event {
    public ContinuePayEvent(String msg) {
        super();
        super.setMsg(msg);
    }

    @Override
    public EventEnum getEventType() {
        return EventEnum.CONTINUE_PAY;
    }
}
