package com.cxd.Sell.event.eventImpl;

import com.cxd.Sell.event.Event;
import com.cxd.Sell.event.EventEnum;

/**
 * Created by childe on 2017/6/21.
 */
public class RefundEvent extends Event {
    public RefundEvent(String msg) {
        super();
        super.setMsg(msg);
    }

    @Override
    public EventEnum getEventType() {
        return EventEnum.REFUND;
    }
}
