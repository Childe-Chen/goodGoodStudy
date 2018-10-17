package com.cxd.benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * desc
 *
 * @author childe
 * @date 2018/10/17 15:31
 **/
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Group)
@Timeout(time = 10)
public class JMHSample_30_Interrupts {

    /*
     * JMH can also detect when threads are stuck in the benchmarks, and try
     * to forcefully interrupt the benchmark thread. JMH tries to do that
     * when it is arguably sure it would not affect the measurement.
     *
     * JMH还可以检测线程何时卡在基准测试中，并尝试强制中断基准线程。
     * 在可以确定它不会影响测量时，JMH会尝试这样做。
     */

    /*
     * In this example, we want to measure the simple performance characteristics
     * of the ArrayBlockingQueue. Unfortunately, doing that without a harness
     * support will deadlock one of the threads, because the executions of
     * take/put are not paired perfectly. Fortunately for us, both methods react
     * to interrupts well, and therefore we can rely on JMH to terminate the
     * measurement for us. JMH will notify users about the interrupt actions
     * nevertheless, so users can see if those interrupts affected the measurement.
     * JMH will start issuing interrupts after the default or user-specified timeout
     * had been reached.
     *
     * This is a variant of org.openjdk.jmh.samples.JMHSample_18_Control, but without
     * the explicit control objects. This example is suitable for the methods which
     * react to interrupts gracefully.
     *
     * 在这个例子中，我们想测量ArrayBlockingQueue的简单性能特征。
     * 不幸的是，在没有工具支持的情况下执行此操作会使其中一个线程死锁，因为take / put的执行不能完美配对。
     * 幸运的是，这两种方法都能很好地应对中断，因此我们可以依赖JMH来中断测量。
     * JMH将通知用户有关中断操作的信息，因此用户可以查看这些中断是否会影响测量。
     * 在达到默认或用户指定的超时后，JMH将开始发出中断。
     *
     * 这是 {@link com.cxd.benchmark.JMHSample_18_Control}的一个变种，但是没有明确的控制对象。
     * 这个例子很适合那些需要优雅应对中断的方法。
     */

    private BlockingQueue<Integer> q;

    @Setup
    public void setup() {
        q = new ArrayBlockingQueue<>(1);
    }

    @Group("Q")
    @Benchmark
    public Integer take() throws InterruptedException {
        return q.take();
    }

    @Group("Q")
    @Benchmark
    public void put() throws InterruptedException {
        q.put(42);
    }

    /*
     * ============================== HOW TO RUN THIS TEST: ====================================
     *
     * You can run this test:
     *
     * a) Via the command line:
     *    $ mvn clean install
     *    $ java -jar target/benchmarks.jar JMHSample_30 -t 2 -f 5 -to 10
     *    (we requested 2 threads, 5 forks, and 10 sec timeout)
     *
     * b) Via the Java API:
     *    (see the JMH homepage for possible caveats when running from IDE:
     *      http://openjdk.java.net/projects/code-tools/jmh/)
     */

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JMHSample_30_Interrupts.class.getSimpleName())
                .threads(2)
                .forks(5)
//                .timeout(TimeValue.seconds(10))
                .output("JMHSample_30_Interrupts_annotation.log")
                .build();

        new Runner(opt).run();
    }

}