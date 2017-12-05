package com.cxd.sotiy.statement;

import com.cxd.sotiy.common.NullObject;
import com.cxd.sotiy.node.FieldNode;
import com.cxd.sotiy.node.FromNode;
import com.cxd.sotiy.node.WhereNode;

/**
 * 抽象语句
 *
 * @author childe
 * @date 2017/12/2 16:53
 **/
public abstract class AbstractStatement implements NullObject {

    public static final String DOT = ".";

    /**
     * 命名空间
     */
    private String namespace;


    /**
     * 查询结果转换成的目标类的全限定名
     */
    private Class<?> targetClass;

    /**
     * 查询语句ID
     */
    private String id;

    /**
     * FieldNode
     * 查询字段
     */
    private String field;

    /**
     * FromNode
     * 索引名
     * the Solr collection to query
     */
    private String collection;

    /**
     * WhereNode
     * 待渲染语句
     */
    private String whereStatement;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public void setField(FieldNode node) {
        this.field = node.getPretreatmentBuffer().toString();
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public void setCollection(FromNode node) {
        this.collection = node.getPretreatmentBuffer().toString();
    }

    public String getWhereStatement() {
        return whereStatement;
    }

    public void setWhereStatement(String whereStatement) {
        this.whereStatement = whereStatement;
    }

    public void setWhereStatement(WhereNode node) {
        this.whereStatement = node.getPretreatmentBuffer().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        setTargetClass(namespace);
        this.namespace = namespace;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    private void setTargetClass(Class<?> targetClass) {
        this.targetClass = targetClass;
    }

    private void setTargetClass(String targetClassName) {
        try {
            setTargetClass(Class.forName(targetClassName));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 语句唯一标志
     * namespace.id
     * @return String
     */
    public String getKey() {
        return namespace + DOT + id;
    }

    @Override
    public boolean isNullObject() {
        return false;
    }
}
