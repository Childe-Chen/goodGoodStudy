package com.cxd.solrVelocity.test;

/**
 * Created by childe on 2017/7/17.
 */
public enum OrderEnum {

    INSTANCE(new OrderVo("Order construct")),
    INSTANCE1(new OrderVo("Order construct1"));

    OrderVo orderVo;

    static {
        System.out.println("orderEnum construct-" + Thread.currentThread().getName() + "-init");
    }

    OrderEnum(OrderVo orderVo) {
        System.out.println("OrderEnum construct");
        this.orderVo = orderVo;
    }
}
