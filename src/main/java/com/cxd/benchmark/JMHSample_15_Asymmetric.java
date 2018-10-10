package com.cxd.benchmark;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Group;
import org.openjdk.jmh.annotations.GroupThreads;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 非对称分组执行
 *
 * 例子场景：生产者/消费者
 *
 * @author childe
 * @date 2018/10/10 10:40
 **/
@State(Scope.Group)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class JMHSample_15_Asymmetric {

    /*
     * So far all the tests were symmetric: the same code was executed in all the threads.
     * At times, you need the asymmetric test. JMH provides this with the notion of @Group,
     * which can bind several methods together, and all the threads are distributed among
     * the test methods.
     *
     * Each execution group contains of one or more threads. Each thread within a particular
     * execution group executes one of @Group-annotated @Benchmark methods. Multiple execution
     * groups may participate in the run. The total thread count in the run is rounded to the
     * execution group size, which will only allow the full execution groups.
     *
     * Note that two state scopes: Scope.Benchmark and Scope.Thread are not covering all
     * the use cases here -- you either share everything in the state, or share nothing.
     * To break this, we have the middle ground Scope.Group, which marks the state to be
     * shared within the execution group, but not among the execution groups.
     *
     * Putting this all together, the example below means:
     *  a) define the execution group "g", with 3 threads executing inc(), and 1 thread
     *     executing get(), 4 threads per group in total;
     *  b) if we run this test case with 4 threads, then we will have a single execution
     *     group. Generally, running with 4*N threads will create N execution groups, etc.;
     *  c) each execution group has one @State instance to share: that is, execution groups
     *     share the counter within the group, but not across the groups.
     *
     * 到目前位置，我们的测试都是对称的：所有的线程都运行相同的代码。
     * 是时候了解非对称测试了。JMH提供了@Group注解来把几个方法绑定到一起，所有线程都分布在测试方法中。
     *
     * 每个执行组包含一个或者多个线程。特定执行组中的每个线程执行@Group-annotated @Benchmark方法之一。
     * 多个执行组可以参与运行。运行中的总线程数将四舍五入为执行组大小，这将只允许完整的执行组。？？？
     *
     * 注意那两个状态的作用域：Scope.Benchmark 和 Scope.Thread没有在这个用例中覆盖 -- 你要么在状态中共享每个东西，要么不共享。
     * 我们使用Scope.Group状态用来表明在执行组内共享，而不在组间共享。
     *
     * 以下事例含义：
     *  a)定义执行组"g"，它有3个线程来执行inc()，1个线程来执行get()，每个分组共有4个线程；
     *  b)如果我们用4个线程来运行这个测试用例，我们将会有一个单独的执行组。通常，用4*N个线程来创建N个执行组。
     *  c)每个执行组内共享一个@State实例：也就是执行组内共享counter，而不是跨组共享。
     */

    private AtomicInteger counter;

    @Setup
    public void up() {
        counter = new AtomicInteger();
    }

    @Benchmark
    @Group("g")
    @GroupThreads(3)
    public int inc() {
        return counter.incrementAndGet();
    }

    @Benchmark
    @Group("g")
    @GroupThreads(1)
    public int get() {
        return counter.get();
    }

    /*
     * ============================== HOW TO RUN THIS TEST: ====================================
     *
     * You will have the distinct metrics for inc() and get() from this run.
     *
     * 在此次运行中我们讲分别获得inc()和get()的指标。
     *
     * You can run this test:
     *
     * a) Via the command line:
     *    $ mvn clean install
     *    $ java -jar target/benchmarks.jar JMHSample_15 -f 1
     *    (we requested single fork; there are also other options, see -h)
     *
     * b) Via the Java API:
     *    (see the JMH homepage for possible caveats when running from IDE:
     *      http://openjdk.java.net/projects/code-tools/jmh/)
     */

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JMHSample_15_Asymmetric.class.getSimpleName())
                .forks(1)
                .output("JMHSample_15_Asymmetric.log")
                .build();

        new Runner(opt).run();
    }

}