package com.cxd.algorithm.consistentHash;

/**
 * 缓存实体
 * Created by childe on 2017/5/14.
 */
public class Entry {
    private String key;

    Entry(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
