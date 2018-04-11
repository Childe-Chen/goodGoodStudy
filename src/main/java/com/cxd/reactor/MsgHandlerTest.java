package com.cxd.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * desc
 *
 * @author childe
 * @date 2018/4/8 14:13
 **/
public class MsgHandlerTest {

    public static void main(String[] args) {
        Flux.just(1, 2)
                .concatWith(Mono.error(new IllegalArgumentException()))
                // 失败重试，重试的动作是通过重新订阅序列来实现的
                .retry(0)
                .onErrorResume(e -> {
                    if (e instanceof IllegalStateException) {
                        return Mono.just(0);
                    } else if (e instanceof IllegalArgumentException) {
                        return Mono.just(-1);
                    }
                    return Mono.empty();
                }).subscribe(System.out::println);
    }
}
