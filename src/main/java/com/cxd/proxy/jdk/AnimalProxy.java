package com.cxd.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by childe on 2017/7/2.
 */
public class AnimalProxy implements InvocationHandler {
    private Animal animal;

    public AnimalProxy(Animal animal) {
        this.animal = animal;
    }

    /**
     * 生成代理byte[]  Proxy$ProxyClassFactory.apply(...)
     * @return
     */
    public Animal getProxiedAnimal() {
        return (Animal) Proxy.newProxyInstance(
                getClass().getClassLoader(),
                animal.getClass().getInterfaces(),
                this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("吃前吠两声");
        Object result = method.invoke(animal, args);
        System.out.println("吃后吠两声");
        return result;
    }
}