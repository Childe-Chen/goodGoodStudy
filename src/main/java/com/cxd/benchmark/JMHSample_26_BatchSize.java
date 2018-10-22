package com.cxd.benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.LinkedList;
import java.util.List;

/**
 * desc
 *
 * @author childe
 * @date 2018/10/17 10:05
 **/
@State(Scope.Thread)
public class JMHSample_26_BatchSize {

    /*
     * Sometimes you need to evaluate operation which doesn't have
     * the steady state. The cost of a benchmarked operation may
     * significantly vary from invocation to invocation.
     *
     * In this case, using the timed measurements is not a good idea,
     * and the only acceptable benchmark mode is a single shot. On the
     * other hand, the operation may be too small for reliable single
     * shot measurement.
     *
     * We can use "batch size" parameter to describe the number of
     * benchmark calls to do per one invocation without looping the method
     * manually and protect from problems described in JMHSample_11_Loops.
     *
     * 有时你需要评估没有稳定状态的操作。基准操作的成本可能因调用而异。
     *
     * 这种情况下，使用时间检测不是一个好主意，唯一可以接受的基准模型是single shot（单次测量）。
     * 另一方面，对于可靠的单次测量，操作可能太小。
     *
     * 我们可以使用“batch size”参数来描述每次调用执行的基准调用次数，而无需手动循环方法并防止JMHSample_11_Loops中描述的问题。
     */

    /*
     * Suppose we want to measure insertion in the middle of the list.
     */

    List<String> list = new LinkedList<>();

    @Benchmark
    @Warmup(iterations = 5, time = 1)
    @Measurement(iterations = 5, time = 1)
    @BenchmarkMode(Mode.AverageTime)
    public List<String> measureWrong_1() {
        list.add(list.size() / 2, "something");
        return list;
    }

    @Benchmark
    @Warmup(iterations = 5, time = 5)
    @Measurement(iterations = 5, time = 5)
    @BenchmarkMode(Mode.AverageTime)
    public List<String> measureWrong_5() {
        list.add(list.size() / 2, "something");
        return list;
    }

    /*
     * This is what you do with JMH.
     */

    @Benchmark
    @Warmup(iterations = 5, batchSize = 5000)
    @Measurement(iterations = 5, batchSize = 5000)
    @BenchmarkMode(Mode.SingleShotTime)
    public List<String> measureRight() {
        list.add(list.size() / 2, "something");
        return list;
    }

    @Setup(Level.Iteration)
    public void setup(){
        list.clear();
    }

    /*
     * ============================== HOW TO RUN THIS TEST: ====================================
     *
     * You can see completely different results for measureWrong_1 and measureWrong_5; this
     * is because the workload has no steady state. The result of the workload is dependent
     * on the measurement time. measureRight does not have this drawback, because it measures
     * the N invocations of the test method and measures it's time.
     *
     * We measure batch of 5000 invocations and consider the batch as the single operation.
     *
     * 您可以看到measureWrong_1和measureWrong_5的完全不同的结果;这是因为工作负载没有稳定状态。工作量的结果取决于测量时间。
     * measureRight没有这个缺点，因为它测量测试方法的N次调用并测量它的时间。
     *
     * 我们测量5000次调用批次并将批次视为单个操作。
     *
     * You can run this test:
     *
     * a) Via the command line:
     *    $ mvn clean install
     *    $ java -jar target/benchmarks.jar JMHSample_26 -f 1
     *
     * b) Via the Java API:
     *    (see the JMH homepage for possible caveats when running from IDE:
     *      http://openjdk.java.net/projects/code-tools/jmh/)
     */

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JMHSample_26_BatchSize.class.getSimpleName())
                .forks(1)
                .output("JMHSample_26_BatchSize.sampleLog")
                .build();

        new Runner(opt).run();
    }

}