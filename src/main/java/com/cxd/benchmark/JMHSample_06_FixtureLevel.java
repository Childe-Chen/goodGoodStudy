package com.cxd.benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * @see org.openjdk.jmh.annotations.Level
 * @see org.openjdk.jmh.annotations.Setup
 * @see org.openjdk.jmh.annotations.TearDown
 *
 * @author childe
 * @date 2018/10/8 15:36
 **/
@State(Scope.Thread)
public class JMHSample_06_FixtureLevel {

    private double x;

    /*
     * Fixture methods have different levels to control when they should be run.
     * There are at least three Levels available to the user. These are, from
     * top to bottom:
     *
     * Level.Trial: before or after the entire benchmark run (the sequence of iterations)
     * Level.Iteration: before or after the benchmark iteration (the sequence of invocations)
     * Level.Invocation; before or after the benchmark method invocation (WARNING: read the Javadoc before using)
     *
     * Time spent in fixture methods does not count into the performance
     * metrics, so you can use this to do some heavy-lifting.
     *
     * fixture方法在运行时有几个不同的等级。
     * 一共有三个等级供我们使用：
     *
     * Level.Trial：整个基准测试（一个@Benchmark注解为一个基准测试）运行之前或之后（迭代序列）
     * Level.Iteration：在基准迭代之前或之后（调用序列）
     * Level.Invocation：该等级表现比较复杂 @see org.openjdk.jmh.annotations.Level
     *
     * fixture方法的耗时不会被统计进性能指标，所以可以用它来做一些比较重的操作。
     */

    @TearDown(Level.Iteration)
    public void check() {
        assert x > Math.PI : "Nothing changed?";
    }

    @Benchmark
    public void measureRight() {
        x++;
    }

    @Benchmark
    public void measureWrong() {
        double x = 0;
        x++;
    }

    /*
     * java -ea //启动断言
     * java -ea:pkname... //在包pkname及其子包下起用断言
     * java -ea:pkname.classname //对类 pkname.classname启用断言
     * 停用断言与启用设置类似
     */

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JMHSample_06_FixtureLevel.class.getSimpleName())
                .forks(1)
                .jvmArgs("-ea")
                // switch to "true" to fail the complete run
                .shouldFailOnError(false)
                .output("JMHSample_06_FixtureLevel.sampleLog")
                .build();

        new Runner(opt).run();
    }
}