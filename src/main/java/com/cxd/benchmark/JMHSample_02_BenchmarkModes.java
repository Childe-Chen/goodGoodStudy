package com.cxd.benchmark;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * 基准测试-模式：
 *
 * @see org.openjdk.jmh.annotations.Mode
 *
 * @author childe
 * @date 2018/9/19 11:29
 **/
public class JMHSample_02_BenchmarkModes {

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(JMHSample_02_BenchmarkModes.class.getSimpleName())
                .exclude(JMHSample_02_BenchmarkModes.class.getSimpleName() + ".measureAll")
                .exclude(JMHSample_02_BenchmarkModes.class.getSimpleName() + ".measureMultiple")
                .output("JMHSample_02_BenchmarkModes_result.sampleLog")
                .forks(1)
                .build();

        new Runner(options).run();
    }

    /*
     * JMH generates lots of synthetic code for the benchmarks for you during
     * the benchmark compilation. JMH can measure the benchmark methods in lots
     * of modes. Users may select the default benchmark mode with a special
     * annotation, or select/override the mode via the runtime options.
     *
     * With this scenario, we start to measure something useful. Note that our
     * payload code potentially throws exceptions, and we can just declare them
     * to be thrown. If the code throws the actual exception, the benchmark
     * execution will stop with an error.
     *
     * When you are puzzled with some particular behavior, it usually helps to
     * look into the generated code. You might see the code is doing not
     * something you intend it to do. Good experiments always follow up on the
     * experimental setup, and cross-checking the generated code is an important
     * part of that follow up.
     *
     * The generated code for this particular sample is somewhere at
     * target/generated-sources/annotations/.../JMHSample_02_BenchmarkModes.java
     */

    /**
     * <p>Throughput: 每单位时间的操作 ops/time</p>
     *
     * <p>持续调用标记有{@link Benchmark}的方法，计算所有工作线程的总吞吐量。
     * 这个模式基于时间，一致运行到迭代时间结束</p>
     *
     * Mode.Throughput, as stated in its Javadoc, measures the raw throughput by
     * continuously calling the benchmark method in a time-bound iteration, and
     * counting how many times we executed the method.
     *
     * We are using the special annotation to select the units to measure in,
     * although you can use the default.
     */
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public static void measureThroughput() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(100);
    }

    /*
     * Mode.AverageTime measures the average execution time, and it does it
     * in the way similar to Mode.Throughput.
     *
     * Some might say it is the reciprocal throughput, and it really is.
     * There are workloads where measuring times is more convenient though.
     */

    /**
     * <p>Average time: 每次操作的平均时间 time/op</p>
     *
     * <p>持续调用标记有{@link Benchmark}的方法，计算调用所有工作线程的平均时间。这个是{@link Mode#Throughput}的倒数，
     * 采用不同的聚合政策。这个模式基于时间，一直运行到迭代时间结束</p>
     */
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public static void measureAverageTime() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(100);
    }

    /*
     * Mode.SampleTime samples the execution time. With this mode, we are
     * still running the method in a time-bound iteration, but instead of
     * measuring the total time, we measure the time spent in *some* of
     * the benchmark method calls.
     *
     * This allows us to infer the distributions, percentiles, etc.
     *
     * JMH also tries to auto-adjust sampling frequency: if the method
     * is long enough, you will end up capturing all the samples.
     */

    /**
     * <p>Sample time: 对每次操作的时间进行采样 Sampling time</p>
     *
     * <p>This mode automatically adjusts the sampling
     * frequency, but may omit some pauses which missed the sampling measurement. </p>
     * <p>
     *     持续调用标记有{@link Benchmark}的方法，随机采集调用需要的时间。这个模式自动调整采样频率，
     *     但可能会忽略一些错过采样测量的暂停？？（但是可能会因为测量的暂停遗漏一些采样）
     *     这个模式基于时间，一直运行到迭代时间结束
     * </p>
     */
    @Benchmark
    @BenchmarkMode(Mode.SampleTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public static void measureSampleTime() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(100);
    }

    /*
     * Mode.SingleShotTime measures the single method invocation time. As the Javadoc
     * suggests, we do only the single benchmark method invocation. The iteration
     * time is meaningless in this mode: as soon as benchmark method stops, the
     * iteration is over.
     *
     * This mode is useful to do cold startup tests, when you specifically
     * do not want to call the benchmark method continuously.
     */

    /**
     *
     * <p>Single shot time: 测量单次操作的时间。</p>
     * <p>
     *     运行一次{@link Benchmark}的调用，并且测量它的时间。
     *     这种模式对下面几种情况很有用：当你不想隐藏热身调用时来检测"冷"性能；你想看到调用的进展；你想记录每一个样本。
     *     这个模式是基于工作的，仅在单次调用{@link Benchmark}时运行。
     * </p>
     * 这种模式的注意事项包括:
     * <ul>
     *  <li>通常需要更多的预热/测量迭代</li>
     *  <li>如果基准测试很小，定时器开销可能很大；如果这是一个问题，切换到{@link Mode#SampleTime} 模式</li>
     * </ul>
     */
    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public static void measureSingleShotTime() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(100);
    }

    /*
     * We can also ask for multiple benchmark modes at once. All the tests
     * above can be replaced with just a single test like this:
     */

    @Benchmark
    @BenchmarkMode({Mode.Throughput, Mode.AverageTime, Mode.SampleTime, Mode.SingleShotTime})
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void measureMultiple() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(100);
    }

    /**
     * Meta-mode: all the benchmark modes.
     * This is mostly useful for internal JMH testing.
     */

    @Benchmark
    @BenchmarkMode(Mode.All)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void measureAll() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(100);
    }

}
