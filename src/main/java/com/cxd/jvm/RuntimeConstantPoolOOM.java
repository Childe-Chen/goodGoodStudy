package com.cxd.jvm;

/**
 * Created by childe on 16/6/19.
 */
public class RuntimeConstantPoolOOM {
    public static void main(String[] args) {
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);

        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);

//        String s = "ss";
//        String s1 = null;
//        System.out.printf(s + s1);
    }
}
