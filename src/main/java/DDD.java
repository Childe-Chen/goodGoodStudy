import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * desc
 *
 * @author fanyin
 * @date 2019/11/7 11:50
 **/
public class DDD {

    /**
     * {
     *     "rate": 1000,
     *     "gmvType": 1,
     *     "channelName": "快手",
     *     "postageRate": 500
     * }
     * @param args
     */

    /**
     * rate : 1000
     * gmvType : 1
     * channelName : 快手
     * postageRate : 500
     * platformId : 1
     */



    private String channelName;
    private Integer rate;

    private Integer gmvType;
    private Integer postageRate;

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Integer getGmvType() {
        return gmvType;
    }

    public void setGmvType(Integer gmvType) {
        this.gmvType = gmvType;
    }

    public Integer getPostageRate() {
        return postageRate;
    }

    public void setPostageRate(Integer postageRate) {
        this.postageRate = postageRate;
    }

    public static void main(String[] args) {
//        String s = "{\"1\": {\"rate\": 1000, \"gmvType\": 1, \"channelName\": \"快手\", \"postageRate\": 500 }, \"2\": {\"rate\": 800, \"gmvType\": 0, \"channelName\": \"陌陌\", \"postageRate\": 2000 }, \"3\": {\"rate\": 750, \"gmvType\": 1, \"channelName\": \"qq\", \"postageRate\": 800 }, \"5\": {\"rate\": 200, \"gmvType\": 1, \"channelName\": \"爱逛\", \"postageRate\": 100 } }";
//        Map<String, Object> map = JSONObject.parseObject(s, Map.class);
//        Map<String, DDD> map1 = new HashMap<>();
//        for (Map.Entry<String, Object> obS: map.entrySet()) {
//            map1.put(obS.getKey(), JSONObject.parseObject(obS.getValue().toString(), DDD.class));
//        }
//
//        System.out.println(map);

//        DDD s = new DDD();
//        s.setGmvType(1);
//
//        DDD s1 = new DDD();
//        s1.setGmvType(100);
//
//        DDD s2 = new DDD();
//        s2.setGmvType(50);
//
//        List<DDD> a= new ArrayList<>();
//        a.add(s);
//        a.add(s1);
//        a.add(s2);
//        List<DDD> dd = a.stream().sorted(Comparator.comparingInt(DDD::getGmvType)).collect(Collectors.toList());
//        System.out.println(JSONArray.toJSONString(dd));

        String a = "0023408000023240";
        String b = null;
        System.out.println(a + "_" + b);
    }


}
