package com.cxd.javaSourceCode;

import java.math.BigDecimal;

/**
 * Created by childe on 17/3/3.
 */
public class BigDecimalTest {
    public static void main(String[] args) {
        String num = "0.064000";
        BigDecimal old = new BigDecimal(num);
        System.out.println(old.setScale(2, BigDecimal.ROUND_HALF_UP));
    }
}
