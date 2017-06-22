package com.cxd.Sell.pay;

import com.cxd.Sell.event.Event;
import com.cxd.Sell.event.EventEnum;

/**
 * Created by childe on 2017/6/21.
 */
public class QRCodePay extends Pay {
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
