package com.cxd.algorithm.sort;

/**
 * 排序
 *
 * @author childe
 * @date 2017/11/8 20:19
 **/
public class Main {

    public static void main(String[] args) {
        int[] arr = SortUtil.genericArr(10, 100);

        SortUtil.print(arr);
        SortUtil.print(SelectionSort.sort(arr));


    }
}
