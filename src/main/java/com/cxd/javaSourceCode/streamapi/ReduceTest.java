package com.cxd.javaSourceCode.streamapi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * desc
 *
 * @author childe
 * @date 2018/7/4 11:33
 **/
public class ReduceTest {
    public static void main(String[] args) {
        System.out.println(Stream.of(1).reduce(0, Integer::sum));

        Stream.of(1,2,3,33,333).forEach(integer -> {
            if (integer > 10) {
                System.out.println(integer);
                return;
            }
            System.out.println("ss" + integer);
        });

    }
}
