package com.cxd.jvm.generic;

/**
 * desc
 *
 * @author fanyin
 * @date 2019/5/28 11:02
 **/
public class PairParent<T> {
    private T value;

    private String key;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
