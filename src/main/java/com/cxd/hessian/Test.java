package com.cxd.hessian;

import net.sf.json.JSONObject;

/**
 * hessian 序列化会保存类的信息
 * Created by childe on 2017/7/5.
 */
public class Test {
    public static void main(String[] args) throws Exception {
//        Man man = new Man();
//        man.setAge(12);
//        man.setName("hessian");
//
//        String json = JSONObject.fromObject(man).toString();
//
//        System.out.println(json);

        for (int i = 0; i < 10;) {
            Man man1 = new Man();
            man1.setAge(++i);
            System.out.println(man1.getAge());
        }


//        Woman woman= com.alibaba.fastjson.JSONObject.parseObject(json,Woman.class);
//
//        System.out.println(woman);
//
//        byte[] hessianByte = HessianUtil.serialize(man);
//
//        System.out.println(new String(hessianByte));
//
//        com.cxd.hessian.pojo.Man womanHessian = (com.cxd.hessian.pojo.Man) HessianUtil.deserialize(hessianByte);
//        System.out.println(womanHessian);
    }
}
