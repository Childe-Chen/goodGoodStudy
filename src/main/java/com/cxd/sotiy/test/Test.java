package com.cxd.sotiy.test;

import com.cxd.sotiy.assistant.SotiyAssistant;
import com.cxd.sotiy.factory.StatementFactory;
import com.cxd.sotiy.loader.ResourceLoader;
import com.cxd.sotiy.statement.AbstractStatement;

import java.util.HashMap;

/**
 * desc
 *
 * @author childe
 * @date 2017/12/5 19:29
 **/
public class Test {
    public static void main(String[] args) {
        ResourceLoader resourceLoader = new ResourceLoader();
        resourceLoader.load();

        AbstractStatement statement = StatementFactory.getStatement("com.cxd.sotiy.test.Commodity","querySolrSpuWithSkuList");
        System.out.println(statement.getKey());
        System.out.println(statement.getField());
        System.out.println(statement.getCollection());
        System.out.println(statement.getWhereStatement());

        String queryStr = SotiyAssistant.getQuery("com.cxd.sotiy.test.Commodity","querySolrSpuWithSkuList",new HashMap<>());
        System.out.println(queryStr);
    }
}
