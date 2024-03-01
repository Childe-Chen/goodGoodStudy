package com.cxd.algorithm.longestCommonPrefix;

/**
 * @author fanyin
 * @date 2023/4/14 16:02
 */
public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0 || strs[0].length() == 0) {
            return "";
        }

        int longestCommonIndex = 0;

        char curr = strs[0].charAt(longestCommonIndex);
        do{

            for(int i=1;i<strs.length;i++) {
                if(strs[i].length() == longestCommonIndex) {
                    return strs[0].substring(0, longestCommonIndex);
                }

                if (strs[i].charAt(longestCommonIndex) != curr) {
                    return strs[0].substring(0, longestCommonIndex);
                }
            }

            longestCommonIndex++;

            if (strs[0].length() == longestCommonIndex) {
                return strs[0].substring(0, longestCommonIndex);
            }

            curr = strs[0].charAt(longestCommonIndex);
        }while(true);
    }
}
