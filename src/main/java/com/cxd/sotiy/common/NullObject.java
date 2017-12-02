package com.cxd.sotiy.common;

/**
 * NULL
 *
 * @author childe
 * @date 2017/12/2 17:02
 **/
public interface NullObject {

    /**
     * 是否null对象
     * @return
     */
    default boolean isNullObject() {
        return false;
    }
}
