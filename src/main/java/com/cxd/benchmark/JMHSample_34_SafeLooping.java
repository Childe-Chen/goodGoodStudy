package com.cxd.benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * 如何避免编译器的优化，按照预想跑循环
 *
 * @author childe
 * @date 2018/10/17 17:37
 **/
@State(Scope.Thread)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(3)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class JMHSample_34_SafeLooping {

    /*
     * JMHSample_11_Loops warns about the dangers of using loops in @Benchmark methods.
     * Sometimes, however, one needs to traverse through several elements in a dataset.
     * This is hard to do without loops, and therefore we need to devise a scheme for
     * safe looping.
     *
     * JMHSample_11_Loops警告我们在@Benchmark方法中使用循环很危险。
     * 然而，有时我们需要便利数据集中的元素。很难避免循环，因此我们需要为安全循环设计一种方式。
     */

    /*
     * Suppose we want to measure how much it takes to execute work() with different
     * arguments. This mimics a frequent use case when multiple instances with the same
     * implementation, but different data, is measured.
     *
     * 假设我们想要测量以不同参数运行work()时的性能。当测量具有相同实现但数据不同的多个实例时，这模仿了一个常见的用例。
     */

    static final int BASE = 42;

    static int work(int x) {
        return BASE + x;
    }

    /*
     * Every benchmark requires control. We do a trivial control for our benchmarks
     * by checking the benchmark costs are growing linearly with increased task size.
     * If it doesn't, then something wrong is happening.
     *
     * 每个基准都需要控制。我们对的基准进行了微不足道的控制，通过检查基准成本随着任务规模的增加而线性增长。
     * 如果没有，那么就出现了问题。
     */

    @Param({"1", "10", "100", "1000"})
    int size;

    int[] xs;

    @Setup
    public void setup() {
        xs = new int[size];
        for (int c = 0; c < size; c++) {
            xs[c] = c;
        }
    }

    /*
     * First, the obviously wrong way: "saving" the result into a local variable would not
     * work. A sufficiently smart compiler will inline work(), and figure out only the last
     * work() call needs to be evaluated. Indeed, if you run it with varying $size, the score
     * will stay the same!
     *
     * 首先，这个错误很明显：把结果保存在了局部变量中，这是达不到效果。一个足够只能的编译器会把work()进行内敛优化，
     * 并指出只有最后一次work()调用需要被操作。确实，如果你用一系列$size（x数组的长度）来测试，会发现他们的结果是一样的。
     */

    @Benchmark
    public int measureWrong_1() {
        int acc = 0;
        for (int x : xs) {
            acc = work(x);
        }
        return acc;
    }

    /*
     * Second, another wrong way: "accumulating" the result into a local variable. While
     * it would force the computation of each work() method, there are software pipelining
     * effects in action, that can merge the operations between two otherwise distinct work()
     * bodies. This will obliterate the benchmark setup.
     *
     * In this example, HotSpot does the unrolled loop, merges the $BASE operands into a single
     * addition to $acc, and then does a bunch of very tight stores of $x-s. The final performance
     * depends on how much of the loop unrolling happened *and* how much data is available to make
     * the large strides.
     *
     * 然后，另外一个错误：把计算结果保存在局部变量中。
     * 虽然它会强制计算每个work()方法，但是有一些软件的pipelining可以合并两个不同的work()体之间的操作。这将消除基准设置。
     *
     * HotSpot执行unrolled循环，将$BASE操作数合并为$acc的单个添加，然后执行一堆非常紧凑的$x-s存储。
     * 最终的性能取决于循环unrolled的次数以及可用于实现大幅度跨越的数据量。
     */

    @Benchmark
    public int measureWrong_2() {
        int acc = 0;
        for (int x : xs) {
            acc += work(x);
        }
        return acc;
    }

    /*
     * Now, let's see how to measure these things properly. A very straight-forward way to
     * break the merging is to sink each result to Blackhole. This will force runtime to compute
     * every work() call in full. (We would normally like to care about several concurrent work()
     * computations at once, but the memory effects from Blackhole.consume() prevent those optimization
     * on most runtimes).
     *
     * 现在，让我们看下如何正确测量。一个很直接打断合并的方法就是把每个结果都下沉到Blackhole。
     * 这会强制在运行时为每次调用work()方法都会进行计算。我们通常喜欢同时关注几个并发的work()计算，但是Blackhole.consume()的内存效果阻止了大多数运行时的优化）
     */

    @Benchmark
    public void measureRight_1(Blackhole bh) {
        for (int x : xs) {
            bh.consume(work(x));
        }
    }

    /*
     * DANGEROUS AREA, PLEASE READ THE DESCRIPTION BELOW.
     *
     * Sometimes, the cost of sinking the value into a Blackhole is dominating the nano-benchmark score.
     * In these cases, one may try to do a make-shift "sinker" with non-inlineable method. This trick is
     * *very* VM-specific, and can only be used if you are verifying the generated code (that's a good
     * strategy when dealing with nano-benchmarks anyway).
     *
     * You SHOULD NOT use this trick in most cases. Apply only where needed.
     *
     * 危险区域，请阅读以下解释。
     *
     * 有时，将结果下沉到Blackhole的花费会影响nano-benchmark的结果。
     * 在这些情况下，我们或许可以尝试用non-inlineable的方法来做切换。？这个技巧非常特定于VM？，只有在验证生成的代码？时才能使用（这在处理nano-benchmarks时是一个很好的策略）。
     *
     * 大多情况下不要使用该技巧。仅在需要时使用。
     */

    @Benchmark
    public void measureRight_2() {
        for (int x : xs) {
            sink(work(x));
        }
    }

    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    public static void sink(int v) {
        // IT IS VERY IMPORTANT TO MATCH THE SIGNATURE TO AVOID AUTOBOXING.
        // The method intentionally does nothing.
    }


    /*
     * ============================== HOW TO RUN THIS TEST: ====================================
     *
     * You might notice measureWrong_1 does not depend on $size, measureWrong_2 has troubles with
     * linearity, and otherwise much faster than both measureRight_*. You can also see measureRight_2
     * is marginally faster than measureRight_1.
     *
     * 你会注意到measureWrong_1结果不受$size影响，measureWrong_2不是线性增长并且比measureRight_*要快的多。
     * 你也会看到measureRight_2比measureRight_1稍快。
     *
     * You can run this test:
     *
     * a) Via the command line:
     *    $ mvn clean install
     *    $ java -jar target/benchmarks.jar JMHSample_34
     *
     * b) Via the Java API:
     *    (see the JMH homepage for possible caveats when running from IDE:
     *      http://openjdk.java.net/projects/code-tools/jmh/)
     */

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JMHSample_34_SafeLooping.class.getSimpleName())
                .forks(3)
                .output("JMHSample_34_SafeLooping.sampleLog")
                .build();

        new Runner(opt).run();
    }

}
