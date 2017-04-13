package com.cxd.jvm.references.ref;

import java.lang.ref.WeakReference;

/**
 *
 * Created by childe on 2017/3/31.
 */
public class ClassWeak {
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

    /**
     * 执行结果
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        System.out.println("创建一个弱引用--->");

        Referred strong = new Referred();
        WeakReference<Referred> weak = new WeakReference<>(strong);

        ClassWeak.collect();
        System.out.println("删除引用--->");

        strong = null;
        ClassWeak.collect();

        System.out.println("Done");
    }
}
