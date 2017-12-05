package com.cxd.sotiy.assistant;

import com.cxd.sotiy.cache.Item;
import com.cxd.sotiy.factory.StatementFactory;
import com.cxd.sotiy.parser.StatementParser;
import com.cxd.sotiy.statement.AbstractStatement;
import org.apache.solr.client.solrj.SolrQuery;

/**
 * sotiy助手
 *
 * @author childe
 * @date 2017/12/5 20:35
 **/
public class SotiyAssistant {

    public static String getQuery(String nameSpace, String id, Object param) {
        AbstractStatement statement = StatementFactory.getStatement(nameSpace,id);
        return StatementParser.parseWhereStatement(statement,param);
    }

    public static Item getSolrQuery(String nameSpace, String id, Object param) {
        AbstractStatement statement = StatementFactory.getStatement(nameSpace,id);
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setFields(statement.getField());
        solrQuery.setQuery(StatementParser.parseWhereStatement(statement,param));
        return new Item(statement.getCollection(),solrQuery);
    }
}
