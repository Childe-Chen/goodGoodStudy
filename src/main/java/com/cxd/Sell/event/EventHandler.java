package com.cxd.Sell.event;

/**
 * Created by childe on 2017/6/21.
 */
public interface EventHandler {

    void handle(Event e) throws Exception;

    EventEnum[] getInterestEvent();

    /**
     * 将修改后的拷贝数据写回
     */
    void commit();
}
