package com.cxd.benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.profile.DTraceAsmProfiler;
import org.openjdk.jmh.profile.LinuxPerfNormProfiler;
import org.openjdk.jmh.profile.LinuxPerfProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 分支预测，底层做的一件很牛逼的运行优化
 * @author childe
 * @date 2018-10-20
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(5)
@State(Scope.Benchmark)
public class JMHSample_36_BranchPrediction {

    /*
     * This sample serves as a warning against regular data sets.
     *
     * It is very tempting to present a regular data set to benchmark, either due to
     * naive generation strategy, or just from feeling better about regular data sets.
     * Unfortunately, it frequently backfires: the regular datasets are known to be
     * optimized well by software and hardware. This example exploits one of these
     * optimizations: branch prediction.
     *
     * Imagine our benchmark selects the branch based on the array contents, as
     * we are streaming through it:
     *
     * 这个例子用作对常规数据集的警告。
     *
     * 由于天真的生成策略，或者只是感觉对常规数据集更好，将常规数据集呈现为基准测试是非常诱人的。
     * 不幸的是，它经常事与愿违：已知常规数据集可以通过软件和硬件很好地进行优化。
     * 此示例利用了以下优化之一：分支预测。
     *
     * 假设我们的基准测试通过数组的内容来选择分支，因为我们正在通过它进行流式传输：
     */

    private static final int COUNT = 1024 * 1024;

    private byte[] sorted;
    private byte[] unsorted;

    @Setup
    public void setup() {
        sorted = new byte[COUNT];
        unsorted = new byte[COUNT];
        Random random = new Random(1234);
        random.nextBytes(sorted);
        random.nextBytes(unsorted);
        Arrays.sort(sorted);
    }

    @Benchmark
    @OperationsPerInvocation(COUNT)
    public void sorted(Blackhole bh1, Blackhole bh2) {
        for (byte v : sorted) {
            if (v > 0) {
                bh1.consume(v);
            } else {
                bh2.consume(v);
            }
        }
    }

    @Benchmark
    @OperationsPerInvocation(COUNT)
    public void unsorted(Blackhole bh1, Blackhole bh2) {
        for (byte v : unsorted) {
            if (v > 0) {
                bh1.consume(v);
            } else {
                bh2.consume(v);
            }
        }
    }

    /*
        There is a substantial difference in performance for these benchmarks!

        It is explained by good branch prediction in "sorted" case, and branch mispredicts in "unsorted"
        case. -prof perfnorm conveniently highlights that, with larger "branch-misses", and larger "CPI"
        for "unsorted" case:

        Benchmark                                                       Mode  Cnt   Score    Error  Units
        JMHSample_36_BranchPrediction.sorted                            avgt   25   2.160 Â±  0.049  ns/op
        JMHSample_36_BranchPrediction.sorted:Â·CPI                       avgt    5   0.286 Â±  0.025   #/op
        JMHSample_36_BranchPrediction.sorted:Â·branch-misses             avgt    5  â‰ˆ 10â»â´            #/op
        JMHSample_36_BranchPrediction.sorted:Â·branches                  avgt    5   7.606 Â±  1.742   #/op
        JMHSample_36_BranchPrediction.sorted:Â·cycles                    avgt    5   8.998 Â±  1.081   #/op
        JMHSample_36_BranchPrediction.sorted:Â·instructions              avgt    5  31.442 Â±  4.899   #/op

        JMHSample_36_BranchPrediction.unsorted                          avgt   25   5.943 Â±  0.018  ns/op
        JMHSample_36_BranchPrediction.unsorted:Â·CPI                     avgt    5   0.775 Â±  0.052   #/op
        JMHSample_36_BranchPrediction.unsorted:Â·branch-misses           avgt    5   0.529 Â±  0.026   #/op  <--- OOPS
        JMHSample_36_BranchPrediction.unsorted:Â·branches                avgt    5   7.841 Â±  0.046   #/op
        JMHSample_36_BranchPrediction.unsorted:Â·cycles                  avgt    5  24.793 Â±  0.434   #/op
        JMHSample_36_BranchPrediction.unsorted:Â·instructions            avgt    5  31.994 Â±  2.342   #/op

        It is an open question if you want to measure only one of these tests. In many cases, you have to measure
        both to get the proper best-case and worst-case estimate!
     */


    /*
     * ============================== HOW TO RUN THIS TEST: ====================================
     *
     * You can run this test:
     *
     * a) Via the command line:
     *    $ mvn clean install
     *    $ java -jar target/benchmarks.jar JMHSample_36
     *
     * b) Via the Java API:
     *    (see the JMH homepage for possible caveats when running from IDE:
     *      http://openjdk.java.net/projects/code-tools/jmh/)
     */
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(".*" + JMHSample_36_BranchPrediction.class.getSimpleName() + ".*")
                .build();

        new Runner(opt).run();
    }

}