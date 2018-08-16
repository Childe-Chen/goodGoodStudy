package com.cxd.javaSourceCode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc
 *
 * @author childe
 * @date 2018/7/18 10:47
 **/
public class ExceptionTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionTest.class);
    public static void main(String[] args) {
        setLogger();
        try {
            Long.parseLong("ss");
        } catch (NumberFormatException e) {
            throw new RuntimeException("eeee");
        } finally {
            LOGGER.error("finally");
        }
    }

    static void setLogger() {
        setLogger1();
    }

    static void setLogger1() {
        LOGGER.error("eee={}, {}", null, "ff");
        StackTraceElement[] traceElements = Thread.currentThread().getStackTrace();
        int len = traceElements.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < len - 1; i++) {
            sb.append(traceElements[i].toString()).append("\n");
        }
        LOGGER.warn(sb.toString());
    }
}
