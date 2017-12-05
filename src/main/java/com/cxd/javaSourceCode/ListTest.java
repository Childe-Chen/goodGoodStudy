package com.cxd.javaSourceCode;

import java.util.ArrayList;
import java.util.List;

/**
 * desc
 *
 * @author childe
 * @date 2017/12/5 19:19
 **/
public class ListTest {
    public static void main(String[] args) {
        List<String> t = new ArrayList<>();
        t.add("1");
        t.add("2");
        t.add("3");
        int index = t.indexOf("1");
        System.out.println(index);
        t.add(index,"2222");
        t.remove(index);
        for (int i = 0; i < t.size(); i++) {
            System.out.println(t.get(i));
        }
    }
}
