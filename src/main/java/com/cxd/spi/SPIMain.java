package com.cxd.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Created by childe on 17/2/25.
 */
public class SPIMain {
    public static void main(String[] args) {
        ServiceLoader<SayHello> sayHellos = ServiceLoader.load(SayHello.class);
        Iterator<SayHello> sayHelloIterator = sayHellos.iterator();
        while (sayHelloIterator.hasNext()) {
            SayHello sayHello = sayHelloIterator.next();
            sayHello.say();
        }
    }
}
