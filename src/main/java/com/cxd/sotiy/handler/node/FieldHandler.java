package com.cxd.sotiy.handler.node;

import com.cxd.solrWay.constants.ElementConstant;
import com.cxd.sotiy.constants.AttributeConstant;
import com.cxd.sotiy.constants.NodeConstant;
import com.cxd.sotiy.node.AbstractNode;
import com.cxd.sotiy.node.FieldNode;
import com.cxd.sotiy.statement.AbstractStatement;
import org.dom4j.Element;

/**
 * desc
 *
 * @author childe
 * @date 2017/12/2 17:25
 **/
public class FieldHandler implements INodeHandler {

    @Override
    public Element doHandle(Element element, AbstractStatement statement) {
        Element fieldEle = element.element(NodeConstant.FIELD);

        AbstractNode fieldNode = new FieldNode();

        if (fieldEle == null) {
            fieldNode.setPreCondition("*");
            statement.setField(fieldNode.getPretreatmentBuffer().toString());
            return element;
        }

        fieldNode.setPrefix(fieldEle.attributeValue(AttributeConstant.PREFIX));
        fieldNode.setSuffix(fieldEle.attributeValue(AttributeConstant.SUFFIX));
        fieldNode.setTrim(Boolean.valueOf(fieldEle.attributeValue(AttributeConstant.TRIM,"true")));
        fieldNode.setPreCondition(fieldEle.getText());

        statement.setField(fieldNode.getPretreatmentBuffer().toString());

        return element;
    }
}
