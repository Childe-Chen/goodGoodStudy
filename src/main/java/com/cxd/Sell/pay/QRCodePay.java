package com.cxd.Sell.pay;

import com.cxd.Sell.event.Event;

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
    public Event[] getInterestEvent() {
        return new Event[0];
    }
}
