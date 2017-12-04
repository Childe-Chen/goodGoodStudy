package com.cxd.sotiy.handler.document;

import com.cxd.solrWay.constants.ElementConstant;
import com.cxd.sotiy.constants.AttributeConstant;
import com.cxd.sotiy.constants.NodeConstant;
import com.cxd.sotiy.exception.AbstractSotiyException;
import com.cxd.sotiy.exception.SotiyAttrNotExistsException;
import com.cxd.sotiy.exception.SotiyAttrValueIllegalException;
import com.cxd.sotiy.exception.SotiyLabeIllegalException;
import com.cxd.sotiy.handler.element.IElementHandler;
import com.cxd.sotiy.handler.element.SelectElementHandler;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;

import java.util.List;

/**
 * xml
 *
 * @author childe
 * @date 2017/12/2 17:24
 **/
public class XmlDocumentHandler implements IDocumentHandler {

    @Override
    public void doHandle(Document document) throws AbstractSotiyException {

        rootCheck(document.getRootElement());

        List<Element> elements = document.getRootElement().elements(ElementConstant.SELECT);

        IElementHandler elementHandler = new SelectElementHandler();
        for (Element ele : elements) {
            elementHandler.handle(ele);
        }

    }

    private void rootCheck(Element rootEle) throws SotiyAttrNotExistsException, SotiyAttrValueIllegalException {
        Attribute namespaceAttr = rootEle.attribute(AttributeConstant.NAMESPACE);
        if (namespaceAttr == null) {
            throw new SotiyAttrNotExistsException(ElementConstant.NAMESPACE + "缺失!");
        }

        String namespace = namespaceAttr.getValue();
        if (namespace == null || namespace.length() == 0) {
            throw new SotiyAttrValueIllegalException(ElementConstant.NAMESPACE + "未配置!");
        }
    }
}
