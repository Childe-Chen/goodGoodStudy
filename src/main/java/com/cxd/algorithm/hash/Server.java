package com.cxd.algorithm.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by childe on 2017/5/5.
 */
public class Server {
    private String name;
    private Map<Entry, Entry> entries;

    Server(String name) {
        this.name = name;
        entries = new HashMap<>();
    }

    public void put(Entry e) {
        entries.put(e, e);
    }

    public Entry get(Entry e) {
        return entries.get(e);
    }
}
