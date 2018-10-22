package com.cxd.benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * AuxCounters是实验类，慎用！未来可能删除。
 * CAVEAT: THIS IS AN EXPERIMENTAL API, it may be changed or removed in future without prior warning.
 *
 * 所以不深入了解了。大意是作为@State的辅助类，可以分别统计操作对象的每个字段值。
 * @author childe
 * @date 2018/10/16 15:20
 **/
@OutputTimeUnit(TimeUnit.SECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class JMHSample_23_AuxCounters {

    /*
     * In some weird cases you need to get the separate throughput/time
     * metrics for the benchmarked code depending on the outcome of the
     * current code. Trying to accommodate the cases like this, JMH optionally
     * provides the special annotation which treats @State objects
     * as the object bearing user counters. See @AuxCounters javadoc for
     * the limitations.
     */

    @State(Scope.Thread)
    @AuxCounters(AuxCounters.Type.OPERATIONS)
    public static class OpCounters {
        // These fields would be counted as metrics
        public int case1;
        public int case2;

        // This accessor will also produce a metric
        public int total() {
            return case1 + case2;
        }
    }

    @State(Scope.Thread)
    // 计算“事件”，即工作量生命周期中的一次性事件。此计数器不会按时间标准化。
    @AuxCounters(AuxCounters.Type.EVENTS)
    public static class EventCounters {
        // This field would be counted as metric
        public int wows;
    }

    /*
     * This code measures the "throughput" in two parts of the branch.
     * The @AuxCounters state above holds the counters which we increment
     * ourselves, and then let JMH to use their values in the performance
     * calculations.
     */

    @Benchmark
    public void splitBranch(OpCounters counters) {
        if (Math.random() < 0.1) {
            counters.case1++;
        } else {
            counters.case2++;
        }
    }

    @Benchmark
    public void runSETI(EventCounters counters) {
        float random = (float) Math.random();
        float wowSignal = (float) Math.PI / 4;
        if (random == wowSignal) {
            // WOW, that's unusual.
            counters.wows++;
        }
    }

    /*
     * ============================== HOW TO RUN THIS TEST: ====================================
     *
     * You can run this test:
     *
     * a) Via the command line:
     *    $ mvn clean install
     *    $ java -jar target/benchmarks.jar JMHSample_23
     *
     * b) Via the Java API:
     *    (see the JMH homepage for possible caveats when running from IDE:
     *      http://openjdk.java.net/projects/code-tools/jmh/)
     */

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JMHSample_23_AuxCounters.class.getSimpleName())
                .output("JMHSample_23_AuxCounters.sampleLog")
                .build();

        new Runner(opt).run();
    }

}