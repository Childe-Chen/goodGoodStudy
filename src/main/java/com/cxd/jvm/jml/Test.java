package com.cxd.jvm.jml;

/**
 *
 * -javaagent:/Users/childe/Documents/workspace/goodGoodStudy/target/childe-1.0-SNAPSHOT.jar
 *
 * -XX:+UseCompressedOops 默认开启压缩指针压缩
 *
 * @author childe
 * @date 2018/8/15 11:43
 **/
public class Test {
    /**
     * HotSpot的对齐方式为8字节对齐：即大小应为8的倍数
     *
     * 对象布局 = 对象头（Header）， 实例数据（Instance Data）， 对齐填充（Padding）
     *
     * 开启（-XX:+UseCompressedOops）对象头大小为12bytes（64位机器）
     * @param args a
     */
    public static void main(String[] args) {
        Object o = new Object();
        // +UseCompressedOops Object sizeOf : 16=12（head）+ 4（padding） fullSizeOf : 16
        // -UseCompressedOops Object sizeOf : 16=16（head） fullSizeOf : 16
        System.out.println("Object sizeOf : " + ObjectSizeUtil.sizeOf(o) + " fullSizeOf : " + ObjectSizeUtil.fullSizeOf(o));

        int integer = 90;
        // int 自身4byte
        // +UseCompressedOops Integer sizeOf : 16 fullSizeOf : 16
        // -UseCompressedOops Integer sizeOf : 24 fullSizeOf : 24
        System.out.println("Integer sizeOf : " + ObjectSizeUtil.sizeOf(integer) + " fullSizeOf : " + ObjectSizeUtil.fullSizeOf(integer));

        long a = 100L;
        // long 自身8byte
        // +UseCompressedOops Long sizeOf : 24 fullSizeOf : 24
        // -UseCompressedOops Long sizeOf : 24 fullSizeOf : 24
        System.out.println("Long sizeOf : " + ObjectSizeUtil.sizeOf(a) + " fullSizeOf : " + ObjectSizeUtil.fullSizeOf(a));

        byte b = 1;
        // byte 自身1byte
        // +UseCompressedOops byte sizeOf : 16 fullSizeOf : 16
        // -UseCompressedOops byte sizeOf : 24 fullSizeOf : 24
        System.out.println("byte sizeOf : " + ObjectSizeUtil.sizeOf(b) + " fullSizeOf : " + ObjectSizeUtil.fullSizeOf(b));

        char c = '2';
        // char 自身2byte
        // +UseCompressedOops char sizeOf : 16 fullSizeOf : 16
        // -UseCompressedOops char sizeOf : 24 fullSizeOf : 24
        System.out.println("char sizeOf : " + ObjectSizeUtil.sizeOf(c) + " fullSizeOf : " + ObjectSizeUtil.fullSizeOf(c));

        String s = new String("w");
        //
        // +UseCompressedOops new String sizeOf : 24 fullSizeOf : 48
        // -UseCompressedOops new String sizeOf : 32 fullSizeOf : 64
        System.out.println("new String sizeOf : " + ObjectSizeUtil.sizeOf(s) + " fullSizeOf : " + ObjectSizeUtil.fullSizeOf(s));

        String ss = "c";
        // byte 自身1byte
        // +UseCompressedOops String sizeOf : 24 fullSizeOf : 0
        // -UseCompressedOops String sizeOf : 32 fullSizeOf : 0
        System.out.println("String sizeOf : " + ObjectSizeUtil.sizeOf(ss) + " fullSizeOf : " + ObjectSizeUtil.fullSizeOf(ss));

    }
}
