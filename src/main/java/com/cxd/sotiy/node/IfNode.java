package com.cxd.sotiy.node;

/**
 * desc
 *
 * @author childe
 * @date 2017/12/9 14:22
 **/
public class IfNode extends IncludeNode {
    private static final String IF_PREFIX = "#if(";

    private static final String IF_SUFFIX = ")";

    private static final String IF_END = "#end";

    /**
     * if表达式
     * 比如：
     * $!{adCodes}
     */
    private String expression;

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
        setPrefix(getPrefix() + IF_PREFIX + getExpression() + IF_SUFFIX);
        setSuffix(getSuffix() + IF_END);
    }
}
