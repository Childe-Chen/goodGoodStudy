package com.cxd.benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * Dead-Code Elimination (DCE)
 * 死代码消除对基准测试造成的影响
 *
 * @author childe
 * @date 2018/10/9 11:18
 **/
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class JMHSample_08_DeadCode {

    /*
     * The downfall of many benchmarks is Dead-Code Elimination (DCE): compilers
     * are smart enough to deduce some computations are redundant and eliminate
     * them completely. If the eliminated part was our benchmarked code, we are
     * in trouble.
     *
     * Fortunately, JMH provides the essential infrastructure to fight this
     * where appropriate: returning the result of the computation will ask JMH
     * to deal with the result to limit dead-code elimination (returned results
     * are implicitly consumed by Blackholes, see JMHSample_09_Blackholes).
     *
     * 许多基准测试的失败是死代码消除（DCE）：
     * 编译器非常聪明，可以推断出一些冗余的计算并完全消除它们。
     * 如果被淘汰的部分是我们的基准代码，我们就遇到了麻烦。
     *
     * 幸运的是JMH提供了必要的基础设施，以便在适当的时候来避免这种情况：返回计算结果将要求JMH进行处理以限制死码消除（
     * 返回的结果可以饮食被Blackholes消费，参见 JMHSample_09_Blackholes
     * ）
     */

    private double x = Math.PI;

    @Benchmark
    public void baseline() {
        // do nothing, this is a baseline
        // 基准线
    }

    @Benchmark
    public void measureWrong() {
        // This is wrong: result is not used and the entire computation is optimized away.
        // 这是错误的：结果没有被使用，整个计算将会被编译器优化。
        Math.log(x);
    }

    @Benchmark
    public double measureRight() {
        // This is correct: the result is being used.
        return Math.log(x);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JMHSample_08_DeadCode.class.getSimpleName())
                .output("JMHSample_08_DeadCode.sampleLog")
                .forks(1)
                .build();

        new Runner(opt).run();
    }

}
