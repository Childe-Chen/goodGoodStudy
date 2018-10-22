package com.cxd.benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * desc
 *
 * @author childe
 * @date 2018/10/22 19:47
 **/
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(5)
public class JMHSample_38_PerInvokeSetup {

    /*
     * This example highlights the usual mistake in non-steady-state benchmarks.
     *
     * Suppose we want to test how long it takes to bubble sort an array. Naively,
     * we could make the test that populates an array with random (unsorted) values,
     * and calls sort on it over and over again:
     *
     * 此示例突显非稳态基准测试(non-steady-state benchmarks)中的常见错误。
     *
     * 假设我们要测试对数组进行冒泡排序的耗时。
     * 天真地，我们可以使用随机（未排序）值填充数组，并一遍又一遍地调用sort测试：
     */

    private void bubbleSort(byte[] b) {
        boolean changed = true;
        while (changed) {
            changed = false;
            for (int c = 0; c < b.length - 1; c++) {
                if (b[c] > b[c + 1]) {
                    byte t = b[c];
                    b[c] = b[c + 1];
                    b[c + 1] = t;
                    changed = true;
                }
            }
        }
    }

    // Could be an implicit State instead, but we are going to use it
    // as the dependency in one of the tests below
    // 可能是一个隐式状态，但我们将在下面的一个测试中依赖它

    @State(Scope.Benchmark)
    public static class Data {

        @Param({"1", "16", "256"})
        int count;

        byte[] arr;

        @Setup
        public void setup() {
            arr = new byte[count];
            Random random = new Random(1234);
            random.nextBytes(arr);
        }
    }

    @Benchmark
    public byte[] measureWrong(Data d) {
        bubbleSort(d.arr);
        return d.arr;
    }

    /*
     * The method above is subtly wrong: it sorts the random array on the first invocation
     * only. Every subsequent call will "sort" the already sorted array. With bubble sort,
     * that operation would be significantly faster!
     *
     * This is how we might *try* to measure it right by making a copy in Level.Invocation
     * setup. However, this is susceptible to the problems described in Level.Invocation
     * Javadocs, READ AND UNDERSTAND THOSE DOCS BEFORE USING THIS APPROACH.
     *
     * 上面的方法是巧妙的错误：它只在第一次调用时对随机数组进行排序。
     * 后续每个调用都将"排序"已排序的数组。
     * 通过冒泡排序，操作速度会明显加快！
     *
     * 我们可以尝试通过在Level.Invocation中制作数组的副本来正确测量它。
     * 在使用Level.Invocation这些方法之前请阅读并理解这些文档。
     */

    @State(Scope.Thread)
    public static class DataCopy {
        byte[] copy;

        @Setup(Level.Invocation)
        public void setup2(Data d) {
            copy = Arrays.copyOf(d.arr, d.arr.length);
        }
    }

    @Benchmark
    public byte[] measureNeutral(DataCopy d) {
        bubbleSort(d.copy);
        return d.copy;
    }

    /*
     * In an overwhelming majority of cases, the only sensible thing to do is to suck up
     * the per-invocation setup costs into a benchmark itself. This work well in practice,
     * especially when the payload costs dominate the setup costs.
     *
     * 在绝大多数情况下，唯一明智的做法是将每次调用设置成本吸收到基准测试本身。
     *
     * 这在实践中很有效，特别是当有效载荷成本主导设置成本时
     * （即：测试本身耗时远远大于准备数据时，准备数据时间对测试本身的影响可以忽略）。
     */

    @Benchmark
    public byte[] measureRight(Data d) {
        byte[] c = Arrays.copyOf(d.arr, d.arr.length);
        bubbleSort(c);
        return c;
    }

    /*
        Benchmark                                   (count)  Mode  Cnt      Score     Error  Units

        JMHSample_38_PerInvokeSetup.measureWrong          1  avgt   25      2.408 Â±   0.011  ns/op
        JMHSample_38_PerInvokeSetup.measureWrong         16  avgt   25      8.286 Â±   0.023  ns/op
        JMHSample_38_PerInvokeSetup.measureWrong        256  avgt   25     73.405 Â±   0.018  ns/op

        JMHSample_38_PerInvokeSetup.measureNeutral        1  avgt   25     15.835 Â±   0.470  ns/op
        JMHSample_38_PerInvokeSetup.measureNeutral       16  avgt   25    112.552 Â±   0.787  ns/op
        JMHSample_38_PerInvokeSetup.measureNeutral      256  avgt   25  58343.848 Â± 991.202  ns/op

        JMHSample_38_PerInvokeSetup.measureRight          1  avgt   25      6.075 Â±   0.018  ns/op
        JMHSample_38_PerInvokeSetup.measureRight         16  avgt   25    102.390 Â±   0.676  ns/op
        JMHSample_38_PerInvokeSetup.measureRight        256  avgt   25  58812.411 Â± 997.951  ns/op

        We can clearly see that "measureWrong" provides a very weird result: it "sorts" way too fast.
        "measureNeutral" is neither good or bad: while it prepares the data for each invocation correctly,
        the timing overheads are clearly visible. These overheads can be overwhelming, depending on
        the thread count and/or OS flavor.

        明显可以看出"measureWrong"跑出来一个奇怪的结果：它的排序太快了。
        "measureNeutral"一般般：它正确地为每个调用准备数据，时间开销清晰可见。在不同的线程数 和/或 OS风格下，这些开销可能会非常大。
     */

    /*
     * ============================== HOW TO RUN THIS TEST: ====================================
     *
     * You can run this test:
     *
     * a) Via the command line:
     *    $ mvn clean install
     *    $ java -jar target/benchmarks.jar JMHSample_38
     *
     * b) Via the Java API:
     *    (see the JMH homepage for possible caveats when running from IDE:
     *      http://openjdk.java.net/projects/code-tools/jmh/)
     */
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(".*" + JMHSample_38_PerInvokeSetup.class.getSimpleName() + ".*")
                .output("JMHSample_38_PerInvokeSetup.sampleLog")
                .build();

        new Runner(opt).run();
    }

}
