package com.cxd.hessian;

import java.io.Serializable;

/**
 * Created by childe on 2017/7/5.
 */
public class Man implements Serializable{

    private String name;

    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
