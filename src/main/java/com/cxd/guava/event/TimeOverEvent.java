package com.cxd.guava.event;

/**
 * Created by childe on 2017/4/10.
 */
public class TimeOverEvent {
    private String info;

    public TimeOverEvent(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
