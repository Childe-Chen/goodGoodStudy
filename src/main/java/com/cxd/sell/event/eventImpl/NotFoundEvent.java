package com.cxd.sell.event.eventImpl;

import com.cxd.sell.event.Event;
import com.cxd.sell.event.EventEnum;

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
