package com.cxd.jvm.references;

import java.lang.ref.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by childe on 2017/3/31.
 */
public class References {
    private static ReferenceQueue<Grocery> rq = new ReferenceQueue<>();

    public static void checkQueue() {
        Reference<? extends Grocery> inq = rq.poll(); // 从队列中取出一个引用
        if (inq != null)
            System.out.println("In queue: " + inq + " : " + inq.get());
    }

    public static void main(String[] args) {
        final int size = 10;
        // 创建10个Grocery对象以及10个软引用
        Set<SoftReference<Grocery>> sa = new HashSet<>();
        for (int i = 0; i < size; i++) {
            SoftReference<Grocery> ref = new SoftReference<>(
                    new Grocery("Soft " + i), rq);
            System.out.println("Just created: " + ref.get());
            sa.add(ref);
        }

        System.gc();
        try {
            Thread.sleep(2000);
        } catch (Exception e) {

        }
        checkQueue();

        // 创建10个Grocery对象以及10个弱引用
        Set<WeakReference<Grocery>> wa = new HashSet<>();
        for (int i = 0; i < size; i++) {
            WeakReference<Grocery> ref = new WeakReference<>(
                    new Grocery("Weak " + i), rq);
            System.out.println("Just created: " + ref.get());
            wa.add(ref);
        }
        System.gc();
        try {
            Thread.sleep(2000);
        } catch (Exception e) {

        }

        checkQueue();

        // 创建10个Grocery对象以及10个虚引用
        Set<PhantomReference<Grocery>> pa = new HashSet<>();
        for (int i = 0; i < size; i++) {
            PhantomReference<Grocery> ref = new PhantomReference<>(
                    new Grocery("Phantom " + i), rq);
            System.out.println("Just created: " + ref.get());
            pa.add(ref);
        }
        System.gc();
        try {
            Thread.sleep(2000);
        } catch (Exception e) {

        }

        checkQueue();
    }
}
