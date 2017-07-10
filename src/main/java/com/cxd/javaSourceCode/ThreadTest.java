package com.cxd.javaSourceCode;

/**
 * 线程的中断状态只是一个标记，需要线程自行处理，如果线程忽略中断状态，那么就像中断从未发生过一样。
 * 运行结束的线程中断标记默认为false
 * Created by childe on 2017/7/10.
 */
public class ThreadTest {
    public static void main(String[] args) {
        Thread thread1 = new Thread(()->{
//            try {
//                Thread.sleep(1000 * 3);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
////如果任何线程中断了当前线程。当抛出该异常时，当前线程的 中断状态 被清除
//                System.out.println(Thread.currentThread().isInterrupted());
//            }

            TreeMapTest.main(new String[0]);

            System.out.println("==" + Thread.currentThread().isInterrupted());

        });

        thread1.start();

        Thread thread2 = new Thread(()->{
            try {
                Thread.sleep(500 * 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            thread1.interrupt();
        });

        thread2.start();


        while (true) {

            if (thread1.isInterrupted()) {
                System.out.println("thread1.isInterrupted()");
            }
            if (!thread1.isAlive()) {
                System.out.println("thread1.isAlive() " + thread1.isInterrupted());
                break;
            }
            try {
                Thread.sleep(1000 * 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //线程结束后，isInterrupted()返回false。所以在程序结尾得到的线程终端状态始终为false
//        System.out.println(thread1.isInterrupted());
    }
}
