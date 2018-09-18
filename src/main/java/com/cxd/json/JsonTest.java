package com.cxd.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * desc
 *
 * @author childe
 * @date 2018/7/16 11:42
 **/
public class JsonTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonTest.class);

    public static void main(String[] args) {
        int b = 0;
        for (int i = 0; i < 5; i++) {
            b++;
            LOGGER.error(b + "");
        }
        System.out.println(b);
//        t();

//        String jsonStr = "{\"age\":\"1\",\"name\":\"rrrr\"}";
//
//        Man man = JSONObject.parseObject(jsonStr, Man.class);
//
//        System.out.println(man.getAge());
//
//        Man man1 = new Man();
//        man1.setName("222");
//        man1.setAge(2);
//        System.out.println(JSONObject.toJSONString(man1));
    }

    public static void t() {
        String json = "[{\"id\":2222,\"lastVer\":0,\"opUserId\":\"ad51f23606514ab1804b7af5656b7055\",\"commodityGoodsId\":\"999312365f956785015f956f862a0012\",\"commodityId\":\"999312365f956785015f956f85e50011\",\"commodityName\":\"西瓜\",\"entityId\":\"99931236\",\"goodsId\":\"999312365f9455f6015f956ee224002d\",\"goodsStock\":19927,\"imgPath\":\"99931236/commodityGoods/a4a1e8ec2c0d45d1b2f98a3dfe1250d2.png\",\"imgServer\":\"http://ifiletest.2dfire.com\",\"num\":1,\"orderMaxNum\":-1,\"orderMinNum\":0,\"packageUnit\":\"箱\",\"promotionVoList\":[{\"name\":\"配送费优惠\",\"offerAmount\":2200,\"offerThreshold\":2200,\"type\":2}],\"selectTotalPrice\":300,\"selfEntityId\":\"99931236\",\"sellerEntityId\":\"99931236\",\"specName\":\"\",\"storeId\":\"999312365f956785015f9567b1090001\",\"storeName\":\"花椒连锁总部\",\"transferFee\":2200,\"priceLong\":300,\"operationType\":0,\"itemId\":\"9993123664fe93fa0164fe93fae80001\",\"itemName\":\"西瓜\",\"select\":true,\"unValid\":false},{\"id\":\"9993123664fe93fa0164fe93fa590000\",\"lastVer\":0,\"opUserId\":\"ad51f23606514ab1804b7af5656b7055\",\"commodityGoodsId\":\"999312366242c271016242c276240003\",\"commodityId\":\"999312366242c271016242c271c80000\",\"commodityName\":\"安井撒尿牛肉丸2.5kg/包\",\"entityId\":\"99931236\",\"goodsId\":\"9993123662232a540162235f3aab0040\",\"goodsStock\":99972,\"imgPath\":\"99931236/mCommodity/ee5b0d51b9a84d6b9b29d2ec046a6d26.png\",\"imgServer\":\"http://ifiletest.2dfire.com/\",\"num\":1,\"orderMaxNum\":-1,\"orderMinNum\":0,\"packageUnit\":\"包\",\"promotionVoList\":[{\"name\":\"配送费优惠\",\"offerAmount\":2200,\"offerThreshold\":2200,\"type\":2}],\"selectTotalPrice\":600,\"selfEntityId\":\"99931236\",\"sellerEntityId\":\"99931236\",\"specName\":\"\",\"storeId\":\"999312365f956785015f9567b1090001\",\"storeName\":\"花椒连锁总部\",\"transferFee\":2200,\"priceLong\":300,\"operationType\":0,\"itemId\":\"9993123664fe93fa0164fe93fa590000\",\"itemName\":\"安井撒尿牛肉丸2.5kg/包\",\"select\":true,\"unValid\":false}]";

        List<CartGoodsVo> list = JSON.parseArray(json, CartGoodsVo.class);
        list.forEach(baseVo -> System.out.println(baseVo.getIdLong()));
    }
}
