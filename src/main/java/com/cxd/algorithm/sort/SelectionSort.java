package com.cxd.algorithm.sort;

/**
 * 选择排序
 *
 * 首先从未排序序列中找到最小的元素，放置到排序序列的起始位置，
 * 然后从剩余的未排序序列中继续寻找最小元素，放置到已排序序列的末尾。
 * 具体做法是：选择最小的元素与未排序部分的首部交换，使得序列的前面为有序。
 *
 * 最好情况：交换0次，但是每次都要找到最小的元素，因此大约必须遍历N*N次，因此为O(N*N)。减少了交换次数！
 *
 * 最坏情况：平均情况下：O(N*N)
 *
 * 由于每次都是选取未排序序列A中的最小元素x与A中的第一个元素交换，因此跨距离了，很可能破坏了元素间的相对位置，因此选择排序是不稳定的！比如：5,5,3
 *
 * @author childe
 * @date 2017/11/8 19:54
 **/
public class SelectionSort {

    static int[] sort(int[] arr) {
        // 总共要经过 N-1 轮比较
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;

            // 每轮需要比较的次数 N-i
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    // 记录目前能找到的最小值元素的下标
                    min = j;
                }
            }

            // 将找到的最小值和i位置所在的值进行交换
            if (i != min) {
                int tmp = arr[i];
                arr[i] = arr[min];
                arr[min] = tmp;
            }

        }
        return arr;
    }

}
