package com.cxd.sotiy.factory;

import com.cxd.sotiy.statement.AbstractStatement;
import com.cxd.sotiy.statement.NullStatement;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 语句工厂
 *
 * @author childe
 * @date 2017/12/2 16:52
 **/
public class StatementFactory {

    private static final Map<String,AbstractStatement> STATEMENT_MAP = new ConcurrentHashMap<>();

    public static AbstractStatement getStatement(String nameSpace, String id) {
        return STATEMENT_MAP.getOrDefault(nameSpace + AbstractStatement.DOT + id, NullStatement.getNullStatement());
    }

    public static void addStatement(AbstractStatement statement) {
        STATEMENT_MAP.put(statement.getKey(),statement);
    }
}
