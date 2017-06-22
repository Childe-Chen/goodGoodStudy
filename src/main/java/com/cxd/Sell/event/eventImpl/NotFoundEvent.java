package com.cxd.Sell.event.eventImpl;

import com.cxd.Sell.event.Event;
import com.cxd.Sell.event.EventEnum;

/**
 * Created by childe on 2017/6/22.
 */
public class NotFoundEvent extends Event {
    public NotFoundEvent(String msg) {
        super();
        super.setMsg(msg);
    }

    @Override
    public EventEnum getEventType() {
        return EventEnum.NOT_FOUND_COMMODITY;
    }
}
