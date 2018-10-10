package com.cxd.benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * Method Inlining
 * 方法内敛，是JVM对代码的编译优化，常见的编译优化 @see http://www.importnew.com/2009.html
 *
 * 官方白皮书
 * From http://www.oracle.com/technetwork/java/whitepaper-135217.html#method:
 *
 * The frequency of virtual method invocations in the Java programming language is an important optimization bottleneck.
 * Once the Java HotSpot adaptive optimizer has gathered information during execution about program hot spots,
 * it not only compiles the hot spot into native code, but also performs extensive method inlining on that code.
 *
 * Inlining has important benefits.
 * It dramatically reduces the dynamic frequency of method invocations, which saves the time needed to perform those method invocations.
 * But even more importantly, inlining produces much larger blocks of code for the optimizer to work on.
 * This creates a situation that significantly increases the effectiveness of traditional compiler optimizations,
 * overcoming a major obstacle to increased Java programming language performance.
 *
 * Inlining is synergistic with other code optimizations, because it makes them more effective.
 * As the Java HotSpot compiler matures, the ability to operate on large, inlined blocks of code will open the door to a host of even more advanced optimizations in the future.
 *
 * Java编程语言中虚拟方法调用的频率是一个重要的优化瓶颈。
 * 一旦Java HotSpot自适应优化器在执行期间收集有关程序热点的信息，它不仅将热点编译为本机代码，而且还对该代码执行大量方法内联。
 *
 * 内联具有重要的好处。
 * 它大大降低了方法调用的动态频率，从而节省了执行这些方法调用所需的时间。
 * 但更重要的是，内联会为优化程序生成更大的代码块。
 * 这创造了一种能够显着提高传统编译器优化效率的情况，克服了增加Java编程语言性能的主要障碍。
 *
 * 内联与其他代码优化具有协同作用，因为它使它们更有效。
 * 随着Java HotSpot编译器的成熟，对大型内联代码块进行操作的能力将为未来的一系列更高级的优化打开大门。
 *
 * @author childe
 * @date 2018/10/10 14:16
 **/
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class JMHSample_16_CompilerControl {

    /*
     * We can use HotSpot-specific functionality to tell the compiler what
     * do we want to do with particular methods. To demonstrate the effects,
     * we end up with 3 methods in this sample.
     *
     * 我们使用HotSpot特定的功能来告诉编译器我们想对特定的方法做怎么。
     * 为了证明这种效果，我们在这个例子中写了三个测试方法。
     */

    /**
     * These are our targets:
     *   - first method is prohibited from inlining
     *   - second method is forced to inline
     *   - third method is prohibited from compiling
     *
     * We might even place the annotations directly to the benchmarked
     * methods, but this expresses the intent more clearly.
     *
     * 这是我们的目标：
     *  - 第一个方法禁止内敛
     *  - 第二个方法强制内敛
     *  - 第三个方法禁止编译
     *
     * 我们甚至可以将注释直接放在基准测试方法中，但这更清楚地表达了意图。
     */

    public void target_blank() {
        // this method was intentionally left blank
        // 方法故意留空
    }

    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    public void target_dontInline() {
        // this method was intentionally left blank
    }

    @CompilerControl(CompilerControl.Mode.INLINE)
    public void target_inline() {
        // this method was intentionally left blank
    }

    /**
     * Exclude the method from the compilation.
     */
    @CompilerControl(CompilerControl.Mode.EXCLUDE)
    public void target_exclude() {
        // this method was intentionally left blank
    }

    /*
     * These method measures the calls performance.
     *
     * 这些方法来测量调用性能。
     */

    @Benchmark
    public void baseline() {
        // this method was intentionally left blank
    }

    @Benchmark
    public void blank() {
        target_blank();
    }

    @Benchmark
    public void dontinline() {
        target_dontInline();
    }

    @Benchmark
    public void inline() {
        target_inline();
    }

    @Benchmark
    public void exclude() {
        target_exclude();
    }

    /*
     * ============================== HOW TO RUN THIS TEST: ====================================
     *
     * Note the performance of the baseline, blank, and inline methods are the same.
     * dontinline differs a bit, because we are making the proper call.
     * exclude is severely slower, becase we are not compiling it at all.
     *
     * You can run this test:
     *
     * a) Via the command line:
     *    $ mvn clean install
     *    $ java -jar target/benchmarks.jar JMHSample_16 -wi 0 -i 3 -f 1
     *    (we requested no warmup iterations, 3 iterations, single fork; there are also other options, see -h)
     *
     * b) Via the Java API:
     *    (see the JMH homepage for possible caveats when running from IDE:
     *      http://openjdk.java.net/projects/code-tools/jmh/)
     */

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JMHSample_16_CompilerControl.class.getSimpleName())
                .warmupIterations(0)
                .measurementIterations(3)
                .forks(1)
                .output("JMHSample_16_CompilerControl.log")
                .build();

        new Runner(opt).run();
    }

}