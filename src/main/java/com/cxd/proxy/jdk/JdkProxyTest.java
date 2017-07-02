package com.cxd.proxy.jdk;

/**
 * Created by childe on 2017/7/2.
 */
public class JdkProxyTest {
    public static void main(String[] args) {
        Animal dog = new AnimalProxy(new Dog()).getProxiedAnimal();
        Animal cat = new AnimalProxy(new Cat()).getProxiedAnimal();
        dog.eat();
        cat.eat();
    }
}
