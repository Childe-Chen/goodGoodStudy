package com.cxd.algorithm.maxArea;

/**
 * <a href="https://leetcode.cn/problems/container-with-most-water/submissions/">盛最多水的容器</a>
 * @author fanyin
 * @date 2023/4/18 16:02
 */
public class Solution {

    public static void main(String[] args) {
        int[] a = new int[]{1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea(a));
    }

    public static int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int area = 0;
        while (left < right) {
            int minValue = Math.min(height[left], height[right]);
            int currArea =  minValue * (right - left);
            area = Math.max(area, currArea);

            if (height[left] < height[right]) {
                do{
                    left++;
                }while(height[left] <= minValue && left < right);
                continue;
            }


            do{
                right--;
            }while(height[right] <= minValue && left < right);
        }

        return area;
    }
}
