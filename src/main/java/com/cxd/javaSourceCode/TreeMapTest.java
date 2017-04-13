package com.cxd.javaSourceCode;

import java.util.*;

/**
 * Created by childe on 16/11/3.
 */
public class TreeMapTest {
    public static void main(String[] args) {
        for (int i = 1; i <= 100; i++) {
            System.out.println("==========" + i + "==========");
            TreeMap<Integer,Integer> treeMap = treeMaptest();
            HashMap<Integer,Integer> hashMap = hashMaptest();
            getKey(treeMap,hashMap);
        }

    }

    private static TreeMap treeMaptest() {
        TreeMap<Integer,Integer> treeMap = new TreeMap<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5000000; i++) {
            treeMap.put(i,i);
        }
        System.out.println(treeMap.getClass().getName() + " put :" + (System.currentTimeMillis() - start));

        return treeMap;
    }

    private static HashMap hashMaptest() {
        HashMap<Integer,Integer> hashMap = new HashMap<>(5000000 + 3750000);

        long start = System.currentTimeMillis();
        for (int i = 0; i < 5000000; i++) {
            hashMap.put(i,i);
        }
        System.out.println(hashMap.getClass().getName() + " put :" + (System.currentTimeMillis() - start));
        return hashMap;
    }

    private static void getKey(TreeMap<Integer, Integer> treeMap,HashMap<Integer, Integer> hashMap) {
        Random random = new Random();
        long start;
        long treeAllTime = 0;
        long hashAllTime = 0;
        for (int i = 0; i < 100000; i++) {
            Integer key = random.nextInt(5000000);

            start = System.currentTimeMillis();
            treeMap.get(key);
            long time = System.currentTimeMillis() - start;
            treeAllTime+=time;

            start = System.currentTimeMillis();
            treeMap.get(key);
            time = System.currentTimeMillis() - start;
            hashAllTime+=time;

        }
        System.out.println(treeMap.getClass().getName() + " get :" + treeAllTime);
        System.out.println(hashMap.getClass().getName() + " get :" + hashAllTime);
    }
}
