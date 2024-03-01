package com.cxd.algorithm.subarraySum;

/**
 * @author fanyin
 * @date 2023/4/18 17:30
 */
public class Solution {
    public static void main(String[] args) {
        System.out.println(twoSum(new int[]{-1,0}, -1));
    }
    public static int subarraySum(int[] nums, int k) {
        int number = 0;

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] == k) {
                number++;
                continue;
            }

            int sum = nums[i];
            for (int j = i+1; j < nums.length; j++) {
                sum = sum + nums[j];
                if (sum == k) {
                    number++;
                }
            }
        }

        return number;
    }


    public static int[] twoSum(int[] numbers, int target) {
        for(int i=0;i<numbers.length;i++){
            if(numbers[i]>target && target > 0){
                break;
            }

            if(numbers[i]==target && target != 0){
                break;
            }

            for(int j=i+1;j<numbers.length;j++){
                if(numbers[j]>target && target > 0){
                    break;
                }

                if(numbers[i]+numbers[j]==target) {
                    return new int[]{i+1,j+1};
                }

            }
        }
        return new int[0];
    }
}
