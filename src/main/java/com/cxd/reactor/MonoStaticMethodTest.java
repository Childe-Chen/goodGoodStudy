package com.cxd.reactor;

import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Optional;

/**
 * desc
 *
 * @author childe
 * @date 2018/4/4 09:52
 **/
public class MonoStaticMethodTest {

    public static void main(String[] args) {

        Mono.fromCallable(() -> Arrays.asList("2","3")).doOnNext((list) ->
            System.out.println(list.size())
        ).subscribe((list) -> {
            list.forEach((item) -> System.out.print(item + " "));
            System.out.println();
        });

        Mono.justOrEmpty(Optional.of("op")).subscribe(System.out::println);

        Mono.create(monoSink -> monoSink.success("hello")).subscribe(System.out::println);

        System.out.println("finish");
    }


}
