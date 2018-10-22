package com.cxd.benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * 死代码消除解决
 *
 * @author childe
 * @date 2018/10/9 11:44
 **/
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class JMHSample_09_Blackholes {

    /*
     * Should your benchmark require returning multiple results, you have to
     * consider two options (detailed below).
     *
     * NOTE: If you are only producing a single result, it is more readable to
     * use the implicit return, as in JMHSample_08_DeadCode. Do not make your benchmark
     * code less readable with explicit Blackholes!
     *
     * 你的基准测试是否需要返回付哦个测试结果，你需要考虑下民两个问题。
     * 注意：如果你只会产生一个结果，应该使用更易读的明确return，就像JMHSample_08_DeadCode。
     * 不要使用明确的Blackholes来降低您的基准代码的可读性！
     */

    double x1 = Math.PI;
    double x2 = Math.PI * 2;

    /*
     * Baseline measurement: how much single Math.sampleLog costs.
     */

    @Benchmark
    public double baseline() {
        return Math.log(x1);
    }

    /*
     * While the Math.sampleLog(x2) computation is intact（完好）, Math.sampleLog(x1)
     * is redundant and optimized out.
     */

    @Benchmark
    public double measureWrong() {
        Math.log(x1);
        return Math.log(x2);
    }

    /*
     * This demonstrates（演示） Option A:
     *
     * Merge multiple results into one and return it.
     * This is OK when is computation is relatively heavyweight, and merging
     * the results does not offset the results much.
     *
     * 演示选项A：
     * 合并多个结果并返回。
     * 这种方式行得通，但相对较重，并且这个结果不会对结果有太大影响。
     */

    @Benchmark
    public double measureRight_1() {
        return Math.log(x1) + Math.log(x2);
    }

    /*
     * This demonstrates Option B:
     *
     * Use explicit Blackhole objects, and sink the values there.
     * (Background: Blackhole is just another @State object, bundled with JMH).
     *
     * 演示选项B：
     * 明确的使用Blackhole对象，并且把值汇入。
     * （背后：Blackhole就像另一个@State对象，绑定在JMH）。
     */

    @Benchmark
    public void measureRight_2(Blackhole bh) {
        bh.consume(Math.log(x1));
        bh.consume(Math.log(x2));
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JMHSample_09_Blackholes.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}