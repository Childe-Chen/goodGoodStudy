package com.cxd.zookeeper;

import org.apache.zookeeper.KeeperException;

/**
 * 生产者-消费者
 * Created by childe on 2017/5/2.
 */
public class QueueTest {

    public static void main(String args[]) {
        Queue q = new Queue(SyncPrimitive.address, "/app1");

        System.out.println("Input: " + SyncPrimitive.address);
        int i;
        Integer max = new Integer(args[2]);

        if (args[3].equals("p")) {
            System.out.println("Producer");
            for (i = 0; i < max; i++)
                try{
                    q.produce(10 + i);
                } catch (KeeperException e){

                } catch (InterruptedException e){

                }
        } else {
            System.out.println("Consumer");

            for (i = 0; i < max; i++) {
                try{
                    int r = q.consume();
                    System.out.println("Item: " + r);
                } catch (KeeperException e){
                    i--;
                } catch (InterruptedException e){

                }
            }
        }
    }
}
