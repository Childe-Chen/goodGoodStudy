package com.cxd.solrVelocity.statement;

/**
 *
 * Created by childe on 2017/7/14.
 */
public class SolrStatement {

    //文件路径
    private String path;

    //命名空间
    private String namespace;

    //id
    private String id;

    //语句
    private String statement;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return getKey().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        if (obj.getClass() != getClass()) {
            return false;
        }

        SolrStatement that = (SolrStatement)obj;
        if (that.getNamespace().equals(this.getNamespace()) && that.getId().equals(this.getId())) {
            return true;
        }

        return false;
    }

    public String getKey() {
        return namespace + "." + id;
    }
}
