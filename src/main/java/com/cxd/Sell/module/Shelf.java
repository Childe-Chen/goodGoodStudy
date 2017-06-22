package com.cxd.Sell.module;

import com.cxd.Sell.Constant;
import com.cxd.Sell.commodity.Commodity;
import com.cxd.Sell.event.Event;
import com.cxd.Sell.event.EventEnum;
import com.cxd.Sell.event.EventHandler;
import com.cxd.Sell.event.eventImpl.CommditySelectedEvent;
import com.cxd.Sell.event.eventImpl.NotFoundEvent;
import com.cxd.Sell.event.eventImpl.ShipEvent;

/**
 * Created by childe on 2017/6/22.
 */
public class Shelf implements Module,EventHandler {

    final Commodity[][] shelf = new Commodity[Constant.SHELF_X][Constant.SHELF_Y];


    @Override
    public void handle(Event e) {
        if (e instanceof CommditySelectedEvent) {
            CommditySelectedEvent event = (CommditySelectedEvent)e;
            getCommodity(event.getX(),event.getY());
        }
    }

    private void getCommodity(int x, int y) {
        Commodity commodity = null;
        try {
            commodity = shelf[x][y];
        } catch (Exception e) {
            //publish NotFoundEvent
            NotFoundEvent notFoundEvent = new NotFoundEvent("NotFoundEvent");

            return;
        }

        //库存检查
        if (commodity.getStock() < 1) {
            //publish NoStockEvent
            return ;
        }

        //publish ShipEvent
        ShipEvent shipEvent = new ShipEvent();
        shipEvent.setCommodity(commodity);

    }

    @Override
    public EventEnum[] getInterestEvent() {
        return new EventEnum[0];
    }

    @Override
    public void on() {

    }

    @Override
    public void off() {

    }

    @Override
    public void commit() {

    }
}
