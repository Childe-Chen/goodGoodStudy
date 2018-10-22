package com.cxd.benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.BenchmarkParams;
import org.openjdk.jmh.infra.ThreadParams;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * 通过JMH提供的脚手架获取JMH的一些运行信息
 *
 * @author childe
 * @date 2018/10/17 16:35
 **/
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Benchmark)
public class JMHSample_31_InfraParams {

    /*
     * There is a way to query JMH about the current running mode. This is
     * possible with three infrastructure objects we can request to be injected:
     *   - BenchmarkParams: covers the benchmark-global configuration
     *   - IterationParams: covers the current iteration configuration
     *   - ThreadParams: covers the specifics about threading
     *
     * Suppose we want to check how the ConcurrentHashMap scales under different
     * parallelism levels. We can put concurrencyLevel in @Param, but it sometimes
     * inconvenient if, say, we want it to follow the @Threads count. Here is
     * how we can query JMH about how many threads was requested for the current run,
     * and put that into concurrencyLevel argument for CHM constructor.
     *
     * 有一种方式用来查JMH并发运行的模型。通过请求注入以下三个脚手架对象我们就可以做到：
     *   - BenchmarkParams: 涵盖了benchmark的全局配置
     *   - IterationParams: 涵盖了当前迭代的配置
     *   - ThreadParams: 涵盖了指定线程的配置
     *
     * 假设我们想检查ConcurrentHashMap如何在不同的并行级别下扩展。我们可以可以把concurrencyLevel通过@Param传入，
     * 但有时不方便，比如，我们想让他和@Threads一致。以下是我们如何查询JMH关于当前运行请求的线程数，
     * 并将其放入ConcurrentHashMap构造函数的concurrencyLevel参数中。
     */

    static final int THREAD_SLICE = 1000;

    private ConcurrentHashMap<String, String> mapSingle;
    private ConcurrentHashMap<String, String> mapFollowThreads;

    @Setup
    public void setup(BenchmarkParams params) {
        int capacity = 16 * THREAD_SLICE * params.getThreads();
        // 并发级别数量似乎只会影响initcapacity（仅在initcapacity小于并发数量时）。这么测试好像没什么意义。
        mapSingle        = new ConcurrentHashMap<>(capacity, 0.75f, 1);
        mapFollowThreads = new ConcurrentHashMap<>(capacity, 0.75f, params.getThreads());
    }

    /*
     * Here is another neat trick. Generate the distinct set of keys for all threads:
     *
     * 这是另一个巧妙的伎俩。为所有线程生成不同的密钥集：
     */

    @State(Scope.Thread)
    public static class Ids {
        private List<String> ids;

        @Setup
        public void setup(ThreadParams threads) {
            ids = new ArrayList<>();
            for (int c = 0; c < THREAD_SLICE; c++) {
                ids.add("ID" + (THREAD_SLICE * threads.getThreadIndex() + c));
            }
        }
    }

    @Benchmark
    public void measureDefault(Ids ids) {
        for (String s : ids.ids) {
            mapSingle.remove(s);
            mapSingle.put(s, s);
        }
    }

    @Benchmark
    public void measureFollowThreads(Ids ids) {
        for (String s : ids.ids) {
            mapFollowThreads.remove(s);
            mapFollowThreads.put(s, s);
        }
    }

    /*
     * ============================== HOW TO RUN THIS TEST: ====================================
     *
     * You can run this test:
     *
     * a) Via the command line:
     *    $ mvn clean install
     *    $ java -jar target/benchmarks.jar JMHSample_31 -t 4 -f 5
     *    (we requested 4 threads, and 5 forks; there are also other options, see -h)
     *
     * b) Via the Java API:
     *    (see the JMH homepage for possible caveats when running from IDE:
     *      http://openjdk.java.net/projects/code-tools/jmh/)
     */

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JMHSample_31_InfraParams.class.getSimpleName())
                .threads(4)
                .forks(5)
                .output("JMHSample_31_InfraParams.sampleLog")
                .build();

        new Runner(opt).run();
    }

}