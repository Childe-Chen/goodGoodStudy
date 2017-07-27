package com.cxd.solrWay.test;

/**
 * Created by childe on 2017/7/17.
 */
public class OrderEnumTest {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            Thread t = new Thread(()->{
                OrderVo e = OrderEnum.INSTANCE.orderVo;
                System.out.println(e);
            });
            t.setName("t" + i);
            t.start();
        }

//        OrderEnum[] orderEnums = OrderEnum.values();
//        for (int i = 0; i < orderEnums.length; i++) {
//            System.out.println(orderEnums[i].ordinal());
//        }
//        System.out.println(orderEnums[0].compareTo(orderEnums[1]));
    }
}
