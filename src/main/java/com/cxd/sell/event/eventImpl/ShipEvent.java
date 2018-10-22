package com.cxd.sell.event.eventImpl;

import com.cxd.sell.commodity.Commodity;
import com.cxd.sell.event.Event;
import com.cxd.sell.event.EventEnum;

/**
 * Created by childe on 2017/6/21.
 */
public class ShipEvent extends Event {
    public ShipEvent(String msg) {
        super();
        super.setMsg(msg);
    }

    public ShipEvent() {
    }

    private Commodity commodity;

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    @Override
    public EventEnum getEventType() {
        return EventEnum.SHIP;
    }
}
