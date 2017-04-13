package com.cxd.jvm.references;

/**
 * Created by childe on 2017/3/31.
 */
public class Grocery {
    private static final int SIZE = 10000;
    // 属性d使得每个Grocery对象占用较多内存，有80K左右
    private double[] d = new double[SIZE];
    private String id;
    public Grocery(String id) {
        this.id = id;
    }
    public String toString() {
        return id;
    }
    public void finalize() {
        System.out.println("Finalizing " + id);
    }
}
