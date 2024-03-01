package com.cxd.algorithm.lengthOfLCIS;

import org.apache.commons.math3.analysis.function.Max;

import java.util.HashSet;
import java.util.Set;

/**
 * <a href="https://leetcode.cn/problems/longest-continuous-increasing-subsequence/">连续递增的子序列</a>
 * @author fanyin
 * @date 2023/4/18 14:44
 */
public class Solution {
    public int findLengthOfLCIS(int[] nums) {

        int length = 1;
        int lastIndex = 0;

        // 1 3 5 7
        // 1 3 5 4 7
        for (int endIndex = 1; endIndex < nums.length; endIndex++) {
            if (nums[endIndex-1] < nums[endIndex]) {
                if (endIndex + 1 == nums.length) {
                    length = Math.max(length, endIndex - lastIndex +1);
                    break;
                }

                continue;
            }


            length = Math.max(length, endIndex - lastIndex);
            lastIndex = endIndex;

        }

        return length;
    }


}
