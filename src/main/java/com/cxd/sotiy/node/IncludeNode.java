package com.cxd.sotiy.node;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * include节点
 *
 * @author childe
 * @date 2017/12/2 15:52
 **/
public class IncludeNode extends AbstractNode {

    /**
     * 前缀
     */
    protected String prefix = "";

    /**
     * 后缀
     */
    protected String suffix = "";


    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        if (prefix == null) {
            return;
        }
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        if (suffix == null) {
            return;
        }
        this.suffix = suffix;
    }

    @Override
    public StringBuffer getPretreatmentBuffer() {
        if (isNullObject()) {
            return new StringBuffer();
        }
        StringBuffer sb = new StringBuffer();
        sb.append(getPrefix());
        if (getTrim()) {
            setPreCondition(getPreCondition().trim());
        }
        sb.append(getPreCondition());
        sb.append(getSuffix());
        return sb;
    }



//    private List<AbstractNode> nodeList = new LinkedList<>();
//
//    public void addNode(AbstractNode node) {
//        nodeList.add(node);
//    }
//
//    @Override
//    public StringBuffer getPretreatmentBuffer() {
//        if (!getPreCondition().equals("")) {
//            return super.getPretreatmentBuffer();
//        }
//        setPreCondition(nodeList.parallelStream().map(node -> node.getPretreatmentBuffer()).collect(Collectors.joining()));
//        return super.getPretreatmentBuffer();
//    }
}
