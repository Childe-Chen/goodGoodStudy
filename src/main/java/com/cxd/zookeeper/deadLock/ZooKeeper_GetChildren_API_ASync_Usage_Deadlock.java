package com.cxd.zookeeper.deadLock;

/**
 * Created by childe on 2017/5/4.
 */
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.Watcher.Event.KeeperState;

/**
 * ZooKeeper API 获取子节点列表，使用异步(ASync)接口。
 * @author [银时](mailto:nileader@gmail.com)
 * 死锁原因:
 * 在ZooKeeper客户端中，需要处理来自服务端的两类事件通知：
 * 一类是Watches时间通知，另一类则是异步接口调用的响应。
 * 值得一提的是，在ZooKeeper的客户端线程模型中，这两个事件由同一个线程处理，并且是串行处理。
 * 具体可以自己查看事件处理的核心类：org.apache.zookeeper.ClientCnxn.EventThread$EventThread。
 */
public class ZooKeeper_GetChildren_API_ASync_Usage_Deadlock implements Watcher {

    private CountDownLatch connectedSemaphore = new CountDownLatch( 1 );
    private static CountDownLatch _semaphore = new CountDownLatch( 2 );
    private ZooKeeper zk;

    ZooKeeper createSession( String connectString, int sessionTimeout, Watcher watcher ) throws IOException {
        ZooKeeper zookeeper = new ZooKeeper( connectString, sessionTimeout, watcher );
        try {
            connectedSemaphore.await();
        } catch ( InterruptedException e ) {
        }
        return zookeeper;
    }

    /** create path by sync */
    void createPath_sync( String path, String data, CreateMode createMode ) throws IOException, KeeperException, InterruptedException {

        if ( zk == null ) {
            zk = this.createSession( "localhost:2181", 5000, this );
        }
        zk.create( path, data.getBytes(), Ids.OPEN_ACL_UNSAFE, createMode );
    }

    /** Get children znodes of path and set watches */
    void getChildren( String path ) throws KeeperException, InterruptedException, IOException{

        System.out.println(Thread.currentThread().getId() + "===Start to get children znodes.===" );
        if ( zk == null ) {
            zk = this.createSession( "localhost:2181", 5000, this );
        }

//        final CountDownLatch _semaphore_get_children = new CountDownLatch( 1 );

        zk.getChildren( path, true, new AsyncCallback.Children2Callback() {
            @Override
            public void processResult( int rc, String path, Object ctx, List children, Stat stat ) {

                System.out.println(Thread.currentThread().getId() +  "Get Children znode result: [response code: " + rc + ", param path: " + path + ", ctx: " + ctx + ", children list: "
                        + children + ", stat: " + stat );
//                _semaphore_get_children.countDown();
                _semaphore.countDown();
            }
        }, null);
//        _semaphore_get_children.await();
    }

    /**
     * console print:

     * @param args
     * @throws IOException
     * @throws InterruptedException
     */
    public static void main( String[] args ) throws IOException, InterruptedException {

        ZooKeeper_GetChildren_API_ASync_Usage_Deadlock sample = new ZooKeeper_GetChildren_API_ASync_Usage_Deadlock();
        String path = "/get_children_test";

        try {
            sample.createPath_sync( path, "", CreateMode.PERSISTENT );
            sample.createPath_sync( path + "/c1", "", CreateMode.PERSISTENT );
            //Get children and register watches.
            sample.getChildren( path );
            //Add a new child znode to test watches event notify.
            sample.createPath_sync( path + "/c2", "", CreateMode.PERSISTENT );

            _semaphore.await();
        } catch ( KeeperException e ) {
            System.err.println( "error: " + e.getMessage() );
            e.printStackTrace();
        }
    }

    /**
     * Process when receive watched event
     */
    @Override
    public void process( WatchedEvent event ) {
        System.out.println( "Receive watched event：" + event );
        if ( KeeperState.SyncConnected == event.getState() ) {

            if( EventType.None == event.getType() && null == event.getPath() ){
                connectedSemaphore.countDown();
            }else if( event.getType() == EventType.NodeChildrenChanged ){
                //children list changed
                try {
                    this.getChildren( event.getPath() );

                } catch ( Exception e ) {
                    e.printStackTrace();
                }
            }

        }
    }
}
