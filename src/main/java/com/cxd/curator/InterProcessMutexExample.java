package com.cxd.curator;

import com.cxd.curator.FakeLimitedResource;
import com.cxd.curator.reentrantReadWriteLock.ReadWriteReentrantLocks;
import com.cxd.curator.sharedReentrantLock.ReentrantLocks;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by childe on 2017/5/16.
 */
public class InterProcessMutexExample {
    private static final int QTY = 1;
    private static final int REPETITIONS = 1;
    private static final String PATH = "/examples/locks";

    public static void main(String[] args) throws Exception {

        final FakeLimitedResource resource = new FakeLimitedResource();

        ExecutorService service = Executors.newFixedThreadPool(QTY);

        for (int i = 0; i < QTY; ++i) {
            final int index = i;

            CuratorFramework client = CuratorFrameworkFactory.newClient("localhost:2181", new ExponentialBackoffRetry(3000, 3));
//            final Locks example = new ReentrantLocks(client, PATH, resource, "Client " + index);
            final Locks example = new ReadWriteReentrantLocks(client, PATH, resource, "Client " + index);

            service.submit(()->{
                try {
                    client.start();
                    for (int j = 0; j < REPETITIONS; ++j) {
                        example.doWork(10, TimeUnit.SECONDS);
                    }
                } catch (Throwable e) {
                    e.printStackTrace();
                } finally {
                    CloseableUtils.closeQuietly(client);
                }
            });
        }
        service.shutdown();
        service.awaitTermination(10, TimeUnit.MINUTES);

    }
}
