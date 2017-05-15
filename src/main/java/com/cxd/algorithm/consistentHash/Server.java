package com.cxd.algorithm.consistentHash;

import java.util.*;

/**
 * 模拟缓存机器
 * Created by childe on 2017/5/14.
 */
public class Server {
    private String name;
    private Map<String, Entry> entries;

    Server(String name) {
        this.name = name;
        entries = new HashMap<>();
    }

    public void put(Entry e) {
        entries.put(e.getKey(), e);
    }

    public Entry get(String key) {
        return entries.get(key);
    }

    public int hashCode() {
        return name.hashCode();
    }

}
