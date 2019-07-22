package com.cxd.javaSourceCode;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;

/**
 * desc
 *
 * @author fanyin
 * @date 2019/3/27 17:39
 **/
public class CompletableFutureTest {

    static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setDaemon(false)
            .setNameFormat("demo-pool-%d").build();

    static ScheduledExecutorService singleThreadPool = new ScheduledThreadPoolExecutor(2, namedThreadFactory);

    public static void main(String[] args) throws Exception {

        List<Integer> integerList = new ArrayList<>(100);
        for (int i = 1; i < 10; i++) {
            integerList.add(i);
        }

        integerList = integerList.stream().filter(integer -> integer >5).collect(Collectors.toList());
        integerList.forEach(integer -> {
            System.out.println(integer);
        });
//        List<CompletableFuture<String>> futureList = new ArrayList<>(10);

//        singleThreadPool.execute(() -> {
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            System.out.println("ddddd");
//        });
//        getList(futureList);
//        Thread.currentThread().join();
        System.out.println("done");

    }

    private static void getList1() {
        List<Integer> integerList = new ArrayList<>(100);
        for (int i = 1; i < 10; i++) {
            integerList.add(i);
        }

        CompletableFuture[] futures = integerList.stream().map(integer ->
            CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int nextInt = new Random().nextInt(100);
                if (nextInt > 10) {
                    throw new RuntimeException(Thread.currentThread().getName() + "  " + nextInt + "");
                }
                return String.valueOf(nextInt);
            }, singleThreadPool).exceptionally(throwable -> {
                System.out.println(throwable.getMessage());
                return "-1";
            })
        ).toArray(CompletableFuture[]::new);

        System.out.println(">>>>");

        CompletableFuture.allOf(futures).join();

        System.out.println("<<<<");
    }

    private static List<String> getList(List<CompletableFuture<String>> futureList) throws InterruptedException, java.util.concurrent.ExecutionException {
        long start = System.currentTimeMillis();
        for (int i = 1; i < 51; i++) {
            CompletableFuture<String> dataPrepare = CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int nextInt = new Random().nextInt(100);
                if (nextInt > 10) {
                    throw new RuntimeException(Thread.currentThread().getName() + "  " + nextInt + "");
                }
                return String.valueOf(nextInt);
            }, singleThreadPool).exceptionally(throwable -> {
                System.out.println(throwable.getMessage());
                return "-1";
            });

            futureList.add(dataPrepare);

            if (i % 10 == 0) {
                List<String> r = futureList.stream().map(CompletableFuture::join).collect(Collectors.toList());
                CompletableFuture.allOf(futureList.toArray(new CompletableFuture[0])).join();
                System.out.println(i + "---------" + (System.currentTimeMillis() - start));

//                r.forEach(System.out::println);

                futureList.clear();
            }
        }

//        CompletableFuture<List<String>> allResult = CompletableFuture.allOf(futureList.toArray(new CompletableFuture[futureList.size()]))
//                .thenApply(aVoid -> {
//                    System.out.println(Thread.currentThread().getName());
//                    return futureList.stream().map(CompletableFuture::join).collect(Collectors.toList());
//                }).exceptionally(throwable -> {
//                    System.out.println(throwable.getMessage() + "------ddddd");
//                    return Collections.emptyList();
//                });
//
//        List<String> rr = allResult.get();

//        System.out.println(allResult.isDone());



        return null;
    }
}
