package com.cxd.zookeeper;

import org.apache.zookeeper.KeeperException;

/**
 * 类似于任务分解
 * 将一个父任务分解为若干个子任务，当所有子任务完成后，父任务进行后续汇总或者其他操作
 * 这个实现例子类似JDK中的CountDownLatch的应用，或者ForkJoinPool
 * Created by childe on 2017/5/2.
 */
public class BarrierTest {

    public static void main(String args[]) {
        Barrier b = enterBarrier();

        try {
            Thread.sleep(1000 * 60 * 5);
        } catch (InterruptedException e) {

        }

        Barrier b1 = enterBarrier();


        try {
            Thread.sleep(1000 * 60 * 2);
        } catch (InterruptedException e) {

        }

        try{
            b.leave();
            b1.leave();
        } catch (KeeperException e){

        } catch (InterruptedException e){

        }
        System.out.println("Left barrier");
    }

    private static Barrier enterBarrier() {
        Barrier b = new Barrier(SyncPrimitive.address, "/b1", 2);
        try{
            boolean flag = b.enter();
            System.out.println("Entered barrier: " + 2);
            if(!flag) System.out.println("Error when entering the barrier");
        } catch (KeeperException e){

        } catch (InterruptedException e){

        }
        return b;
    }
}
