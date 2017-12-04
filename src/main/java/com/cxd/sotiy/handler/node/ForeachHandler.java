package com.cxd.sotiy.handler.node;

import com.cxd.sotiy.node.AbstractNode;
import com.cxd.sotiy.node.ForeachNode;
import com.cxd.sotiy.statement.AbstractStatement;
import org.dom4j.Element;

/**
 * desc
 *
 * @author childe
 * @date 2017/12/2 23:24
 **/
public class ForeachHandler implements INodeHandler {

    @Override
    public AbstractNode doHandle(Element element, AbstractStatement statement) {

        //        Element where = ele.element("where");
//        Element include = where.element("include");
//        Element include1 = include.element("include");
//        List<Element> foreachList = include1.elements("foreach");
//        for (Element e: foreachList) {
//            int index = include1.elements().indexOf(e);
//            include1.elements().add(index,new DefaultText("F1FFFFFFF"));
//            include1.elements().remove(index);
//        }

        AbstractNode foreachNode = new ForeachNode();



        return foreachNode;
    }
}
