package com.cxd.spi.impl;

import com.cxd.spi.SayHello;

/**
 * Created by childe on 17/2/25.
 */
public class JerrySayHello implements SayHello {
    @Override
    public void say() {
        System.out.printf("jerry say hello");
    }
}
