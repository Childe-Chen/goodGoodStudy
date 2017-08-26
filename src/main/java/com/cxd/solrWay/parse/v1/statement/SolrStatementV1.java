package com.cxd.solrWay.parse.v1.statement;

import com.cxd.solrWay.statement.SolrStatement;

/**
 *
 * Created by childe on 2017/7/14.
 */
public class SolrStatementV1 extends SolrStatement {

    //语句
    private String statement;

    private SolrStatementV1 subStatement;

    private String subPrefix;

    private String subSuffix;


    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public SolrStatementV1 getSubStatement() {
        return subStatement;
    }

    public void setSubStatement(SolrStatementV1 subStatement) {
        this.subStatement = subStatement;
    }

    public String getSubPrefix() {
        return subPrefix;
    }

    public void setSubPrefix(String subPrefix) {
        this.subPrefix = subPrefix;
    }

    public String getSubSuffix() {
        return subSuffix;
    }

    public void setSubSuffix(String subSuffix) {
        this.subSuffix = subSuffix;
    }
}
