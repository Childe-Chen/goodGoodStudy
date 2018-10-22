package com.cxd.benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * 幸运的是，大多情况下你只需要一个单独的对象。
 * 这种情况下，我们可以用benchmark的实例来标记@State。
 * 然后，我们就可以像其他任何Java程序那样来引用他。
 *
 * @author childe
 * @date 2018/9/20 17:08
 **/
@State(Scope.Thread)
public class JMHSample_04_DefaultState {

    private double x = Math.PI;

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(JMHSample_04_DefaultState.class.getSimpleName())
                .output("JMHSample_04_DefaultState_result.sampleLog")
                .threads(1)
                .forks(1)
                .build();
        new Runner(options).run();
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void measureDefaultState() {
        x++;
    }
}
