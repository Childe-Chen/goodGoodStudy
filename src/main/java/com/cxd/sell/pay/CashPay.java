package com.cxd.sell.pay;

import com.cxd.sell.event.Event;
import com.cxd.sell.event.EventEnum;

/**
 * Created by childe on 2017/6/21.
 */
public class CashPay extends Pay {
    @Override
    public void pay() {

    }

    @Override
    public void handle(Event e) {

    }

    @Override
    public EventEnum[] getInterestEvent() {
        return new EventEnum[0];
    }

    @Override
    public void commit() {

    }
}
