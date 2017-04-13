package com.cxd.jvm.references.ref;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * -Xms4m -Xmx4m -XX:+PrintGCDetails -Xloggc:/Users/childe/logs/gc-f.log
 * -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/Users/childe/logs/oom-f.hprof
 * Created by childe on 2017/3/31.
 */
public class Finalizable {
    static AtomicInteger aliveCount = new AtomicInteger(0);

    Finalizable() {
        aliveCount.incrementAndGet();
    }

    @Override
    protected void finalize() throws Throwable {
        Finalizable.aliveCount.decrementAndGet();
    }

    public static void main(String args[]) {
        for (int i = 0;; i++) {
            Finalizable f = new Finalizable();
            if ((i % 100_000) == 0) {
                System.out.format("After creating %d objects, %d are still alive.%n", new Object[] {i, Finalizable.aliveCount.get() });
            }
        }
    }
}
