package com.cxd.javaSourceCode;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * desc
 *
 * @author childe
 * @date 2018/3/20 11:56
 **/
public class mapComputeTest {

    public static void main(String[] args) {


        Map<String, Long> oldPriceGoodsId2TimeMap = new HashMap<>();
        oldPriceGoodsId2TimeMap.put("a",1L);
        oldPriceGoodsId2TimeMap.put("b",2L);
        oldPriceGoodsId2TimeMap.put("c",3L);

        List<KV> kvList = new ArrayList<>();
        kvList.add(new KV("a",4L));
        kvList.add(new KV("b",5L));
        kvList.add(new KV("c",6L));

        for (KV record : kvList) {
            oldPriceGoodsId2TimeMap.compute(record.key,
                    (String key, Long v) -> v < record.value ? record.value : v);
        }

        for (Map.Entry e : oldPriceGoodsId2TimeMap.entrySet()) {
            System.out.println(ToStringBuilder.reflectionToString(e));
        }
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
