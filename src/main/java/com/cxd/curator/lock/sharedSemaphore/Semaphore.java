package com.cxd.curator.lock.sharedSemaphore;

import com.cxd.curator.lock.Locks;
import com.google.common.util.concurrent.MoreExecutors;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreV2;
import org.apache.curator.framework.recipes.locks.Lease;
import org.apache.curator.framework.recipes.shared.SharedCount;
import org.apache.curator.framework.recipes.shared.SharedCountListener;
import org.apache.curator.framework.recipes.shared.SharedCountReader;
import org.apache.curator.framework.state.ConnectionState;

import java.util.Collection;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by childe on 2017/7/10.
 */
public class Semaphore implements Locks {

    InterProcessSemaphoreV2 semaphore;

    String clientName;

    Random random = new Random();

    SharedCount sharedCount;

    public Semaphore(CuratorFramework client, String lockPath, String clientName,int maxLeases) {
//        this.semaphore = new InterProcessSemaphoreV2(client,lockPath,maxLeases);
        sharedCount = new SharedCount(client,lockPath,maxLeases);
        this.semaphore = new InterProcessSemaphoreV2(client,lockPath,sharedCount);
        this.clientName = clientName;
        sharedCount.addListener(new SharedCountListener(){
            @Override
            public void countHasChanged(SharedCountReader sharedCount, int newCount) throws Exception {
                log.info("--------");
                log.info("{} newCount : {}, sharedCount.getCount() : {}, sharedCount.getVersionedValue().getValue() : {}, sharedCount.getVersionedValue().getVersion() : {}",clientName,newCount, sharedCount.getCount(),
                        sharedCount.getVersionedValue().getValue(),sharedCount.getVersionedValue().getVersion());
                log.info("--------");
            }

            @Override
            public void stateChanged(CuratorFramework client, ConnectionState newState) {
                log.info("--------");
                log.info("{} newState.name() : {}, isConnected : {}",clientName,newState.name(),newState.isConnected());
                log.info("--------");
            }
        }, MoreExecutors.directExecutor());

    }

    @Override
    public void doWork(long time, TimeUnit unit) {
        Collection<Lease> leasesWork = null;
        Collection<Lease> leasesClear = null;
        try {
            leasesWork = semaphore.acquire(5);
            log.info("{} acquire get {} leasesWork", clientName, leasesWork.size());

            Thread.sleep(1000 * random.nextInt(3));

            try {
                sharedCount.start();
                int count = random.nextInt(10) + 2;
                sharedCount.setCount(count);
                log.info("{} setCount {}",clientName, count);
            } catch (Exception e) {
                log.error(e.getMessage(),e);
            }

            leasesClear = semaphore.acquire(2,2, TimeUnit.SECONDS);
//            leasesClear = semaphore.acquire(2);
            if (leasesClear != null) {
                log.info("{} acquire get {} leasesClear", clientName, leasesClear.size());
            } else {
                log.info("{} acquire can't get leasesClear", clientName);
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        } finally {
            if (leasesWork != null) {
                semaphore.returnAll(leasesWork);
            }
            if (leasesClear != null) {
                semaphore.returnAll(leasesClear);
            }
            log.info("{} now leases {}",clientName, sharedCount.getCount());
        }

    }
}
