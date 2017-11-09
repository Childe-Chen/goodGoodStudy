package com.cxd.algorithm.sort;

/**
 * 选择排序
 *
 * 首先从未排序序列中找到最小的元素，放置到排序序列的起始位置，
 * 然后从剩余的未排序序列中继续寻找最小元素，放置到已排序序列的末尾。
 *
 * @author childe
 * @date 2017/11/8 19:54
 **/
public class SelectionSort {

    static int[] sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int currMin = arr[i];
            int currMinIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (currMin < arr[j]) {
                    continue;
                }
                currMin = arr[j];
                currMinIndex = j;
            }

            arr[currMinIndex] = arr[i];
            arr[i] = currMin;
        }

        return arr;
    }

}
