package com.cxd.disruptor;

/**
 * https://lixiangyun.gitbooks.io/disruptor/content/1.3.html
 *
 * @author childe
 * @date 2018/8/15 17:56
 **/
public final class FalseSharing implements Runnable {
    /**
     * 每改变一次，要重新编译一次，不然抛出的时间差别不准确
     */
    public final static int NUM_THREADS = 1;
    public final static long ITERATIONS = 500L * 1000L * 1000L;
    private final int arrayIndex;

    private static VolatileLong[] longs = new VolatileLong[NUM_THREADS];
    static {
        for (int i = 0; i < longs.length; i++) {
            longs[i] = new VolatileLong();
        }
    }

    public FalseSharing(final int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }

    public static void main(final String[] args) throws Exception {
        final long start = System.nanoTime();
        runTest();
        System.out.println("duration = " + (System.nanoTime() - start));
    }

    private static void runTest() throws InterruptedException {
        Thread[] threads = new Thread[NUM_THREADS];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new FalseSharing(i));
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }
    }

    @Override
    public void run() {
        long i = ITERATIONS + 1;
        while (0 != --i) {
            longs[arrayIndex].value = i;
        }
    }

    /**
     * 从不断上升的测试所需时间中能够明显看出伪共享的影响。没有缓存行竞争时，我们几近达到了随着线程数的线性扩展。
     * 这并不是个完美的测试，因为我们不能确定这些VolatileLong会布局在内存的什么位置。它们是独立的对象。
     * 但是经验告诉我们同一时间分配的对象趋向集中于一块。
     * 所以你也看到了，伪共享可能是无声的性能杀手。
     */
    public final static class VolatileLong {
        // 8byte
        public volatile long value = 0L;
        // 40byte comment out
        // 0. NUM_THREADS = 1 duration = 4587.402.329
        // 1. NUM_THREADS = 2 duration = 5249.820.613
        // 2. NUM_THREADS = 3 duration = 6329.667.147
        // 3. NUM_THREADS = 4 duration = 7743.024.530
        public long p1, p2, p3, p4, p5;

        // 无填充时
        // 0. NUM_THREADS = 1 duration =  8213.558.562
        // 0. NUM_THREADS = 2 duration = 15874.759.369
        // 0. NUM_THREADS = 3 duration = 27350.752.730
        // 0. NUM_THREADS = 4 duration = 27435.461.116
    }
}
