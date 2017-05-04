package com.cxd.jvm.references.ref;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;

/**
 *
 * Created by childe on 2017/3/31.
 */
public class ClassWeak {
    public static class Referred {
        /**
         * 不是必须实现，和Strong不同。
         * 实现该方法是为了追踪GC
         * 实现后也会被当作Finalizer
         * @throws Throwable
         */
        @Override
        protected void finalize() throws Throwable {
            System.out.println("Referred对象被垃圾收集");
        }

        @Override
        public String toString() {
            return "I am weak";
        }
    }

    public static void collect() throws InterruptedException {
        System.gc();
        Thread.sleep(2000);
    }

    static class CheckRefQueueThread extends Thread{
        @Override
        public void run() {
            Reference<Referred> obj = null;
            try {
                obj = (Reference<Referred>) weakQueue.remove();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(obj != null) {
                //因为虚引用的指示对象总是不可到达的，所以此方法总是返回 null
                System.out.println("Object for phantomReference is " + obj.get());
                try {
                    Field referent = Reference.class.getDeclaredField("referent");
                    referent.setAccessible(true);
                    Object result = referent.get(obj);
                    System.out.println("gc will collect: " + result.getClass() + "@" + result.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    static ReferenceQueue<Referred> weakQueue = new ReferenceQueue<>();

    /**
     * JVM配置
     * -XX:+PrintGCDetails -Xloggc:/Users/childe/logs/gc-f.log
     * 务必加上该参数，以确定collect方法后GC被执行
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        System.out.println("创建一个弱引用");

        Referred strong = new Referred();
        WeakReference<Referred> weak = new WeakReference<>(strong,weakQueue);
        new CheckRefQueueThread().start();

        ClassWeak.collect();
        System.out.println("切断强引用");

        strong = null;

        System.out.println("GC之前，弱引用值：" + weak.get().toString());

        ClassWeak.collect();

        System.out.println("Done");
    }
}
