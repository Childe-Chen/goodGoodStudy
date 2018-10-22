package com.cxd.sell.event;

/**
 * Created by childe on 2017/6/21.
 */
public abstract class Event {

    private String msg;

    abstract protected EventEnum getEventType();

    protected String getMsg() {
        return msg;
    }

    protected void setMsg(String msg) {
        this.msg = msg;
    }
}
