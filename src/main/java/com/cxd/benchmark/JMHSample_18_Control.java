package com.cxd.benchmark;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Group;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Control;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 同benchmark交互
 * @see org.openjdk.jmh.infra.Control
 * @author childe
 * @date 2018/10/12 15:12
 **/
@State(Scope.Group)
public class JMHSample_18_Control {

    /*
     * Sometimes you need the tap into the harness mind to get the info
     * on the transition change. For this, we have the experimental state object,
     * Control, which is updated by JMH as we go.
     *
     * 有时您需要点击线束头脑以获取有关转换更改的信息。为此，我们有实验状态对象Control，它由JMH随时更新。
     *
     * WARNING: The API for Contro class is considered unstable, and can be changed without notice.
     */

    /*
     * In this example, we want to estimate the ping-pong speed for the simple
     * AtomicBoolean. Unfortunately, doing that in naive manner will livelock
     * one of the threads, because the executions of ping/pong are not paired
     * perfectly. We need the escape hatch to terminate the loop if threads
     * are about to leave the measurement.
     *
     * 在这个例子中，我们想要估计简单的AtomicBoolean的ping / pong速度。
     * 不幸的是，以天真的方式执行此操作将会锁定其中一个线程，因为ping / pong的执行不能完美配对。
     * 如果线程即将离开测量，我们需要"逃生舱口"来终止循环。
     */

    public final AtomicBoolean flag = new AtomicBoolean();

    @Benchmark
    @Group("pingpong")
    public void ping(Control cnt) {
        // 业务在迭代时间未结束前，一直运行。
        while (!cnt.stopMeasurement && !flag.compareAndSet(false, true)) {
            // this body is intentionally left blank
        }
    }

    @Benchmark
    @Group("pingpong")
    public void pong(Control cnt) {
        while (!cnt.stopMeasurement && !flag.compareAndSet(true, false)) {
            // this body is intentionally left blank
        }
    }

    /*
     * ============================== HOW TO RUN THIS TEST: ====================================
     *
     * You can run this test:
     *
     * a) Via the command line:
     *    $ mvn clean install
     *    $ java -jar target/benchmarks.jar JMHSample_18 -t 2 -f 1
     *    (we requested 2 threads and single fork; there are also other options, see -h)
     *
     * b) Via the Java API:
     *    (see the JMH homepage for possible caveats when running from IDE:
     *      http://openjdk.java.net/projects/code-tools/jmh/)
     */

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JMHSample_18_Control.class.getSimpleName())
                .threads(2)
                .forks(1)
                .output("JMHSample_18_Control.sampleLog")
                .build();

        new Runner(opt).run();
    }

}