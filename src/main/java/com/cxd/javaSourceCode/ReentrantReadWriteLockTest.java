package com.cxd.javaSourceCode;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by childe on 2017/7/10.
 */
public class ReentrantReadWriteLockTest {

    public static void main(String[] args) {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

        //锁的申请和释放要成对出现。读写锁可降级，但无法升级
        try {
            writeLock.lock();
            System.out.println("main writeLock.lock()");
            readLock.lock();
            System.out.println("main readLock.lock()");
            writeLock.lock();
            System.out.println("main writeLock.lock() again");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
            writeLock.unlock();
            writeLock.unlock();
        }

//        new Thread(()->{
//            try {
//                writeLock.lock();
//                System.out.println("branch thread writeLock.lock()");
//                Thread.sleep(1000 * 5);
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                writeLock.unlock();
//            }
//        }).start();
    }
}
