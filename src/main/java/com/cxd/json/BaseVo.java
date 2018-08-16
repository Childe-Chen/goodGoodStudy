package com.cxd.json;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * desc
 *
 * @author childe
 * @date 2018/8/3 15:31
 **/
public class BaseVo implements Serializable {

    private static final long serialVersionUID = 1234567899348710205L;

    private String id;

    private Integer lastVer;

    public static transient BigDecimal YUAN_2_FEN_RATIO = new BigDecimal(100);

    public String getId() {
        return id;
    }

    @JSONField(name = "idLong", serialize = false)
    public Long getIdLong() {
        try {
            return Long.parseLong(id);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @JSONField(name = "idLong", deserialize = false)
    public void setId(long id) {
        this.id = String.valueOf(id);
    }

    public void setId(String id) {
        this.id = id;
    }

    public String get_id() {
        return id;
    }

    /**
     * 用fastjson做反序列化时，若需要转换类中未定义的属性，需要加此注解
     */
    @JSONField(name="_id")
    public void set_id(String _id) {
        this.id = _id;
    }

    public Integer getLastVer() {
        return lastVer;
    }

    public void setLastVer(Integer lastVer) {
        this.lastVer = lastVer;
    }

}
