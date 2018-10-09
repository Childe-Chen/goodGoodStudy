package com.cxd.benchmark;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 *
 * @see org.openjdk.jmh.annotations.Scope
 *
 * @author childe
 * @date 2018/9/20 16:02
 **/
public class JMHSample_03_States {

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JMHSample_03_States.class.getSimpleName())
                .output("JMHSample_03_States_result.log")
                .threads(4)
                .forks(1)
                .build();

        new Runner(opt).run();
    }

    /*
     * 有些情况下，你需要在基准测试执行时维护一些状态。因为JHM常常用来构建并发基准测试，
     * 我们选择了一个明确的state-bearing（状态支撑）对象概念。
     *
     * 下面是两个状态对象。他们的类名不是必需的，只是我们需要在类上标记@State。
     * <b>这些对象在需要时被创建，在整个基准试验期间重复使用。</b>
     *
     * 这个重要的状态属性始终由其中一个基准线程实例化，然后可以访问该状态。
     * 这意味着你可以初始化字段，就像在工作线程中那样（ThreadLocals是你的，等等）
     *
     */

    @State(Scope.Benchmark)
    public static class BenchmarkState {
        volatile double x = Math.PI;
    }

    @State(Scope.Thread)
    public static class ThreadState {
        volatile double x = Math.PI;
    }

    /*
     * Benchmark方法可以引用这个状态，JHM在调用这些方法时会注入适当的状态。
     * 你可以没有状态，或者只有一个，又或者引用多个。
     * 这使你构建一个多线程的基准测试轻而易举。
     */

    @Benchmark
    public void measureUnshared(ThreadState state) {
        // All benchmark threads will call in this method.
        //
        // However, since ThreadState is the Scope.Thread, each thread
        // will have it's own copy of the state, and this benchmark
        // will measure unshared case.
        state.x++;
    }

    @Benchmark
    public void measureShared(BenchmarkState state) {
        // All benchmark threads will call in this method.
        //
        // Since BenchmarkState is the Scope.Benchmark, all threads
        // will share the state instance, and we will end up measuring
        // shared case.
        state.x++;
    }

}