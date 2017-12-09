package com.cxd.sotiy.node;

import com.cxd.sotiy.common.NullObject;

/**
 * 基本节点
 *
 * @author childe
 * @date 2017/12/2 15:24
 **/
public abstract class AbstractNode  implements NullObject{

    /**
     * 去除前后空白
     */
    protected Boolean trim = Boolean.FALSE;

    /**
     * 预处理条件语句
     */
    protected String preCondition = "";

    public boolean isTrim() {
        return trim;
    }

    public Boolean getTrim() {
        return trim;
    }

    public void setTrim(Boolean trim) {
        this.trim = trim;
    }

    public String getPreCondition() {
        return preCondition;
    }

    public void setPreCondition(String preCondition) {
        if (preCondition == null) {
            return;
        }
        this.preCondition = preCondition;
    }

    public StringBuffer getPretreatmentBuffer() {
        if (isNullObject()) {
            return new StringBuffer();
        }
        StringBuffer sb = new StringBuffer();
        if (getTrim()) {
            setPreCondition(getPreCondition().trim());
        }
        sb.append(getPreCondition());
        return sb;
    }

    @Override
    public boolean isNullObject() {
        return false;
    }
}
