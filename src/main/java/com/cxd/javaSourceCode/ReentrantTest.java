package com.cxd.javaSourceCode;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * desc
 *
 * @author fanyin
 * @date 2019/3/25 22:55
 **/
public class ReentrantTest {

    public static void main(String[] args) throws Exception {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        Runnable r = () -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " start wait");
                condition.await();
                System.out.println(Thread.currentThread().getName() + " sss");

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();

                System.out.println(Thread.currentThread().getName() + " rrr " + lock.getHoldCount());
            }
        };

        Thread t1 = new Thread(r, "t1");
        Thread t2 = new Thread(r, "t2");
        Thread t3 = new Thread(r, "t3");

        t1.start();
        t2.start();
        t3.start();

        Thread.sleep(3000);

        System.out.println(lock.getHoldCount());

        Thread r4 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " start lock");
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "  locked");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            condition.signalAll();
            lock.unlock();
        }, "t4");

        r4.start();

//        Thread.currentThread().join();
    }
}
