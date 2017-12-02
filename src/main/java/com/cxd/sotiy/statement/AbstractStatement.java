package com.cxd.sotiy.statement;

import com.cxd.sotiy.common.NullObject;

/**
 * 抽象语句
 *
 * @author childe
 * @date 2017/12/2 16:53
 **/
public abstract class AbstractStatement implements NullObject {

    private static final String DOT = ".";

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
     * 待渲染语句
     */
    private String statement;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(Class<?> targetClass) {
        this.targetClass = targetClass;
    }

    public void setTargetClass(String targetClassName) {
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
