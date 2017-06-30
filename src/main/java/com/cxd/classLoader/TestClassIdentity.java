package com.cxd.classLoader;

import java.lang.reflect.Method;

/**
 * 双亲委派
 * Created by childe on 2017/6/27.
 */
public class TestClassIdentity {

    public static void main(String[] args) {
        FileSystemClassLoader fileSystemClassLoader = new FileSystemClassLoader("/Users/childe/Documents/workspace/goodGoodStudy/target/classes");
        FileSystemClassLoader fileSystemClassLoader1 = new FileSystemClassLoader("/Users/childe/Documents/workspace/goodGoodStudy/target/classes");
        try {
//            Class<?> c = fileSystemClassLoader.findClass("com.cxd.classLoader.Sample");

//            Class<?> c3 = fileSystemClassLoader.findClass("com.cxd.classLoader.Sample");

//            System.out.println(Sample.class.isAssignableFrom(c));
//            System.out.println(c.getClassLoader());

//            Class<?> c2 = fileSystemClassLoader1.loadClass("com.cxd.classLoader.Sample");
//
//            Object o2 = c2.newInstance();

//            Class<?> c1 = Class.forName("com.cxd.classLoader.Sample");
//            System.out.println(Sample.class.isAssignableFrom(c1));
//            System.out.println(c1.getClassLoader());

            //运行时抛出了 java.lang.ClassCastException异常。虽然两个对象 o1 o2的类的名字相同，但是这两个类是由不同的类加载器实例来加载的，因此不被 Java 虚拟机认为是相同的。
            //不同的类加载器为相同名称的类创建了额外的名称空间。相同名称的类可以并存在 Java 虚拟机中，只需要用不同的类加载器来加载它们即可。
            // 不同类加载器加载的类之间是不兼容的，这就相当于在 Java 虚拟机内部创建了一个个相互隔离的 Java 类空间。这种技术在许多框架中都被用到
//            Object o = c.newInstance();
//            Method method = c.getMethod("setSample", java.lang.Object.class);
//
//            Object o1 = c1.newInstance();
//            method.invoke(o,o1);

            //java.lang.SecurityException: Prohibited package name: java.lang
//            Class<?> c = fileSystemClassLoader.findClass("java.lang.String");
//            System.out.println(c.isAssignableFrom(String.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
