package com.cxd.reactor;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * desc
 *
 * @author childe
 * @date 2018/4/9 16:50
 **/
public class SchedulerTest {

    public static void main(String[] args) {
        Flux.create(sink -> {
            System.out.println(Thread.currentThread().getName());
            sink.next(Thread.currentThread().getName());
            sink.complete();
        }).log().publishOn(Schedulers.single()).log()
                .map(x -> String.format("[%s] %s", Thread.currentThread().getName(), x)).log()
                .publishOn(Schedulers.elastic()).log()
                .map(x -> String.format("[%s] %s", Thread.currentThread().getName(), x)).log()
                .subscribeOn(Schedulers.parallel()).log()
                .toStream()
                .forEach(System.out::println);
    }
}
