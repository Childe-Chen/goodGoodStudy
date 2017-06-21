package com.cxd.Sell.module;

/**
 * 贩卖机模块
 * Created by childe on 2017/6/21.
 */
public interface Module {
    /**
     * 启动，初始化
     */
    void on();

    /**
     * 关闭
     */
    void off();
}