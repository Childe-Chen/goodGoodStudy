package com.cxd.benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * @see org.openjdk.jmh.annotations.Level
 * @see org.openjdk.jmh.annotations.Setup
 * @see org.openjdk.jmh.annotations.TearDown
 *
 * @author childe
 * @date 2018/10/8 11:23
 **/
@State(Scope.Thread)
public class JMHSample_05_StateFixtures {

    private double x;

    /*
     * Since @State objects are kept around during the lifetime of the
     * benchmark, it helps to have the methods which do state housekeeping.
     * These are usual fixture methods, you are probably familiar with them from
     * JUnit and TestNG.
     *
     * Fixture methods make sense only on @State objects, and JMH will fail to
     * compile the test otherwise.
     *
     * As with the State, fixture methods are only called by those benchmark
     * threads which are using the state. That means you can operate in the
     * thread-local context, and (not) use synchronization as if you are
     * executing in the context of benchmark thread.
     *
     * Note: fixture methods can also work with static fields, although the
     * semantics of these operations fall back out of State scope, and obey
     * usual Java rules (i.e. one static field per class).
     *
     * 因为@State对象在benchmark的生命周期中保持不变，它可以帮助我们使用状态管理的方法。
     * 这些fixture方法看起来和JUnit和TestNG很像
     * @see org.openjdk.jmh.annotations.Level。
     *
     * fixture方法只对@State对象有意义，否则，JHM在编译时会报错。
     *
     * 与State一样，fixture方法仅被那些使用state的benchmark线程调用。 这意味着你可以在线程本地上下文中操作，
     * 并且（不）使用同步，就像你在benchmark线程的上下文中执行一样。
     *
     * 注意：fixture方法也可以使用静态字段，尽管这些操作的语义从State范围中退出，并遵守通常的Java规则（即每个类一个静态字段）。
     */

    /*
     * Level默认每个@Benchmark前执行
     * Ok, let's prepare our benchmark:
     */

    @Setup
    public void prepare() {
        x = Math.PI;
    }

    /*
     * Level默认每个@TearDown后执行
     * And, check the benchmark went fine afterwards:
     */

    @TearDown
    public void check() {
        assert x > Math.PI : "Nothing changed?";
    }

    /*
     * This method obviously does the right thing, incrementing the field x
     * in the benchmark state. check() will never fail this way, because
     * we are always guaranteed to have at least one benchmark call.
     */

    @Benchmark
    public void measureRight() {
        x++;
    }

    /*
     * This method, however, will fail the check(), because we deliberately
     * have the "typo", and increment only the local variable. This should
     * not pass the check, and JMH will fail the run.
     */

    @Benchmark
    public void measureWrong() {
        double x = 0;
        x++;
    }

    /*
     * java -ea //启动断言
     * java -ea:pkname... //在包pkname及其子包下起用断言
     * java -ea:pkname.classname //对类 pkname.classname启用断言
     * 停用断言与启用设置类似
     */

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JMHSample_05_StateFixtures.class.getSimpleName())
                .forks(1)
                .jvmArgs("-ea")
                .output("JMHSample_05_StateFixtures.sampleLog")
                .build();

        new Runner(opt).run();
    }

}