package com.cxd.sotiy.handler.element;

import com.cxd.sotiy.factory.NodeHandlerFactory;
import com.cxd.sotiy.handler.node.INodeHandler;
import com.cxd.sotiy.statement.AbstractStatement;
import com.cxd.sotiy.statement.SelectStatement;
import org.dom4j.Element;

import java.util.List;

/**
 * select处理
 *
 * @author childe
 * @date 2017/12/4 19:43
 **/
public class SelectElementHandler implements IElementHandler {

    @Override
    public AbstractStatement doHandle(Element ele) {
        AbstractStatement selectStatement = new SelectStatement();

        List<INodeHandler> nodeHandlerList = NodeHandlerFactory.getNodeHandlerList();

        nodeHandlerList.stream().forEach(nodeHandler -> nodeHandler.handle(ele,selectStatement));

        return selectStatement;
    }
}
