package com.cxd.jni;

/**
 * Created by childe on 2017/4/13.
 */
public class JNIMain {
    static {
        // hello.dll (Windows) or libhello.so (Unixes) or .jnilib (mac osx)
        System.loadLibrary("TestJNI");
    }

    public static void main(String[] args) {

        new TestJNI().sayHello();  // invoke the native method
    }
}
