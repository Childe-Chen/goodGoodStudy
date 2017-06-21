package com.cxd.Sell.pay;

import com.cxd.Sell.module.Module;
import com.cxd.Sell.event.EventHandler;
import com.cxd.Sell.module.Wallet;

/**
 * Created by childe on 2017/6/21.
 */
public abstract class Pay implements EventHandler,Module {

    protected Wallet wallet = new Wallet();

    abstract void pay();

    @Override
    public void on() {
        wallet.on();
    }

    @Override
    public void off() {

    }

    public Wallet getWallet() {
        return wallet;
    }
}
