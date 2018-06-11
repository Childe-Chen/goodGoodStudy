package com.cxd.javaSourceCode;

import java.util.concurrent.SynchronousQueue;

/**
 * desc
 *
 * @author childe
 * @date 2018/6/8 15:41
 **/
public class SynchronousQueueTest {

    public static void main(String[] args) {
        SynchronousQueue<String> queue = new SynchronousQueue<>();

        Thread provider = new Thread(()->{
            System.out.println("provider start");
            try {
                queue.put("1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("provider end");
        });

        Thread consumer = new Thread(()->{
            System.out.println("consumer start");
            try {
                System.out.println("consumer take = " + queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("consumer end");
        });

//        consumer.start();
        provider.start();

        try {
            Thread.sleep(1000 * 5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        provider.start();
        consumer.start();
    }
}
