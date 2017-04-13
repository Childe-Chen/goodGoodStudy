package com.cxd.jvm.references.ref;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by childe on 2017/3/31.
 */
public class ClassSoft {

    public static class Referred {
        @Override
        protected void finalize() throws Throwable {
            System.out.println("Referred对象被垃圾收集");
        }

        @Override
        public String toString() {
            return "Referred";
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
                obj = (Reference<Referred>)softQueue.remove();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(obj != null) {
                try {
                    Field referent = Reference.class.getDeclaredField("referent");
                    referent.setAccessible(true);
                    Object result = referent.get(obj);
                    //此处异常可以说明，在被放入队列之前referent已经被JVM置为null
                    System.out.println("gc will collect: " + result.getClass() + "@" + result.hashCode());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("Object for SoftReference is " + obj.get());
            }
        };
    }

    static ReferenceQueue<Referred> softQueue = new ReferenceQueue<>();

    /**
     * -Xms4m -Xmx4m
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        System.out.println("创建一个软引用--->");

        Referred strong = new Referred();
        SoftReference<Referred> soft = new SoftReference<>(strong,softQueue);
        new CheckRefQueueThread().start();

        ClassSoft.collect();

        System.out.println("删除引用--->");

        strong = null;
        ClassSoft.collect();

        System.out.println("开始堆占用");

        try {
            List<byte[]> bytes = new ArrayList<>();
            while (true) {
                bytes.add(new byte[1024*1024]);
                ClassSoft.collect();
            }
        } catch (OutOfMemoryError e) {
            // 软引用对象应该在这个之前被收集
            System.out.println("内存溢出...");
        }

        System.out.println("Done");
    }
}
