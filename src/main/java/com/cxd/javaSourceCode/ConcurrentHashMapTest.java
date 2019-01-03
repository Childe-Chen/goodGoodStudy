package com.cxd.javaSourceCode;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * desc
 *
 * @author childe
 * @date 2018/12/21 16:49
 **/
public class ConcurrentHashMapTest {

    public static void main(String[] args) {
        Map<String, String> testMap = new ConcurrentHashMap<>();
        testMap.put("a", "1");
        testMap.put("a", "12");
    }
}
