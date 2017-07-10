package com.cxd.curator.sharedLock;

import com.cxd.curator.FakeLimitedResource;
import com.cxd.curator.Locks;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreMutex;

import java.util.concurrent.TimeUnit;

/**
 * Created by childe on 2017/5/16.
 */
public class SharedLocks implements Locks {

    //可重入由馆长提供
    private final InterProcessSemaphoreMutex lock;

    private final FakeLimitedResource resource;
    private final String clientName;

    public SharedLocks(CuratorFramework client, String lockPath, FakeLimitedResource resource, String clientName) {
        this.resource = resource;
        this.clientName = clientName;
        lock = new InterProcessSemaphoreMutex(client, lockPath);
    }

    public void doWork(long time, TimeUnit unit) throws Exception {
        if (!lock.acquire(time, unit)) {
            throw new IllegalStateException(clientName + " could not acquire the lock 1");
        }
        try {
            log.info(clientName + " has the lock");
            resource.use(); //access resource exclusively
        } finally {
            log.info(clientName + " releasing the lock");
            lock.release(); // always release the lock in a finally block
        }
    }
}
