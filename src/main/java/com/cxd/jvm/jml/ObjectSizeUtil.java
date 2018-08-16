package com.cxd.jvm.jml;

import java.lang.instrument.Instrumentation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 测量对象内存大小
 *
 * @author childe
 * @date 2018/8/15 11:39
 **/
public class ObjectSizeUtil {

    private static Instrumentation inst;

    public static void premain(String agentArgs, Instrumentation instP) {
        inst = instP;
    }

    public static long sizeOf(Object o) {
        if(inst == null) {
            throw new IllegalStateException("Can not access instrumentation environment.\n" +
                    "Please check if jar file containing SizeOfAgent class is \n" +
                    "specified in the java's \"-javaagent\" command line argument.");
        }
        return inst.getObjectSize(o);
    }

    /**
     * 递归计算当前对象占用空间总大小，包括当前类和超类的实例字段大小以及实例字段引用对象大小
     * 像MAT中的 Retained heap
     */
    public static long fullSizeOf(Object obj) {
        //深入检索对象，并计算大小
        Map<Object, Object> visited = new IdentityHashMap<>();
        Stack<Object> stack = new Stack<>();
        long result = internalSizeOf(obj, stack, visited);
        //通过栈进行遍历
        while (!stack.isEmpty()) {
            result += internalSizeOf(stack.pop(), stack, visited);
        }
        visited.clear();
        return result;
    }
    //判定哪些是需要跳过的
    private static boolean skipObject(Object obj, Map<Object, Object> visited) {
        if (obj instanceof String) {
            if (obj == ((String) obj).intern()) {
                return true;
            }
        }
        return (obj == null) || visited.containsKey(obj);
    }

    private static long internalSizeOf(Object obj, Stack<Object> stack, Map<Object, Object> visited) {
        //跳过常量池对象、跳过已经访问过的对象
        if (skipObject(obj, visited)) {
            return 0;
        }
        //将当前对象放入栈中
        visited.put(obj, null);
        long result = 0;
        result += sizeOf(obj);
        Class <?>clazz = obj.getClass();
        //如果数组
        if (clazz.isArray()) {
            // skip primitive type array
            if(clazz.getName().length() != 2) {
                int length =  Array.getLength(obj);
                for (int i = 0; i < length; i++) {
                    stack.add(Array.get(obj, i));
                }
            }
            return result;
        }
        return getNodeSize(clazz , result , obj , stack);
    }

    /**
     * 这个方法获取非数组对象自身的大小，并且可以向父类进行向上搜索
     * @param clazz
     * @param result
     * @param obj
     * @param stack
     * @return
     */
    private static long getNodeSize(Class <?>clazz , long result , Object obj , Stack<Object> stack) {
        while (clazz != null) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                //这里抛开静态属性
                if (!Modifier.isStatic(field.getModifiers())) {
                    //这里抛开基本关键字（因为基本关键字在调用java默认提供的方法就已经计算过了）
                    if (field.getType().isPrimitive()) {
                        continue;
                    }

                    field.setAccessible(true);
                    try {
                        Object objectToAdd = field.get(obj);
                        if (objectToAdd != null) {
                            //将对象放入栈中，一遍弹出后继续检索
                            stack.add(objectToAdd);
                        }
                    } catch (IllegalAccessException ex) {
                        assert false;
                    }
                }
            }
            //找父类class，直到没有父类
            clazz = clazz.getSuperclass();
        }

        return result;
    }
}
