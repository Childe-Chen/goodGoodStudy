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
