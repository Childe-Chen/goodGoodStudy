package com.cxd.javaSourceCode;


import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.PriorityQueue;
import java.util.concurrent.*;

/**
 * link PriorityBlockingQueue
 *
 * @author childe
 * @date 2019/3/17 18:01
 **/
public class PriorityQueueTest {


    ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
            .setNameFormat("demo-pool-%d").build();

    static ScheduledExecutorService singleThreadPool = new ScheduledThreadPoolExecutor(1, (r) -> {
        Thread t = new Thread(r);
        t.setName("Scheduled");
        return t;
    });

    public static void main(String[] args) {

        singleThreadPool.scheduleAtFixedRate(() -> {
            System.out.println("start " + System.currentTimeMillis()/1000);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("end   " + System.currentTimeMillis()/1000);
            System.out.println();
        }, 0, 2, TimeUnit.SECONDS);

//        singleThreadPool.scheduleWithFixedDelay(() -> {
//            System.out.println("start " + System.currentTimeMillis()/1000);
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("end   " + System.currentTimeMillis()/1000);
//            System.out.println();
//        }, 0, 2, TimeUnit.SECONDS);


//        PriorityQueue<Integer> pq = new PriorityQueue<>();
//
//        pq.add(3);
//        pq.add(2);
//        pq.add(10);
//        pq.add(1);
//
//        pq.forEach(item -> System.out.print("," + item));
//
//        Future f = singleThreadPool.submit(() ->{
//            System.out.println("ss");
//        });
//
//        f.cancel(true);
//
//        ScheduledFuture f1 = singleThreadPool.schedule(() -> {
//            System.out.println("ss");
//        }, 10, TimeUnit.SECONDS);
//
//        f1.cancel(true);
    }
}
