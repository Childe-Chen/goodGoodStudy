package com.cxd.sotiy.handler.node;

import com.cxd.sotiy.node.AbstractNode;
import com.cxd.sotiy.node.NullNode;
import com.cxd.sotiy.statement.AbstractStatement;
import org.dom4j.Element;

/**
 * desc
 *
 * @author childe
 * @date 2017/12/2 17:18
 **/
public interface INodeHandler {

    /**
     * 解析elemnt
     * @param element
     * @param statement
     * @return
     */
    default Element handle(Element element, AbstractStatement statement) {
        if (element == null) {
            return element;
        }

        //执行解析，解析操作element
        return doHandle(element, statement);
    }

    /**
     * 执行解析
     * @param element
     * @param statement
     * @return
     */
    Element doHandle(Element element, AbstractStatement statement);
}
