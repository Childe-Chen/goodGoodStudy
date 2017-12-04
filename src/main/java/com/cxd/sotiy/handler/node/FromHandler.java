package com.cxd.sotiy.handler.node;

import com.cxd.sotiy.constants.AttributeConstant;
import com.cxd.sotiy.constants.NodeConstant;
import com.cxd.sotiy.node.AbstractNode;
import com.cxd.sotiy.node.FromNode;
import com.cxd.sotiy.statement.AbstractStatement;
import org.dom4j.Element;

/**
 * desc
 *
 * @author childe
 * @date 2017/12/4 19:52
 **/
public class FromHandler implements INodeHandler {
    @Override
    public AbstractNode doHandle(Element element, AbstractStatement statement) {
        Element fromEle = element.element(NodeConstant.FROM);

        AbstractNode fromNode = new FromNode();

        if (fromEle == null) {
            statement.setField(fromNode.getPretreatmentBuffer().toString());
            return fromNode;
        }

        fromNode.setPrefix(fromEle.attributeValue(AttributeConstant.PREFIX));
        fromNode.setSuffix(fromEle.attributeValue(AttributeConstant.SUFFIX));
        fromNode.setTrim(Boolean.valueOf(fromEle.attributeValue(AttributeConstant.TRIM)));
        fromNode.setPreCondition(fromEle.getText());

        statement.setCollection(fromNode.getPretreatmentBuffer().toString());

        return fromNode;
    }
}
