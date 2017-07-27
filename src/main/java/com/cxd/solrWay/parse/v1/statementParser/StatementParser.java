package com.cxd.solrWay.parse.v1.statementParser;

import com.cxd.solrWay.constants.SotiyConstant;
import com.cxd.solrWay.exception.SotiyAttrNotExistsException;
import com.cxd.solrWay.exception.SotiyAttrValueIllegalException;
import com.cxd.solrWay.exception.SotiyLabeIllegalException;
import com.cxd.solrWay.statement.SolrStatement;
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

        Attribute namespaceAttr = rootEle.attribute(SotiyConstant.NAMESPACE);
        if (namespaceAttr == null) {
            throw new SotiyAttrNotExistsException(SotiyConstant.NAMESPACE + "缺失!");
        }

        String namespace = namespaceAttr.getValue();
        if (namespace == null || namespace.length() == 0) {
            throw new SotiyAttrValueIllegalException(SotiyConstant.NAMESPACE + "未配置!");
        }

        List<Element> elements = rootEle.elements(SotiyConstant.SELECT);

        for (Element ele : elements) {
            Attribute idAttr = ele.attribute(SotiyConstant.ID);
            if (idAttr == null) {
                throw new SotiyAttrNotExistsException(SotiyConstant.NAMESPACE + "空间下，" + SotiyConstant.ID + "缺失!");
            }
            String id = idAttr.getValue();
            if (id == null || id.length() == 0) {
                throw new SotiyAttrValueIllegalException(SotiyConstant.NAMESPACE + "空间下，" + SotiyConstant.ID + "未配置!");
            }

            String name = ele.getName();
            if (!SotiyConstant.SELECT.equals(name)) {
                throw new SotiyLabeIllegalException(SotiyConstant.NAMESPACE + "空间下，存在非法标签：" + name);
            }

            String statement = ele.getTextTrim();

            SolrStatement solrStatement = new SolrStatement();
            solrStatement.setNamespace(namespace);
            solrStatement.setId(id);
            solrStatement.setStatement(statement);
            solrStatement.setPath(doc.getPath());
            if (logger.isWarnEnabled()) {
                logger.warn(solrStatement.getStatement());
            }
            StatementFactory.put(solrStatement);
        }

    }
}
