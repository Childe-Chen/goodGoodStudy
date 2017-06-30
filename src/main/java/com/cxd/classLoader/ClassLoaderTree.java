package com.cxd.classLoader;

/**
 * Created by childe on 2017/6/27.
 */
public class ClassLoaderTree {
    /**
     * 输出：
     * sun.misc.Launcher$AppClassLoader@18b4aac2
     * sun.misc.Launcher$ExtClassLoader@5305068a
     * null
     * @param args
     */
    public static void main(String[] args) {
        ClassLoader loader = ClassLoaderTree.class.getClassLoader();
        while (loader!=null){
            System.out.println(loader.toString());
            loader = loader.getParent();
        }
        System.out.println(loader);
    }
}
