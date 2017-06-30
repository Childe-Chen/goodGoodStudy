package com.cxd.classLoader;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by childe on 2017/6/27.
 */
public class Sample {
    private Sample instance;

    static {
        System.out.println("static");
    }

    public void setSample(Object instance) {
        this.instance = (Sample) instance;
    }
}
