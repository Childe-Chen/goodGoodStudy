package com.cxd.reactor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Random;

/**
 * Flux 类的静态方法
 *
 * @author childe
 * @date 2018/4/3 14:10
 **/
public class FluxStaticMethodTest {

    public static void main(String[] args) throws Exception {

//        generateSimpleFlux();

//        generateComplexFlux();

        Flux.interval(Duration.ofMillis(1), Duration.ofSeconds(10)).take(20).subscribe(System.out::println);

        Thread.sleep(1000 * 50);
    }

    private static void generateComplexFlux() {
//        Flux.generate(sink -> {
//            sink.next("Hello");
//            sink.complete();
//        }).limitRequest(1).subscribe(System.out::println);

//        final Random random = new Random();
//        Flux.generate(ArrayList::new, (list, sink) -> {
//            int value = random.nextInt(100);
//            list.add(value);
//            sink.next(value);
//            if (list.size() == 1_0000_0000) {
//                sink.complete();
//            }
//            return list;
//        }).doOnComplete(() -> System.out.println("done")).subscribe(System.out::println);

        Flux.create(sink -> {
            for (int i = 0; i < 10; i++) {
                sink.next(i);
            }
            sink.complete();
        }).limitRequest(5).subscribe(System.out::println);
    }

    private static void generateSimpleFlux() {
        Flux.just("Hello", "World").doOnComplete(() -> {
            System.out.println("done just");
        }).subscribe(System.out::println);
        System.out.println("<---------->");

        Flux.fromArray(new Integer[] {1, 2, 3}).doOnComplete(() -> {
            System.out.println("done fromArray");
        }).subscribe(System.out::println);
        System.out.println("<---------->");

        Flux.empty().doOnComplete(() -> {
            System.out.println("done empty");
        }).subscribe(System.out::println);
        System.out.println("<---------->");

        Flux.range(1, 10).doOnComplete(() -> {
            System.out.println("done range");
        }).subscribe(System.out::println);
        System.out.println("<---------->");

        Flux.interval(Duration.of(2, ChronoUnit.SECONDS)).doOnComplete(() -> {
            System.out.println("done interval");
        }).subscribe(System.out::println);
        System.out.println("<---------->");
    }
}
