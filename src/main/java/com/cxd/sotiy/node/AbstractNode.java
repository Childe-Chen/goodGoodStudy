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
     * 前缀
     */
    protected String prefix = "";

    /**
     * 后缀
     */
    protected String suffix = "";

    /**
     * 去除前后空白
     */
    protected Boolean trim = Boolean.FALSE;

    /**
     * 预处理条件语句
     */
    protected String preCondition = "";

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public boolean isTrim() {
        return trim;
    }

    public void setTrim(boolean trim) {
        this.trim = trim;
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
        this.preCondition = preCondition;
    }

    public StringBuffer getPretreatmentBuffer() {
        if (isNullObject()) {
            return new StringBuffer();
        }
        StringBuffer sb = new StringBuffer();
        sb.append(getPrefix());
        if (trim) {
            setPreCondition(getPreCondition().trim());
        }
        sb.append(getPreCondition());
        sb.append(getSuffix());
        return sb;
    }

    @Override
    public boolean isNullObject() {
        return false;
    }
}
