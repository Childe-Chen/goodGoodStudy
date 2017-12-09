package com.cxd.sotiy.node;

/**
 * foreach节点
 *
 * @author childe
 * @date 2017/12/2 15:53
 **/
public final class ForeachNode extends IncludeNode {

    private static final String FOR_EACH_PREFIX = "#foreach(";

    private static final String FOR_EACH_SUFFIX = ")";

    private static final String FOR_EACH_END = "#end";

    //#foreach($temp in $!{adCodes}) #if($velocityCount > 1),#end$temp #end
    /**
     * foreach表达式
     * 比如：
     * $temp in $!{adCodes}
     */
    private String expression;

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
        super.setPrefix(getPrefix() + FOR_EACH_PREFIX + getExpression() + FOR_EACH_SUFFIX);
        super.setSuffix(getSuffix() + FOR_EACH_END);
    }
}
