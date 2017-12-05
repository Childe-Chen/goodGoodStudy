package com.cxd.sotiy.parser;

import com.cxd.solrWay.parse.v1.solrParser.VelocityEngineFactory;
import com.cxd.solrWay.parse.v1.statement.SolrStatementV1;
import com.cxd.solrWay.utils.BeanUtil;
import com.cxd.sotiy.statement.AbstractStatement;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * desc
 *
 * @author childe
 * @date 2017/12/5 20:45
 **/
public class StatementParser {

    public static String parseWhereStatement(AbstractStatement statement, Object paramObject) {
        Map<String,Object> param = BeanUtil.populateMap(new HashMap<>(),paramObject);
        VelocityContext context = new VelocityContext(param);
        return getRenderResult(statement, context);
    }

    private static String getRenderResult(AbstractStatement statement, VelocityContext context) {
        Writer writer = new StringWriter();
        VelocityEngineFactory.getVelocityEngine().evaluate(context,writer,statement.getKey(),statement.getWhereStatement());
        return writer.toString();
    }
}
