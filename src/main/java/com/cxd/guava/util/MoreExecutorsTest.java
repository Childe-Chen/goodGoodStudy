package com.cxd.guava.util;

import com.google.common.util.concurrent.MoreExecutors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * desc
 *
 * @author childe
 * @date 2018/4/28 14:19
 **/
public class MoreExecutorsTest {

    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        MoreExecutors.addDelayedShutdownHook(scheduler, 1, TimeUnit.SECONDS);
        scheduler.schedule(() -> {
            System.out.println("ss");
        }, 0, TimeUnit.SECONDS);

        scheduler.schedule(() -> {
            System.out.println("wwwww");
        }, 10, TimeUnit.SECONDS);

        scheduler.shutdown();
    }
}
