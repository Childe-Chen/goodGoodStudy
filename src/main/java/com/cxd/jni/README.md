1. 先使用javac编译生成.class文件
javac -d . TestJNI.java

2. 使用javah将class文件编译生成.h文件
javah com.cxd.jni.TestJNI(此处是第一步生成的class文件)

3. 用C语言来实现函数
创建TestJNI.c文件，实现 com_cxd_jni_TestJNI 文件中的方法

4. 生成的库文件
库文件名要遵循：lib+文件名＋扩展名 的原则，JVM加载的时候按照此原则查找库文件
```java
static {
    // hello.dll (Windows) or libhello.so (Unixes) or .jnilib (mac osx)
    System.loadLibrary("TestJNI");
}
```
5. 运行
运行时需要指定库位置
-Djava.library.path=/Users/childe/Documents/workspace/goodGoodStudy/src/main/java/com/cxd/jni