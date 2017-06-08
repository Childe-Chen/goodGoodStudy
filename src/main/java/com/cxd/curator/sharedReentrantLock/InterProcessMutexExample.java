package com.cxd.curator.sharedReentrantLock;

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
    private static final int REPETITIONS = QTY * 1;
    private static final String PATH = "/examples/locks";

    public static void main(String[] args) throws Exception {

        final FakeLimitedResource resource = new FakeLimitedResource();

        ExecutorService service = Executors.newFixedThreadPool(QTY);

        for (int i = 0; i < QTY; ++i) {
            final int index = i;
            Callable<Void> task = new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    CuratorFramework client = CuratorFrameworkFactory.newClient("localhost:2181", new ExponentialBackoffRetry(1000, 3));
                    try {
                        client.start();
                        final ExampleClientThatLocks example = new ExampleClientThatLocks(client, PATH, resource, "Client " + index);
                        for (int j = 0; j < REPETITIONS; ++j) {
                            example.doWork(10, TimeUnit.SECONDS);
                        }
                    } catch (Throwable e) {
                        e.printStackTrace();
                    } finally {
                        CloseableUtils.closeQuietly(client);
                    }
                    return null;
                }
            };
            service.submit(task);
        }
        service.shutdown();
        service.awaitTermination(10, TimeUnit.MINUTES);

    }
}
