package com.cxd.javaSourceCode;

import java.math.BigDecimal;

/**
 * Created by childe on 17/3/3.
 */
public class BigDecimalTest {
    public static void main(String[] args) {
        String num = "0.707000";
        BigDecimal old = new BigDecimal(num);
        System.out.println(old.setScale(2, BigDecimal.ROUND_DOWN));

//        BigDecimal o = new BigDecimal(1.01);
//        BigDecimal o1 = new BigDecimal(0.5);
//        o.setScale(1,BigDecimal.ROUND_HALF_UP);
//        System.out.println(o);
//
//        Short s = 1;
//        byte b = 1;
//        System.out.println(s == b);
    }
}
