package com.cxd.solrWay.utils;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by childe on 2017/7/16.
 */
public class BeanUtil {

    private static final Object[] EMPTY_ARRAY = {};

    public static Map populateMap(Map map, Object bean) {
        if (bean == null) {
            return map;
        }
        if (bean instanceof Map) {
            map.putAll((Map)bean);
            return map;
        }
        try {
            Method[] methods = bean.getClass().getMethods();
            for (int i = 0; i < methods.length; i++) {
                String methodName = methods[i].getName();
                Class[] pts = methods[i].getParameterTypes();
                Class rt = methods[i].getReturnType();

                if (methodName.startsWith("get") && pts.length == 0 && !Void.class.equals(rt)) {
                    String propName = Character.toLowerCase(methodName.charAt(3)) + methodName.substring(4);
                    if ("class".equals(propName)) {
                        continue;
                    }

                    Object value = methods[i].invoke(bean, EMPTY_ARRAY);
                    if (value != null) {
                        map.put(propName, value);
                    }
                }
            }

            return map;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
