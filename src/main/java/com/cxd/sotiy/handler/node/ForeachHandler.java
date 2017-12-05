package com.cxd.sotiy.handler.node;

import com.cxd.sotiy.constants.AttributeConstant;
import com.cxd.sotiy.constants.NodeConstant;
import com.cxd.sotiy.exception.AbstractSotiyException;
import com.cxd.sotiy.exception.SotiyAttrValueIllegalException;
import com.cxd.sotiy.node.AbstractNode;
import com.cxd.sotiy.node.ForeachNode;
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
 * @date 2017/12/2 23:24
 **/
public class ForeachHandler implements INodeHandler {

    public static final Logger LOGGER = LoggerFactory.getLogger(ForeachHandler.class);

    @Override
    public Element doHandle(Element element, AbstractStatement statement) {

        List<Element> foreachNodeList = element.elements(NodeConstant.FOREACH);

        foreachNodeList.stream().forEach(foreachNodeElement -> replaceNode(element, foreachNodeElement));

        return element;
    }

    private void replaceNode(Element parentElement, Element foreachNodeElement) {
        ForeachNode foreachNode = new ForeachNode();
        foreachNode.setPrefix(foreachNodeElement.attributeValue(AttributeConstant.PREFIX));
        foreachNode.setSuffix(foreachNodeElement.attributeValue(AttributeConstant.SUFFIX));
        foreachNode.setTrim(Boolean.valueOf(foreachNodeElement.attributeValue(AttributeConstant.TRIM,"true")));
        foreachNode.setPreCondition(foreachNodeElement.getTextTrim());
        String expression = foreachNodeElement.attributeValue(AttributeConstant.EXPRESSION);
        if (expression == null || expression.trim().length() == 0) {
            LOGGER.error(AttributeConstant.EXPRESSION + "未配置");
            return;
        }
        foreachNode.setExpression(expression);

        int index = parentElement.elements().indexOf(foreachNodeElement);
        parentElement.elements().add(index,new DefaultText(foreachNode.getPretreatmentBuffer().toString()));
        parentElement.elements().remove(index);
    }
}
