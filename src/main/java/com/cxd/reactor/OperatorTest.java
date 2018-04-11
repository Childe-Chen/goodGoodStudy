package com.cxd.reactor;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.temporal.TemporalUnit;

/**
 * desc
 *
 * @author childe
 * @date 2018/4/4 11:21
 **/
public class OperatorTest {

    public static void main(String[] args) {
//        Flux.range(1, 100).buffer(20).subscribe(System.out::println);

//        Flux.range(1, 10).subscribe(System.out::println);
//        System.out.println();
//
//        Flux.range(1, 10).bufferUntil(i -> i % 2 == 0).subscribe(System.out::println);
//        System.out.println();
//
//        Flux.range(1, 10).bufferWhile(i -> i % 2 == 0).subscribe(System.out::println);
//        System.out.println();
//
//        Flux.range(1, 10).filter(i -> i % 2 == 0).subscribe(System.out::println);

//        Flux.range(1, 100).window(20).subscribe(System.out::println);
//        System.out.println();
//
//        Flux.interval(Duration.ofMillis(100)).window(Duration.ofMillis(1001)).take(2).toStream().forEach(System.out::println);

//        Flux.just("a", "b")
//                .zipWith(Flux.just("c", "d"))
//                .subscribe(System.out::println);
//        Flux.just(1, 12)
//                .zipWith(Flux.just(2, 22), (Integer s1,Integer s2) -> String.format("%s-%s", s1, s2))
//                .subscribe(System.out::println);

//        Flux.range(1, 1000).take(10).subscribe(System.out::println);
//        System.out.println();

//        Flux.range(1, 1000).limitRequest(100).takeLast(10).subscribe(System.out::println);
//        System.out.println();

//        Flux.range(1, 1000).takeWhile(i -> i < 10).subscribe(System.out::println);
//        System.out.println();
//
//        Flux.range(1, 1000).takeUntil(i -> i == 10).subscribe(System.out::println);

//        Flux.range(1, 100).reduce((x, y) -> x + y).subscribe(System.out::println);
//        Flux.range(1, 100).reduceWith(() -> 100, (x, y) -> x + y).subscribe(System.out::println);

        Flux.merge(Flux.interval(Duration.ofMillis(0),Duration.ofMillis(100)).take(5), Flux.interval(Duration.ofMillis(50), Duration.ofMillis(100)).take(5))
                .toStream()
                .forEach(System.out::println);
        System.out.println();

        Flux.mergeSequential(Flux.interval(Duration.ofMillis(0),Duration.ofMillis(100)).take(5), Flux.interval(Duration.ofMillis(50), Duration.ofMillis(100)).take(5))
                .toStream()
                .forEach(System.out::println);

    }
}
