package com.cxd.algorithm.palindrome;

/**
 *
 * @author fanyin
 * @date 2023/4/14 14:27
 */
public class StringLongestSolution {


    public static void main(String[] args) {
        String[] a = new String[3];
        a[0] = "flower";
        a[1] = "flow";
        a[2] = "flight";

    }

    public String longestPalindrome(String s) {
        if (s ==null || s.length() < 2) {
            return s;
        }

        int maxLen = 0;
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandFromCenter(s, i, i);
            int len2 = expandFromCenter(s, i, i+1);

            int len = Math.max(len1, len2);
            if (len <= maxLen) {
                continue;
            }

            maxLen = len;
            start = i - (len - 1)/2;
        }

        return s.substring(start, start + maxLen);
    }

    public int expandFromCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length()) {
            if (s.charAt(left) != s.charAt(right)) {
                break;
            }

            left--;
            right++;
        }

        return right - left - 1;
    }



}
