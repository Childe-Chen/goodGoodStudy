package com.cxd.algorithm.hash;

/**
 * Created by childe on 2017/5/5.
 */
public class Entry {
    private String key;

    Entry(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return key;
    }
}
