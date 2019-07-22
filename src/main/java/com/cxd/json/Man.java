package com.cxd.json;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * Created by childe on 2017/7/5.
 */
public class Man implements Serializable{

    private String name;

    private int age;

    public Man() {
    }

    public Man(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    @JSONField(serialize = false, deserialize = false)
    public String getAgeStr() {
        return String.valueOf(age);
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Man{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
