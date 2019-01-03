package com.cxd.javaSourceCode.streamapi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * desc
 *
 * @author childe
 * @date 2018/7/4 11:33
 **/
public class ReduceTest {
    public static void main(String[] args) {
        System.out.println(Stream.of(1).reduce(0, Integer::sum));

        Stream.of(1,2,3,33,333).filter(integer -> integer > 100)
                .map(integer -> integer * 2)
                .forEach(integer -> {
                    if (integer > 10) {
                        System.out.println(integer);
                        return;
                    }
                    System.out.println("ss" + integer);
        });

        List<KV> kvList = new ArrayList<>(5);

        for (long i = 0; i < 5; i++) {
            kvList.add(new KV(String.valueOf(i),1L));
        }

        long sum = kvList.stream().map(KV::getValue).reduce(0L,Long::sum);
        System.out.println(sum);
    }


    static class KV {
        String key;

        Long value;

        public KV(String key, Long value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public Long getValue() {
            return value;
        }

        public void setValue(Long value) {
            this.value = value;
        }
    }
}
