package com.cxd.algorithm.removeDuplicates;

/**
 * @author fanyin
 * @date 2023/4/18 17:20
 */
public class Solution {
    public static void main(String[] args) {
        System.out.println(removeDuplicates(new int[]{1,1,2}));
        System.out.println(removeDuplicates(new int[]{0,0,1,1,1,2,2,3,3,4}));
    }
    public static int removeDuplicates(int[] nums) {
        int nextReplaceIndex = 1;
        int currValue = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (currValue == nums[i]) {
                continue;
            }

            currValue = nums[i];
            nums[nextReplaceIndex] = nums[i];
            nextReplaceIndex++;
        }

        return nextReplaceIndex++;
    }
}
