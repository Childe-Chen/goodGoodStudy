package com.cxd.javaSourceCode;

/**
 * desc
 *
 * @author childe
 * @date 2018/5/30 10:24
 **/
public class StringTest {

    public static void main(String[] args) {
        String a = "aaa";
        String c = "aaa";

        a.intern();


        String b = "aaa";

        System.out.println(a == b);
        System.out.println(a == c);
    }
}
