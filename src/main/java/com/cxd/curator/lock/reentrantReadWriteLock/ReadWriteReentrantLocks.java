package com.cxd.curator.lock.reentrantReadWriteLock;

import com.cxd.curator.lock.FakeLimitedResource;
import com.cxd.curator.lock.Locks;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;

import java.util.concurrent.TimeUnit;

/**
 * Created by childe on 2017/7/10.
 */
public class ReadWriteReentrantLocks implements Locks {


    private final InterProcessReadWriteLock lock;
    private final InterProcessMutex readLock;
    private final InterProcessMutex writeLock;
    private final FakeLimitedResource resource;
    private final String clientName;

    public ReadWriteReentrantLocks(CuratorFramework client, String lockPath, FakeLimitedResource resource, String clientName) {
        this.resource = resource;
        this.clientName = clientName;
        lock = new InterProcessReadWriteLock(client, lockPath);
        readLock = lock.readLock();
        writeLock = lock.writeLock();
    }

    public void doWork(long time, TimeUnit unit) throws Exception {
        //
        if (!writeLock.acquire(time, unit)) {
            throw new IllegalStateException(clientName + " could not acquire the writeLock");
        }
        log.info(clientName + " has the writeLock");

        if (!readLock.acquire(time, unit)) {
            throw new IllegalStateException(clientName + " could not acquire the readLock");
        }
        log.info(clientName + " has the readLock too");

        //如果一开始申请的是读锁，此处会抛出无法获得写锁的异常
//        if (!writeLock.acquire(time, unit)) {
//            throw new IllegalStateException(clientName + " could not acquire the writeLock");
//        }
//        sampleLog.info(clientName + " has the writeLock");

        try {
            resource.use(); //access resource exclusively
        } finally {
            readLock.release(); // always release the lock in a finally block
            writeLock.release(); // always release the lock in a finally block
            log.info(clientName + " releasing the lock");
        }
    }
}
