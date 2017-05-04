package com.cxd.jvm.references.ref;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by childe on 2017/3/31.
 */
public class ClassPhantom {

    public static class Referred {
        @Override
        protected void finalize() throws Throwable {
            System.out.println("Referred对象被垃圾收集");
        }

        @Override
        public String toString() {
            return "Referredqq";
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
                obj = (Reference<Referred>) phantomQueue.remove();
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

    static ReferenceQueue<Referred> phantomQueue = new ReferenceQueue<>();

    /**
     * -Xms4m -Xmx4m
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        System.out.println("创建一个软引用");

        Referred strong = new Referred();
        PhantomReference<Referred> soft = new PhantomReference<>(strong, phantomQueue);
        new CheckRefQueueThread().start();

        collect();

        System.out.println("切断强引用");

        strong = null;
        collect();

        System.out.println("开始堆占用");

        try {
            List<byte[]> bytes = new ArrayList<>();
            while (true) {
                bytes.add(new byte[1024*1024]);
                collect();
            }
        } catch (OutOfMemoryError e) {
            // 软引用对象应该在这个之前被收集
            System.out.println("内存溢出...");
        }

        System.out.println("Done");
    }
}
