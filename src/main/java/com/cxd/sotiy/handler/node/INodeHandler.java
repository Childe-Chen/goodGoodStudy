package com.cxd.sotiy.handler.node;

import com.cxd.sotiy.node.AbstractNode;
import com.cxd.sotiy.node.NullNode;
import org.dom4j.Element;

/**
 * desc
 *
 * @author childe
 * @date 2017/12/2 17:18
 **/
public interface INodeHandler {

    default AbstractNode handle(Element element) {
        if (element == null) {
            return NullNode.getNullNode();
        }

        return doHandle(element);
    }

    AbstractNode doHandle(Element element);
}
