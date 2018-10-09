package com.cxd.benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * 基准测试方法中进行循环是坏做法
 *
 * @author childe
 * @date 2018/10/9 15:51
 **/
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class JMHSample_11_Loops {

    /*
     * It would be tempting for users to do loops within the benchmarked method.
     * (This is the bad thing Caliper taught everyone). These tests explain why
     * this is a bad idea.
     *
     * Looping is done in the hope of minimizing the overhead of calling the
     * test method, by doing the operations inside the loop instead of inside
     * the method call. Don't buy this argument; you will see there is more
     * magic happening when we allow optimizers to merge the loop iterations.
     *
     * 在基准测试方法中做循环是很诱人的。这是Caliper教给大家的坏事情。以下测试告诉你原因。
     *
     * 循环是为了通过在循环内而不是在方法调用内部进行操作来最小化调用测试方法的开销。
     * 不要买这个论点;当我们允许优化器合并循环迭代时，你会发现有更多的魔法发生。
     */

    /*
     * Suppose we want to measure how much it takes to sum two integers:
     */

    int x = 1;
    int y = 2;

    /*
     * This is what you do with JMH.
     */

    @Benchmark
    public int measureRight() {
        return (x + y);
    }

    /*
     * The following tests emulate the naive looping.
     * This is the Caliper-style benchmark.
     *
     * Caliper风格的基准测试
     */

    private int reps(int reps) {
        int s = 0;
        for (int i = 0; i < reps; i++) {
            s += (x + y);
        }
        return s;
    }

    /*
     * We would like to measure this with different repetitions count.
     * Special annotation is used to get the individual operation cost.
     */

    @Benchmark
    @OperationsPerInvocation(1)
    public int measureWrong_1() {
        return reps(1);
    }

    @Benchmark
    @OperationsPerInvocation(10)
    public int measureWrong_10() {
        return reps(10);
    }

    @Benchmark
    @OperationsPerInvocation(100)
    public int measureWrong_100() {
        return reps(100);
    }

    @Benchmark
    @OperationsPerInvocation(1000)
    public int measureWrong_1000() {
        return reps(1000);
    }

    @Benchmark
    @OperationsPerInvocation(10000)
    public int measureWrong_10000() {
        return reps(10000);
    }

    @Benchmark
    @OperationsPerInvocation(100000)
    public int measureWrong_100000() {
        return reps(100000);
    }

    /*
     * You might notice the larger the repetitions count, the lower the "perceived"
     * cost of the operation being measured. Up to the point we do each addition with 1/20 ns,
     * well beyond what hardware can actually do.
     *
     * This happens because the loop is heavily unrolled/pipelined, and the operation
     * to be measured is hoisted from the loop. Morale: don't overuse loops, rely on JMH
     * to get the measurement right.
     *
     * 你可能已经注意到，循环次数阅读，统计出来的时间越短。到目前位置，每次操作的时间已经是1/20 ns，远远超过硬件的实际能力。
     *
     * 发生这种情况是因为循环被大量unrolled/pipelined，并且要从循环中提升要测量的操作。规则：不要过度使用循环，依靠JMH来正确测量。
     */

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JMHSample_11_Loops.class.getSimpleName())
                .forks(1)
                .output("JMHSample_11_Loops.log")
                .build();

        new Runner(opt).run();
    }

}
