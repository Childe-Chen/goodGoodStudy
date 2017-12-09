package com.cxd.sotiy.handler.node;

import com.cxd.sotiy.constants.AttributeConstant;
import com.cxd.sotiy.constants.NodeConstant;
import com.cxd.sotiy.node.IfNode;
import com.cxd.sotiy.node.IncludeNode;
import com.cxd.sotiy.statement.AbstractStatement;
import com.google.common.base.CharMatcher;
import org.dom4j.Element;
import org.dom4j.tree.DefaultText;

import java.util.List;

/**
 * desc
 *
 * @author childe
 * @date 2017/12/9 14:39
 **/
public class IfHandler implements INodeHandler {

    @Override
    public Element doHandle(Element element, AbstractStatement statement) {
        List<Element> ifNodeList = element.elements(NodeConstant.IF);

        ifNodeList.stream().forEach(ifNodeElement -> replaceNode(element, ifNodeElement, statement));

        return element;
    }

    private void replaceNode(Element parentElement, Element ifNodeElement, AbstractStatement statement) {
        ifNodeElement = handleChildNode(ifNodeElement, statement);

        IfNode ifNode = new IfNode();
        ifNode.setPrefix(ifNodeElement.attributeValue(AttributeConstant.PREFIX));
        ifNode.setSuffix(ifNodeElement.attributeValue(AttributeConstant.SUFFIX));
        ifNode.setExpression(ifNodeElement.attributeValue(AttributeConstant.EXPRESSION));
        ifNode.setTrim(Boolean.valueOf(ifNodeElement.attributeValue(AttributeConstant.TRIM,"true")));
        ifNode.setPreCondition(CharMatcher.WHITESPACE.trimAndCollapseFrom(ifNodeElement.getTextTrim(), ' '));

        int index = parentElement.elements().indexOf(ifNodeElement);
        parentElement.elements().add(index,new DefaultText(ifNode.getPretreatmentBuffer().toString()));
        parentElement.elements().remove(index);
    }

    private Element handleChildNode(Element ifNodeElement, AbstractStatement statement) {
        ifNodeElement = new IncludeHandler().handle(ifNodeElement,statement);
        ifNodeElement = new IfHandler().handle(ifNodeElement,statement);
        ifNodeElement = new ForeachHandler().handle(ifNodeElement,statement);
        return ifNodeElement;
    }
}
