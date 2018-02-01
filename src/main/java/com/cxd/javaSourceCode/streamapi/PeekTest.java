package com.cxd.javaSourceCode.streamapi;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * desc
 *
 * @author childe
 * @date 2017/12/13 20:44
 **/
public class PeekTest {
    public static void main(String[] args) {
        System.out.println(Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList()));
    }
}
