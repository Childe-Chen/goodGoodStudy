package com.cxd.sotiy.handler.element;

import com.cxd.sotiy.constants.AttributeConstant;
import com.cxd.sotiy.constants.NodeConstant;
import com.cxd.sotiy.exception.AbstractSotiyException;
import com.cxd.sotiy.exception.SotiyAttrNotExistsException;
import com.cxd.sotiy.exception.SotiyAttrValueIllegalException;
import com.cxd.sotiy.exception.SotiyLabeIllegalException;
import com.cxd.sotiy.factory.StatementFactory;
import com.cxd.sotiy.statement.AbstractStatement;
import org.dom4j.Attribute;
import org.dom4j.Element;

/**
 * 元素处理
 *
 * @author childe
 * @date 2017/12/2 17:32
 **/
public interface IElementHandler {

    default void handle(Element ele) throws AbstractSotiyException{
        Attribute idAttr = ele.attribute(AttributeConstant.ID);
        if (idAttr == null) {
            throw new SotiyAttrNotExistsException(AttributeConstant.NAMESPACE + "空间下，" + AttributeConstant.ID + "缺失!");
        }
        String id = idAttr.getValue();
        if (id == null || id.length() == 0) {
            throw new SotiyAttrValueIllegalException(AttributeConstant.NAMESPACE + "空间下，" + AttributeConstant.ID + "未配置!");
        }

        String name = ele.getName();
        if (!NodeConstant.SELECT.equals(name)) {
            throw new SotiyLabeIllegalException(AttributeConstant.NAMESPACE + "空间下，存在非法标签：" + name);
        }

        AbstractStatement statement = doHandle(ele);
        statement.setId(id);
        statement.setNamespace(ele.getParent().attributeValue(AttributeConstant.NAMESPACE));

        StatementFactory.addStatement(statement);
    }

    AbstractStatement doHandle(Element ele);
}
