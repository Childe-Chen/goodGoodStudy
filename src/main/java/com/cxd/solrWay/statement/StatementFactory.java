package com.cxd.solrWay.statement;

import com.cxd.solrWay.exception.SotiyKeyDuplicateException;
import com.cxd.solrWay.parse.v1.statement.SolrStatementV1;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by childe on 2017/7/14.
 */
public class StatementFactory {

    private static Map<String, SolrStatement> id2StatementMap = new HashMap<>();

    static {
        startWork();
    }

    private static void startWork() {
        ResourceLoader.load();
    }

    public static void put(SolrStatement solrStatement) throws SotiyKeyDuplicateException {
        if (id2StatementMap.get(solrStatement.getKey()) != null) {
            throw new SotiyKeyDuplicateException(solrStatement.getKey() + "重复!");
        }

        id2StatementMap.put(solrStatement.getKey(), solrStatement);
    }

    public static SolrStatementV1 getStatementV1(String key) {
        SolrStatementV1 solrStatement = (SolrStatementV1)id2StatementMap.get(key);

        return solrStatement;
    }

}
