package com.cxd.solrVelocity.test;

import com.cxd.solrVelocity.parse.v1.solrParser.SolrQueryParser;
import com.cxd.solrVelocity.statement.SolrStatement;
import com.cxd.solrVelocity.statement.StatementFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by childe on 2017/7/14.
 */
public class Main {

    public static void main(String[] args) {
        OrderVo orderVo = new OrderVo();
        orderVo.setId("0001");
        orderVo.setNo("no1");
        List<String> statusList = new ArrayList<>(3);
        statusList.add("1");
        statusList.add("2");
        statusList.add("3");
        orderVo.setStatusList(statusList);
        SolrStatement solrStatement = StatementFactory.getStatement("sotiy1.getDemo");
        System.out.println(SolrQueryParser.parse(solrStatement,orderVo));
    }
}
