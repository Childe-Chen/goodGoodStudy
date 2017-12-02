package com.cxd.sotiy.handler.document;

import com.cxd.solrWay.constants.ElementConstant;
import org.dom4j.Document;
import org.dom4j.Element;

/**
 * desc
 *
 * @author childe
 * @date 2017/12/2 17:18
 **/
public interface IDocumentHandler {

    default void handle(Document document) {
        if (document == null) {
            return;
        }

        Element root = document.getRootElement();
        if (!root.getName().equals(ElementConstant.SOTIY)) {
            return;
        }

        doHandle(document);
    }

    void doHandle(Document document);
}
