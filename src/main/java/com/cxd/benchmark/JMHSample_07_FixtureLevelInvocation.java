package com.cxd.benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.*;

/**
 * Fixtures have different Levels to control when they are about to run.
 * Level.Invocation is useful sometimes to do some per-invocation work
 * which should not count as payload (e.g. sleep for some time to emulate
 * think time)
 *
 * 在他们运行的时候fixture有不同的等级进行控制。
 * Level.Invocation一些情况下很有用，比如：每次调用前做一些工作，并且这些工作不会被统计为有效载荷。
 *
 * @author childe
 * @date 2018/10/8 17:13
 **/
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class JMHSample_07_FixtureLevelInvocation {

    /*
     * Fixtures have different Levels to control when they are about to run.
     * Level.Invocation is useful sometimes to do some per-invocation work,
     * which should not count as payload. PLEASE NOTE the timestamping and
     * synchronization for Level.Invocation helpers might significantly offset
     * the measurement, use with care. See Level.Invocation javadoc for further
     * discussion.
     *
     * 在他们运行的时候fixture有不同的等级进行控制。
     * Level.Invocation一些情况下很有用，比如：每次调用前做一些工作，并且这些工作不会被统计为有效载荷。
     * 请注意Level.Invocation助手的时间戳和同步可能会显着抵消测量，请谨慎使用。
     *
     * Consider this sample:
     * 看下面的例子：
     */

    /*
     * This state handles the executor.
     * Note we create and shutdown executor with Level.Trial, so
     * it is kept around the same across all iterations.
     */

    @State(Scope.Benchmark)
    public static class NormalState {
        ExecutorService service;

        @Setup(Level.Trial)
        public void up() {
            System.out.println("up");
            service = Executors.newCachedThreadPool();
        }

        @TearDown(Level.Trial)
        public void down() {
            service.shutdown();
        }

    }

    /*
     * This is the *extension* of the basic state, which also
     * has the Level.Invocation fixture method, sleeping for some time.
     *
     * @State 是可继承的
     */

    public static class LaggingState extends NormalState {
        static final int SLEEP_TIME = Integer.getInteger("sleepTime", 10);

        @Setup(Level.Invocation)
        public void lag() throws InterruptedException {
            TimeUnit.MILLISECONDS.sleep(SLEEP_TIME);
        }
    }

    /*
     * This allows us to formulate the task: measure the task turnaround in
     * "hot" mode when we are not sleeping between the submits, and "cold" mode,
     * when we are sleeping.
     */

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public double measureHot(NormalState e, final Scratch s) throws ExecutionException, InterruptedException {
        return e.service.submit(new Task(s)).get();
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public double measureCold(LaggingState e, final Scratch s) throws ExecutionException, InterruptedException {
        return e.service.submit(new Task(s)).get();
    }

    /*
     * This is our scratch state which will handle the work.
     */

    @State(Scope.Thread)
    public static class Scratch {

        private double p;

        private double doWork() {
            p = Math.log(p);
            return p;
        }
    }

    public static class Task implements Callable<Double> {
        private Scratch s;

        private Task(Scratch s) {
            this.s = s;
        }

        @Override
        public Double call() {
            return s.doWork();
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JMHSample_07_FixtureLevelInvocation.class.getSimpleName())
                .forks(1)
                .output("JMHSample_07_FixtureLevelInvocation.log")
                .build();

        new Runner(opt).run();
    }

}