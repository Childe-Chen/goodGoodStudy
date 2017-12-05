package com.cxd.sotiy.handler.node;

import com.cxd.sotiy.constants.AttributeConstant;
import com.cxd.sotiy.constants.NodeConstant;
import com.cxd.sotiy.node.AbstractNode;
import com.cxd.sotiy.node.NullNode;
import com.cxd.sotiy.node.WhereNode;
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
 * @date 2017/12/2 23:26
 **/
public class WhereHandler implements INodeHandler {

    public static final Logger LOGGER = LoggerFactory.getLogger(WhereHandler.class);

    @Override
    public Element doHandle(Element element, AbstractStatement statement) {
        List<Element> whereElementList = element.elements(NodeConstant.WHERE);
        if (whereElementList.size() != 1) {
            LOGGER.error("存在多个where节点");
            return element;
        }

        //子节点处理
        Element whereNodeElement = handleChildNode(statement, whereElementList);

        WhereNode whereNode = new WhereNode();
        whereNode.setPrefix(whereNodeElement.attributeValue(AttributeConstant.PREFIX));
        whereNode.setSuffix(whereNodeElement.attributeValue(AttributeConstant.SUFFIX));
        whereNode.setPreCondition(whereNodeElement.getTextTrim());

        int index = element.elements().indexOf(whereNodeElement);
        element.elements().add(index,new DefaultText(whereNode.getPretreatmentBuffer().toString()));
        element.elements().remove(index);

        statement.setWhereStatement(whereNode);

        return element;
    }

    private Element handleChildNode(AbstractStatement statement, List<Element> whereElementList) {
        Element whereNodeElement = new ForeachHandler().handle(whereElementList.get(0),statement);
        whereNodeElement = new IncludeHandler().handle(whereNodeElement,statement);
        return whereNodeElement;
    }
}
