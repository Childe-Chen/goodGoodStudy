package com.cxd.javaSourceCode;

import java.util.HashMap;

/**
 * Created by childe on 2017/5/11.
 */
public class HashMapResizeTest {

    public static int resizeMoreTimeCount = 0;

    public static void main(String[] args) {
        int initialCapacity = 1;
        int expectCapacity = 100_0000;
        do {
            initialCapacity = initialCapacity << 1;
        } while (initialCapacity <= expectCapacity);

        int threshold = (int)(initialCapacity * 0.75);
        System.out.println("initialCapacity: " + initialCapacity + " resize threshold:" + threshold);

        for (int i = 0; i < 100; i++) {
            HashMap<String,Integer> preHot = new HashMap<>(initialCapacity);
            for (int j = 0; j < threshold + initialCapacity; j++) {
                preHot.put(String.valueOf(j),j);
            }

            test(initialCapacity,threshold,i);
            System.out.println(resizeMoreTimeCount);
        }
    }

    public static void test(int initialCapacity, int threshold, int times) {

        HashMap<String,Integer> noResize = new HashMap<>(initialCapacity);
        Long start = System.currentTimeMillis();
        for (int i = 0; i < threshold; i++) {
            noResize.put(String.valueOf(i),i);
        }
        Long end = System.currentTimeMillis();
        Long diffNoResize = end - start;
//        if (times > 79) {
//
//            System.out.println("no resize diff : " + diff);
//        }

        HashMap<String,Integer> resize = new HashMap<>(initialCapacity);
        start = System.currentTimeMillis();
        for (int i = 0; i < threshold + 1; i++) {
            resize.put(String.valueOf(i),i);
        }
        end = System.currentTimeMillis();
        Long diffResize = end - start;

        if (diffNoResize > diffResize) {
            resizeMoreTimeCount++;
        }

//        if (times > 79) {
//            System.out.println("resize diff : " + diff);
//        }
    }
}
