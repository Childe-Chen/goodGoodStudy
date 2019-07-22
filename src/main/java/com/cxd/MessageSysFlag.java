package com.cxd;

import com.alibaba.fastjson.JSON;
import net.sf.json.JSONObject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * desc
 *
 * @author fanyin
 * @date 2019/4/24 17:01
 **/
public class MessageSysFlag {
    public final static int COMPRESSED_FLAG = 0x1;
    public final static int MULTI_TAGS_FLAG = 0x1 << 1;
    public final static int TRANSACTION_NOT_TYPE = 0;
    public final static int TRANSACTION_PREPARED_TYPE = 0x1 << 2;
    public final static int TRANSACTION_COMMIT_TYPE = 0x2 << 2;
    public final static int TRANSACTION_ROLLBACK_TYPE = 0x3 << 2;

    public static int getTransactionValue(final int flag) {
        return flag & TRANSACTION_ROLLBACK_TYPE;
    }

    public static int resetTransactionValue(final int flag, final int type) {
        return (flag & (~TRANSACTION_ROLLBACK_TYPE)) | type;
    }

    public static int clearCompressedFlag(final int flag) {
        return flag & (~COMPRESSED_FLAG);
    }

    public static void main(String[] args) {
//        System.out.println(resetTransactionValue(0, 0));

//        BigDecimal realPay = BigDecimal.valueOf(paymentInfo.getRealPay());
//        BigDecimal feeRate = BigDecimal.valueOf(commissionDO.getCommissionRate() / 100);

//        商品id(id)	418854831
//        店铺Id(kdt_id)	19008705
//
//        商品id(id)	459750335
//        店铺Id(kdt_id)	41788401



        DD dd = new DD();
        dd.source = "wx_app";
        dd.groupId = 2748365L;
        dd.buyerId = 1234567L;
        dd.fansId = 1234567L;
        dd.source = "spotlight_platform";



        System.out.println(JSON.toJSONString(dd));
    }

    static class DD {
        private Long groupId;
        /**
         * buyer_id
         */
        private Long buyerId;
        /**
         * fans_id
         */
        private Long fansId;
        /**
         * fans_type
         */
        private Integer fansType;
        /**
         * 领取来源
         */
        private String source;

        /**
         * 领券时,指定面额, 单位为分
         */
        private Long assignValue;

        public Long getGroupId() {
            return groupId;
        }

        public void setGroupId(Long groupId) {
            this.groupId = groupId;
        }

        public Long getBuyerId() {
            return buyerId;
        }

        public void setBuyerId(Long buyerId) {
            this.buyerId = buyerId;
        }

        public Long getFansId() {
            return fansId;
        }

        public void setFansId(Long fansId) {
            this.fansId = fansId;
        }

        public Integer getFansType() {
            return fansType;
        }

        public void setFansType(Integer fansType) {
            this.fansType = fansType;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public Long getAssignValue() {
            return assignValue;
        }

        public void setAssignValue(Long assignValue) {
            this.assignValue = assignValue;
        }
    }
}
