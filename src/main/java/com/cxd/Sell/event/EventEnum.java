package com.cxd.Sell.event;

/**
 * Created by childe on 2017/6/21.
 */
public enum EventEnum {
    PAY ("支付事件",(short)0),
    REFUND ("退币事件",(short)1),
    COMMDITY_SELECTED ("商品选择事件",(short)2),
    SHIP ("出货事件",(short)3),
    CONTINUE_PAY ("金额不足，继续支付事件",(short)4),
    NOT_FOUND_COMMODITY ("金额不足，继续支付事件",(short)6),
    NO_STOCK("库存不足事件",(short)5);

    private String description;
    private Short type;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Short getType() {

        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    private EventEnum(String description, Short type){
        this.description = description;
        this.type = type;
    }

    public static EventEnum codeOf(Short type){
        if(type == null){
            return null;
        }
        for(EventEnum item : EventEnum.values()){
            if(item.getType().shortValue() == type.shortValue()){
                return item;
            }
        }
        return null;
    }
}
