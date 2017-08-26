package com.cxd.solrWay.parse.v1.statementParser;

import com.cxd.solrWay.constants.AttributeConstant;
import com.cxd.solrWay.constants.ElementConstant;
import com.cxd.solrWay.exception.SotiyAttrNotExistsException;
import com.cxd.solrWay.exception.SotiyAttrValueIllegalException;
import com.cxd.solrWay.exception.SotiyLabeIllegalException;
import com.cxd.solrWay.parse.v1.statement.SolrStatementV1;
import com.cxd.solrWay.statement.StatementFactory;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by childe on 2017/7/17.
 */
public class StatementParser {

    static Logger logger = LoggerFactory.getLogger(StatementParser.class);

    public static void parseStatement(Document doc) throws Exception {
        Element rootEle = doc.getRootElement();

        Attribute namespaceAttr = rootEle.attribute(ElementConstant.NAMESPACE);
        if (namespaceAttr == null) {
            throw new SotiyAttrNotExistsException(ElementConstant.NAMESPACE + "缺失!");
        }

        String namespace = namespaceAttr.getValue();
        if (namespace == null || namespace.length() == 0) {
            throw new SotiyAttrValueIllegalException(ElementConstant.NAMESPACE + "未配置!");
        }

        List<Element> elements = rootEle.elements(ElementConstant.SELECT);

        for (Element ele : elements) {
            Attribute idAttr = ele.attribute(ElementConstant.ID);
            if (idAttr == null) {
                throw new SotiyAttrNotExistsException(ElementConstant.NAMESPACE + "空间下，" + ElementConstant.ID + "缺失!");
            }
            String id = idAttr.getValue();
            if (id == null || id.length() == 0) {
                throw new SotiyAttrValueIllegalException(ElementConstant.NAMESPACE + "空间下，" + ElementConstant.ID + "未配置!");
            }

            String name = ele.getName();
            if (!ElementConstant.SELECT.equals(name)) {
                throw new SotiyLabeIllegalException(ElementConstant.NAMESPACE + "空间下，存在非法标签：" + name);
            }


            SolrStatementV1 solrStatement = new SolrStatementV1();
            solrStatement.setNamespace(namespace);
            solrStatement.setId(id);
            solrStatement.setPath(doc.getPath());
            if (logger.isWarnEnabled()) {
                logger.warn(solrStatement.getStatement());
            }

            handleInclude(ele, solrStatement);

            solrStatement.setStatement(ele.getTextTrim());

            StatementFactory.put(solrStatement);
        }

    }

    private static void handleInclude(Element ele, SolrStatementV1 solrStatement) throws SotiyAttrValueIllegalException {
        Element includeEle = ele.element(ElementConstant.INCLUDE);

        if (includeEle == null) {
            return;
        }

        if (includeEle.getTextTrim() == null || includeEle.getTextTrim().length() == 0) {
            return;
        }

        SolrStatementV1 sub = new SolrStatementV1();
        sub.setId(solrStatement.getId());
        sub.setNamespace(solrStatement.getNamespace());
        sub.setPath(solrStatement.getPath());

        sub.setStatement(includeEle.getTextTrim());
        solrStatement.setSubStatement(sub);

        Attribute prefix = includeEle.attribute(AttributeConstant.PREFIX);
        if (prefix == null || prefix.getValue() == null || prefix.getValue().length() == 0) {
            throw new SotiyAttrValueIllegalException(ElementConstant.NAMESPACE + "空间下，" + sub.getId() + "."+ AttributeConstant.PREFIX + "未配置!");
        }
        solrStatement.setSubPrefix(prefix.getValue());

        Attribute suffix = includeEle.attribute(AttributeConstant.SUFFIX);
        if (suffix == null || suffix.getValue() == null || suffix.getValue().length() == 0) {
            throw new SotiyAttrValueIllegalException(ElementConstant.NAMESPACE + "空间下，" + sub.getId() + "."+ AttributeConstant.SUFFIX + "未配置!");
        }

        solrStatement.setSubSuffix(suffix.getValue());

        ele.remove(includeEle);
    }
}
