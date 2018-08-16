package com.cxd.json;

import com.alibaba.fastjson.annotation.JSONField;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;

/**
 * desc
 *
 * @author childe
 * @date 2018/8/3 15:34
 **/
public class CartGoodsVo extends BaseVo {

    //实体ID
    private String entityId;

    //自实体ID
    private String selfEntityId;

    //卖方实体ID
    private String sellerEntityId;


    private String storeId;

    //门店店铺名称
    private String storeName;

    //商品ID
    private String commodityId;

    //商品名称
    private String commodityName;

    //规格原料ID
    private String  commodityGoodsId;

    //商品规格
    private String specName = StringUtils.EMPTY;

    //原料ID
    private String goodsId;

    //商品图片服务器
    private String imgServer = StringUtils.EMPTY;

    //商品图片路径
    private String imgPath = StringUtils.EMPTY;

    //商品单价
    @Deprecated
    private BigDecimal price;

    private Long priceLong;

    //限购数量
    private Integer orderMaxNum;

    private Integer orderMinNum;

    //已选商品数量
    private Integer num;

    //商品库存
    private Integer goodsStock;

    //包装单位
    private String packageUnit;

    //操作人ID
    private String opUserId;

    //操作人姓名
    private String opUserName;

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getSelfEntityId() {
        return selfEntityId;
    }

    public void setSelfEntityId(String selfEntityId) {
        this.selfEntityId = selfEntityId;
    }

    public String getSellerEntityId() {
        return sellerEntityId;
    }

    public void setSellerEntityId(String sellerEntityId) {
        this.sellerEntityId = sellerEntityId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getCommodityGoodsId() {
        return commodityGoodsId;
    }

    public void setCommodityGoodsId(String commodityGoodsId) {
        this.commodityGoodsId = commodityGoodsId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getImgServer() {
        return imgServer;
    }

    public void setImgServer(String imgServer) {
        this.imgServer = imgServer;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Deprecated
    public BigDecimal getPrice() {
        return price;
    }

    @Deprecated
    public void setPrice(BigDecimal price) {
        this.setPriceLongWithBigDecimal(price);
    }

    public Long getPriceLong() {
        return priceLong;
    }

    @JSONField(name="priceLong")
    public void setPriceLong(Long priceLong) {
        this.priceLong = priceLong;
        this.price = new BigDecimal(priceLong).divide(YUAN_2_FEN_RATIO);
    }

    public void setPriceLongWithBigDecimal(BigDecimal price) {
        this.price = price;
        this.priceLong = price.multiply(YUAN_2_FEN_RATIO).longValue();
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getGoodsStock() {
        return goodsStock;
    }

    public void setGoodsStock(Integer goodsStock) {
        this.goodsStock = goodsStock;
    }

    public String getOpUserId() {
        return opUserId;
    }

    public void setOpUserId(String opUserId) {
        this.opUserId = opUserId;
    }

    public String getOpUserName() {
        return opUserName;
    }

    public void setOpUserName(String opUserName) {
        this.opUserName = opUserName;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public Integer getOrderMaxNum() {
        return orderMaxNum;
    }

    public void setOrderMaxNum(Integer orderMaxNum) {
        this.orderMaxNum = orderMaxNum;
    }

    public String getPackageUnit() {
        return packageUnit;
    }

    public void setPackageUnit(String packageUnit) {
        this.packageUnit = packageUnit;
    }

    public Integer getOrderMinNum() {
        return orderMinNum;
    }

    public void setOrderMinNum(Integer orderMinNum) {
        this.orderMinNum = orderMinNum;
    }
}
