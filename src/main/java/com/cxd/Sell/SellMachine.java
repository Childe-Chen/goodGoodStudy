package com.cxd.Sell;

import com.cxd.Sell.event.EventCenter;
import com.cxd.Sell.event.EventHandler;
import com.cxd.Sell.module.Module;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.Enumeration;

/**
 * Created by childe on 2017/6/21.
 */
public class SellMachine {

    public static void main(String[] args) throws Exception {
        String pkgName = "com.cxd.Sell.module";
        //机器开机，创建初始化各个模块
        Enumeration<URL> resources = SellMachine.class.getClassLoader().getResources(pkgName.replace(".", "/"));
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            File f = new File(resource.getFile());
            if (!f.isDirectory()) { // should not be file , but a directory
                continue;
            }
            String[] files = f.list(new FilenameFilter() {
                public boolean accept(File file, String s) {
                    return s.endsWith(".class");
                }
            });

            for (String classFileName : files) {
                String className = classFileName.substring(0, classFileName.length() - 6);
                loadClassAndInit(pkgName + "." + className);
            }
        }

        System.out.println(EventCenter.print());
    }

    private static void loadClassAndInit(String className) {
        try {
            Class c = Class.forName(className);
            if (Modifier.isInterface(c.getModifiers()) || Modifier.isAbstract(c.getModifiers())) {
                return;
            }

            if (!Module.class.isAssignableFrom(c)) {
                return;
            }
            Module module = (Module)c.newInstance();
            module.on();
            if (EventHandler.class.isAssignableFrom(c)) {
                EventHandler eventHandler = (EventHandler)module;
                EventCenter.register(eventHandler);
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
