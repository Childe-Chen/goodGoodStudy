package com.cxd.benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * desc
 *
 * @author childe
 * @date 2018/10/22 17:10
 **/
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(5)
@State(Scope.Benchmark)
public class JMHSample_37_CacheAccess {

    /*
     * This sample serves as a warning against subtle differences in cache access patterns.
     *
     * Many performance differences may be explained by the way tests are accessing memory.
     * In the example below, we walLevelk the matrix either row-first, or col-first:
     */

    private final static int COUNT = 4096;
    private final static int MATRIX_SIZE = COUNT * COUNT;

    private int[][] matrix;

    @Setup
    public void setup() {
        matrix = new int[COUNT][COUNT];
        Random random = new Random(1234);
        for (int i = 0; i < COUNT; i++) {
            for (int j = 0; j < COUNT; j++) {
                matrix[i][j] = random.nextInt();
            }
        }
    }

    @Benchmark
    @OperationsPerInvocation(MATRIX_SIZE)
    public void colFirst(Blackhole bh) {
        for (int c = 0; c < COUNT; c++) {
            for (int r = 0; r < COUNT; r++) {
                bh.consume(matrix[r][c]);
            }
        }
    }

    @Benchmark
    @OperationsPerInvocation(MATRIX_SIZE)
    public void rowFirst(Blackhole bh) {
        for (int r = 0; r < COUNT; r++) {
            for (int c = 0; c < COUNT; c++) {
                bh.consume(matrix[r][c]);
            }
        }
    }

    /*
        Notably, colFirst accesses are much slower, and that's not a surprise: Java's multidimensional
        arrays are actually rigged, being one-dimensional arrays of one-dimensional arrays. Therefore,
        pulling n-th element from each of the inner array induces more cache misses, when matrix is large.
        -prof perfnorm conveniently highlights that, with >2 cache misses per one benchmark op:

        值得注意的是，colFirst访问速度要慢得多，这并不奇怪：
        Java的多维数组实际上是被操纵的，是一维数组的一维数组。
        因此，当矩阵很大时，从每个内部阵列中拉出第n个元素会引起更多的高速缓存未命中。
        -prof perfnorm清晰的展示出，每个基准操作有2个缓存未命中：

        Benchmark                                                 Mode  Cnt   Score    Error  Units
        JMHSample_37_MatrixCopy.colFirst                          avgt   25   5.306 Â±  0.020  ns/op
        JMHSample_37_MatrixCopy.colFirst:Â·CPI                     avgt    5   0.621 Â±  0.011   #/op
        JMHSample_37_MatrixCopy.colFirst:Â·L1-dcache-load-misses   avgt    5   2.177 Â±  0.044   #/op <-- OOPS
        JMHSample_37_MatrixCopy.colFirst:Â·L1-dcache-loads         avgt    5  14.804 Â±  0.261   #/op
        JMHSample_37_MatrixCopy.colFirst:Â·LLC-loads               avgt    5   2.165 Â±  0.091   #/op
        JMHSample_37_MatrixCopy.colFirst:Â·cycles                  avgt    5  22.272 Â±  0.372   #/op
        JMHSample_37_MatrixCopy.colFirst:Â·instructions            avgt    5  35.888 Â±  1.215   #/op

        JMHSample_37_MatrixCopy.rowFirst                          avgt   25   2.662 Â±  0.003  ns/op
        JMHSample_37_MatrixCopy.rowFirst:Â·CPI                     avgt    5   0.312 Â±  0.003   #/op
        JMHSample_37_MatrixCopy.rowFirst:Â·L1-dcache-load-misses   avgt    5   0.066 Â±  0.001   #/op
        JMHSample_37_MatrixCopy.rowFirst:Â·L1-dcache-loads         avgt    5  14.570 Â±  0.400   #/op
        JMHSample_37_MatrixCopy.rowFirst:Â·LLC-loads               avgt    5   0.002 Â±  0.001   #/op
        JMHSample_37_MatrixCopy.rowFirst:Â·cycles                  avgt    5  11.046 Â±  0.343   #/op
        JMHSample_37_MatrixCopy.rowFirst:Â·instructions            avgt    5  35.416 Â±  1.248   #/op

        So, when comparing two different benchmarks, you have to follow up if the difference is caused
        by the memory locality issues.

        所以，在比较两个不同的基准时，如果差异是由内存局部性问题引起的，则必须跟进。
     */

    /*
     * ============================== HOW TO RUN THIS TEST: ====================================
     *
     * You can run this test:
     *
     * a) Via the command line:
     *    $ mvn clean install
     *    $ java -jar target/benchmarks.jar JMHSample_37
     *
     * b) Via the Java API:
     *    (see the JMH homepage for possible caveats when running from IDE:
     *      http://openjdk.java.net/projects/code-tools/jmh/)
     */
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(".*" + JMHSample_37_CacheAccess.class.getSimpleName() + ".*")
                .output("JMHSample_37_CacheAccess.sampleLog")
                .build();

        new Runner(opt).run();
    }

}