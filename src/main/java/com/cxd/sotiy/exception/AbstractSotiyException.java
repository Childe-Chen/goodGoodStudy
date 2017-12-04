package com.cxd.sotiy.exception;

/**
 * sotiy异常
 *
 * @author childe
 * @date 2017/12/4 19:35
 **/
public abstract class AbstractSotiyException extends Exception {
    protected AbstractSotiyException(String message) {
        super(message);
    }
}
