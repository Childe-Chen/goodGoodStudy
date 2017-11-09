package com.cxd.algorithm.sort;

import java.util.Random;

/**
 * 随机工具
 *
 * @author childe
 * @date 2017/11/8 19:57
 **/
public class SortUtil {

    static int[] genericArr(int num, int range) {
        int[] arr = new int[num];

        Random random = new Random();

        for (int i = 0; i < num; i++) {
            arr[i] = random.nextInt(range);
        }

        return arr;
    }

    static void print(int[] arr) {
        System.out.println("*******************************************");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ",");
            if (i == 10) {
                System.out.print("\n");
            }
        }
        System.out.print("\n");
    }
}
