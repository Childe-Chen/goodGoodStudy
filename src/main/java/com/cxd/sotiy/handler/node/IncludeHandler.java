package com.cxd.sotiy.handler.node;

import com.cxd.sotiy.constants.AttributeConstant;
import com.cxd.sotiy.constants.NodeConstant;
import com.cxd.sotiy.node.AbstractNode;
import com.cxd.sotiy.node.ForeachNode;
import com.cxd.sotiy.node.IncludeNode;
import com.cxd.sotiy.statement.AbstractStatement;
import org.dom4j.Element;
import org.dom4j.tree.DefaultText;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * desc
 *
 * @author childe
 * @date 2017/12/2 23:25
 **/
public class IncludeHandler implements INodeHandler {

    public static final Logger LOGGER = LoggerFactory.getLogger(ForeachHandler.class);

    @Override
    public Element doHandle(Element element, AbstractStatement statement) {


        List<Element> includeNodeList = element.elements(NodeConstant.INCLUDE);

        includeNodeList.stream().forEach(includeNodeElement -> replaceNode(element, includeNodeElement, statement));

        return element;
    }

    private void replaceNode(Element parentElement, Element includeNodeElement, AbstractStatement statement) {
        includeNodeElement = handleChildNode(includeNodeElement, statement);

        IncludeNode includeNode = new IncludeNode();
        includeNode.setPrefix(includeNodeElement.attributeValue(AttributeConstant.PREFIX));
        includeNode.setSuffix(includeNodeElement.attributeValue(AttributeConstant.SUFFIX));
        includeNode.setTrim(Boolean.valueOf(includeNodeElement.attributeValue(AttributeConstant.TRIM,"true")));
        includeNode.setPreCondition(includeNodeElement.getTextTrim());

        int index = parentElement.elements().indexOf(includeNodeElement);
        parentElement.elements().add(index,new DefaultText(includeNode.getPretreatmentBuffer().toString()));
        parentElement.elements().remove(index);
    }

    private Element handleChildNode(Element includeNodeElement, AbstractStatement statement) {
        includeNodeElement = new IncludeHandler().handle(includeNodeElement,statement);
        includeNodeElement = new ForeachHandler().handle(includeNodeElement,statement);
        return includeNodeElement;
    }
}
