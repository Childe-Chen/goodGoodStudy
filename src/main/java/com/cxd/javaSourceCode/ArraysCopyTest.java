package com.cxd.javaSourceCode;


//import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by childe on 17/2/15.
 */
public class ArraysCopyTest {
    public static void main(String[] args) {
//        List<String> src = new ArrayList<>(5);
//        for (int i = 0; i < 5; i++) {
//            src.add(String.valueOf(i));
//        }
//
//        Object[] dest = Arrays.copyOf(src.toArray(),src.size());
//        for (int i = 0; i < dest.length; i++) {
//            System.out.printf(dest[i] + " , ");
//        }
//        System.out.println();
//        dest[0] = "sss";
//        System.out.println(src.get(0));
//        System.out.println(dest[0]);


        String[] ss = new String[]{"sss","sss","444"};
        String[] ss1 = new String[ss.length];
        System.arraycopy(ss,0,ss1,0,ss.length);
//        System.out.println(ToStringBuilder.reflectionToString(ss1));
    }
}
