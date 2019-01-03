package com.cxd.javaSourceCode;

import java.util.*;

/**
 * Created by childe on 16/11/3.
 */
public class TreeMapTest {
    public static void main(String[] args) {
        Map<Integer, Integer> treeMap = new TreeMap<>();
        for (int i = 1; i <= 5; i++) {
            treeMap.put(i, i);
        }

        // todo 不为零问题？？？
        System.out.println(1f - 0.8f);
    }
}