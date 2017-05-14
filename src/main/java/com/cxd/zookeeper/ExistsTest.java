package com.cxd.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

/**
 * Created by childe on 2017/5/9.
 */
public class ExistsTest implements Watcher {

    static CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) throws Exception{
        ZooKeeper zk = new ZooKeeper(SyncPrimitive.address, 3000, new ExistsTest());
        zk.exists("/b1/e",true);
        latch.await();
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println(event.toString());
        if (event.getState().getIntValue() == Event.KeeperState.SyncConnected.getIntValue()) {
            return;
        }
        latch.countDown();
        System.out.println(event.toString() + "  latch" + latch.getCount());
    }
}
