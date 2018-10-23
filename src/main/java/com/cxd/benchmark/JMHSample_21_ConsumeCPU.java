package com.cxd.benchmark;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * Consume some amount of time tokens.
 *
 * 消费一些时间令牌。
 *
 * 有时测试消耗一定CPU周期。通过静态的BlackHole.consumeCPU(tokens)方法来实现。
 * Token是一些CPU指令。这样编写方法代码就可以达到运行时间依赖于该参数的目的(不被任何JIT/CPU优化)。
 *
 * @author childe
 * @date 2018/10/13 15:03
 **/
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class JMHSample_21_ConsumeCPU {

    /*
     * At times you require the test to burn some of the cycles doing nothing.
     * In many cases, you *do* want to burn the cycles instead of waiting.
     *
     * For these occasions, we have the infrastructure support. Blackholes
     * can not only consume the values, but also the time! Run this test
     * to get familiar with this part of JMH.
     *
     * (Note we use static method because most of the use cases are deep
     * within the testing code, and propagating blackholes is tedious).
     *
     * 有时你需要测试来烧掉一些无所事事的循环。
     * 在许多情况下，你想要燃烧周期而不是等待。
     *
     * 在这些场合，我们有基础设施支持。
     * Blackhole不仅可以消耗数值，还可以消耗时间！运行此测试以熟悉JMH的这一部分。
     *
     * (注意：我们使用静态方法，因为大多数用例都在测试代码的深处，传播Blackhole很繁琐)
     */

    @Benchmark
    public void consume_0000() {
        Blackhole.consumeCPU(0);
    }

    @Benchmark
    public void consume_0001() {
        Blackhole.consumeCPU(1);
    }

    @Benchmark
    public void consume_0002() {
        Blackhole.consumeCPU(2);
    }

    @Benchmark
    public void consume_0004() {
        Blackhole.consumeCPU(4);
    }

    @Benchmark
    public void consume_0008() {
        Blackhole.consumeCPU(8);
    }

    @Benchmark
    public void consume_0016() {
        Blackhole.consumeCPU(16);
    }

    @Benchmark
    public void consume_0032() {
        Blackhole.consumeCPU(32);
    }

    @Benchmark
    public void consume_0064() {
        Blackhole.consumeCPU(64);
    }

    @Benchmark
    public void consume_0128() {
        Blackhole.consumeCPU(128);
    }

    @Benchmark
    public void consume_0256() {
        Blackhole.consumeCPU(256);
    }

    @Benchmark
    public void consume_0512() {
        Blackhole.consumeCPU(512);
    }

    @Benchmark
    public void consume_1024() {
        Blackhole.consumeCPU(1024);
    }

    /*
     * ============================== HOW TO RUN THIS TEST: ====================================
     *
     * Note the single token is just a few cycles, and the more tokens
     * you request, then more work is spent (almost linearly)
     *
     * 注意：单个token只有几个周期，请求的令牌越多则消耗更多的工作（近乎线性）
     *
     * You can run this test:
     *
     * a) Via the command line:
     *    $ mvn clean install
     *    $ java -jar target/benchmarks.jar JMHSample_21 -f 1
     *    (we requested single fork; there are also other options, see -h)
     *
     * b) Via the Java API:
     *    (see the JMH homepage for possible caveats when running from IDE:
     *      http://openjdk.java.net/projects/code-tools/jmh/)
     */

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JMHSample_21_ConsumeCPU.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }

}