package com.cxd.solrWay.parse.v1.solrParser;

import com.cxd.solrWay.utils.BeanUtil;
import com.cxd.solrWay.parse.v1.statement.SolrStatementV1;
import org.apache.velocity.VelocityContext;

import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by childe on 2017/7/16.
 */
public class SolrQueryParser {

    public static String parse(SolrStatementV1 statement, Object o) {
        Map<String,Object> param = new HashMap<>();
        param = BeanUtil.populateMap(param,o);
        VelocityContext context = new VelocityContext(param);
        String result = getRenderResult(statement, context);
        //目前只有一级一次嵌套
        if (statement.getSubStatement() != null ) {
            String sub = getRenderResult(statement.getSubStatement(), context).trim();
            if (sub != null || sub.length() > 0) {
                if (sub.startsWith("AND") || sub.startsWith("and")) {
                    sub = sub.substring(3);
                }
                result = result + statement.getSubPrefix() +  sub + statement.getSubSuffix();
            }
        }
        return result;
    }

    private static String getRenderResult(SolrStatementV1 statement, VelocityContext context) {
        Writer writer = new StringWriter();
        VelocityEngineFactory.newVelocityEngine().evaluate(context,writer,statement.getKey(),statement.getStatement());
        return writer.toString();
    }
}
