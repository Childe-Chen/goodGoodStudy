package com.cxd.netty;

import io.netty.util.HashedWheelTimer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * netty 时间轮
 *
 * @author childe
 * @date 2019/1/28 14:24
 **/
public class HashedWheelTimerTest {
    public static void main(String[] args) {
        // 这是一个标示符，用来快速计算任务应该呆的格子。
        // 我们知道，给定一个deadline的定时任务，其应该呆的格子=deadline%wheel.length.但是%操作是个相对耗时的操作，所以使用一种变通的位运算代替：
        // 因为一圈的长度为2的n次方，mask = 2^n-1后低位将全部是1，然后deadline&mast == deadline%wheel.length
        // java中的HashMap也是使用这种处理方法 4 100  &011  5%4=1

        HashedWheelTimer wheelTimer = new HashedWheelTimer(1, TimeUnit.SECONDS, 4);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        wheelTimer.start();
        System.out.println("start:" + LocalDateTime.now().format(formatter));
        System.out.println();

        wheelTimer.newTimeout((timeout) -> {
            System.out.println("task1 " + LocalDateTime.now().format(formatter));
            Thread.sleep(2000);
        }, 2, TimeUnit.SECONDS);


        wheelTimer.newTimeout((timeout) -> {
            System.out.println("task2 " + LocalDateTime.now().format(formatter));
        }, 2, TimeUnit.SECONDS);


        wheelTimer.newTimeout((timeout) -> {
            System.out.println("task3 " + LocalDateTime.now().format(formatter));
        }, 2, TimeUnit.SECONDS);

    }
}
