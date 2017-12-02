package com.cxd.sotiy.statement;

/**
 * NullStatement
 *
 * @author childe
 * @date 2017/12/2 17:02
 **/
public class NullStatement extends AbstractStatement {

    private static final NullStatement NULL_STATEMENT = new NullStatement();

    private NullStatement() {
    }

    @Override
    public boolean isNullObject() {
        return true;
    }

    public static NullStatement getNullStatement() {
        return NULL_STATEMENT;
    }
}
