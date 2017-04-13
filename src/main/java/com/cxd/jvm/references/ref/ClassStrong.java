package com.cxd.jvm.references.ref;

/**
 * Created by childe on 2017/3/31.
 */
public class ClassStrong {

    public static class Referred {
        @Override
        protected void finalize() throws Throwable {
            System.out.println("Referred对象被垃圾收集");
        }
    }

    public static void collect() throws InterruptedException {
        System.gc();
        Thread.sleep(2000);
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("创建一个强引用--->");

        // 这是一个强引用
        // 如果没有引用这个对象就会被垃圾收集
        Referred strong = new Referred();

        // 开始垃圾收集
        ClassStrong.collect();

        System.out.println("删除引用--->");
        // 这个对象将要被垃圾收集
        strong = null;
        ClassStrong.collect();

        System.out.println("Done");
    }
}
