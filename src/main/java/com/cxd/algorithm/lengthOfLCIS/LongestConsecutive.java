package com.cxd.algorithm.lengthOfLCIS;

import java.util.HashSet;
import java.util.Set;

/**
 * <a href="https://leetcode.cn/problems/longest-consecutive-sequence/">最长连续序列</a>
 * @author fanyin
 * @date 2023/4/18 15:54
 */
public class LongestConsecutive {


    public int longestConsecutive(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        for(int i = 0;i<nums.length; i++) {
            set.add(nums[i]);
        }

        int length = 1;
        int currLength = 1;
        for(int i = 0;i<nums.length; i++) {
            if(set.contains(nums[i] + 1)) {
                currLength = 1;
                continue;
            }
            int value = nums[i] - 1;
            while (set.contains(value)) {
                value--;
                currLength++;
            }
            length = Math.max(currLength, length);
            currLength = 1;
        }

        return length;
    }
}
