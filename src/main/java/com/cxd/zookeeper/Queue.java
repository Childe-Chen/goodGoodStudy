package com.cxd.zookeeper;

/**
 * Created by childe on 2017/5/2.
 */

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;

import java.nio.ByteBuffer;
import java.util.List;

/**
 * Producer-Consumer queue
 */
public class Queue extends SyncPrimitive {

    /**
     * Constructor of producer-consumer queue
     *
     * @param address
     * @param name
     */
    Queue(String address, String name) {
        super(address);
        this.root = name;
        // Create ZK node name
        if (zk != null) {
            try {
                Stat s = zk.exists(root, false);
                if (s == null) {
                    zk.create(root, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE,
                            CreateMode.PERSISTENT);
                }
            } catch (KeeperException e) {
                System.out.println("Keeper exception when instantiating queue: "
                                + e.toString());
            } catch (InterruptedException e) {
                System.out.println("Interrupted exception");
            }
        }
    }

    /**
     * Add element to the queue.
     *
     * @param i
     * @return
     */

    boolean produce(int i) throws KeeperException, InterruptedException{
        ByteBuffer b = ByteBuffer.allocate(4);
        byte[] value;

        // Add child with value i
        b.putInt(i);
        value = b.array();
        zk.create(root + "/element", value, ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT_SEQUENTIAL);

        return true;
    }


    /**
     * Remove first element from the queue.
     *
     * @return
     * @throws KeeperException
     * @throws InterruptedException
     */
    int consume() throws KeeperException, InterruptedException{
        int retvalue = -1;
        Stat stat = null;

        // Get the first element available
        while (true) {
            synchronized (mutex) {
                List<String> list = zk.getChildren(root, true);
                if (list.size() == 0) {
                    System.out.println("Going to wait");
                    mutex.wait();
                } else {
                    Integer min = new Integer(list.get(0).substring(7));
                    for(String s : list){
                        Integer tempValue = new Integer(s.substring(7));
                        //System.out.println("Temporary value: " + tempValue);
                        if(tempValue < min) min = tempValue;
                    }
                    System.out.println("Temporary value: " + root + "/element" + min);
                    byte[] b = zk.getData(root + "/element" + min,
                            false, stat);
                    zk.delete(root + "/element" + min, 0);
                    ByteBuffer buffer = ByteBuffer.wrap(b);
                    retvalue = buffer.getInt();

                    return retvalue;
                }
            }
        }
    }
}
