package com.cxd.jvm.jml;

/**
 * Java Memory Layout
 *
 * -javaagent:/Users/childe/Documents/workspace/goodGoodStudy/target/childe-1.0-SNAPSHOT.jar
 *
 * -XX:+UseCompressedOops 默认开启压缩指针压缩
 *
 * @author childe
 * @date 2018/8/15 11:43
 **/
public class JMLTest {
    /**
     * HotSpot的对齐方式为8字节对齐：即大小应为8的倍数
     *
     * 对象布局 = 对象头（Header）， 实例数据（Instance Data）， 对齐填充（Padding）
     *
     * 开启（-XX:+UseCompressedOops）对象头大小为12bytes（64位机器）
     * @param args a
     */
    public static void main(String[] args) {
//        System.out.println("Object--------------------对象头-------------------------------------"+ "\n");
//        objectTest();
//
//        System.out.println("Primitive---------------------------------------------------------\n");
//        primitiveTest();
//
//        System.out.println("Reference-------64位机器上reference类型占用8个字节，开启指针压缩后占用4个字节------------" + "\n");
//        referenceTest();

        System.out.println("数组---------64位机器上，数组对象的对象头占用24个字节，启用压缩之后占用16个字节。之所以比普通对象占用内存多是因为需要额外的空间存储数组的长度----------------" + "\n");
        arrayTest();

//        System.out.println("特别的(复合对象) String----------------String存在常量池----------------" + "\n");
//        stringTest();
    }

    private static void stringTest() {
        String s = new String("c");
        //
        // +UseCompressedOops new String sizeOf : 24 fullSizeOf : 48
        // -UseCompressedOops new String sizeOf : 32 fullSizeOf : 64
        System.out.println("new String sizeOf : " + ObjectSizeUtil.sizeOf(s) + " fullSizeOf : " + ObjectSizeUtil.fullSizeOf(s)+ "\n");

        // 走了常量池，ObjectSizeUtil.fullSizeOf()会跳过计算
        String ss = "c";
        //
        // +UseCompressedOops String sizeOf : 24=12(head) + 4(hash int) +  fullSizeOf : 0
        // -UseCompressedOops String sizeOf : 32=12(head) + 4(hash int) +  fullSizeOf : 0
        System.out.println("String sizeOf : " + ObjectSizeUtil.sizeOf(ss) + " fullSizeOf : " + ObjectSizeUtil.fullSizeOf(ss)+ "\n");
    }

    private static void arrayTest() {
        Integer[] integerHead = new Integer[0];
        // integerHead 长度为0，即对象头的大小
        // +UseCompressedOops integerHead sizeOf : 16 fullSizeOf : 16
        // -UseCompressedOops integerHead sizeOf : 24 fullSizeOf : 24
        System.out.println("integerHead sizeOf : " + ObjectSizeUtil.sizeOf(integerHead) + " fullSizeOf : " + ObjectSizeUtil.fullSizeOf(integerHead)+ "\n");

        Integer[] integers1 = new Integer[1];
        // integers1
        // +UseCompressedOops integers1 sizeOf : 24 fullSizeOf : 24
        // -UseCompressedOops integers1 sizeOf : 32 fullSizeOf : 32
        System.out.println("integers1 sizeOf : " + ObjectSizeUtil.sizeOf(integers1) + " fullSizeOf : " + ObjectSizeUtil.fullSizeOf(integers1)+ "\n");

        Integer[] integers2 = new Integer[2];
        // integers2
        // +UseCompressedOops integers2 sizeOf : 24=16(head) + 4*2(两个引用) fullSizeOf : 24
        // -UseCompressedOops integers2 sizeOf : 40=24(head) + 8*2(两个引用) fullSizeOf : 40
        System.out.println("integers2 sizeOf : " + ObjectSizeUtil.sizeOf(integers2) + " fullSizeOf : " + ObjectSizeUtil.fullSizeOf(integers2)+ "\n");

        Integer[] integers3 = new Integer[3];
        // integers3
        // +UseCompressedOops integers3 sizeOf : 32 fullSizeOf : 32
        // -UseCompressedOops integers3 sizeOf : 48 fullSizeOf : 48
        System.out.println("integers3 sizeOf : " + ObjectSizeUtil.sizeOf(integers3) + " fullSizeOf : " + ObjectSizeUtil.fullSizeOf(integers3)+ "\n");

        int[] intHead = new int[0];
        // intHead 长度为0，即对象头的大小
        // +UseCompressedOops intHead sizeOf : 16 fullSizeOf : 16
        // -UseCompressedOops intHead sizeOf : 24 fullSizeOf : 24
        System.out.println("intHead sizeOf : " + ObjectSizeUtil.sizeOf(intHead) + " fullSizeOf : " + ObjectSizeUtil.fullSizeOf(intHead)+ "\n");

        int[] int1 = new int[1];
        // intHead 长度为0，即对象头的大小
        // +UseCompressedOops int1 sizeOf : 24=16（head）+ 4 + 4（padding） fullSizeOf : 24
        // -UseCompressedOops int1 sizeOf : 32 fullSizeOf : 32
        System.out.println("int1 sizeOf : " + ObjectSizeUtil.sizeOf(int1) + " fullSizeOf : " + ObjectSizeUtil.fullSizeOf(int1)+ "\n");

        int[] int2 = new int[2];
        // intHead 长度为0，即对象头的大小
        // +UseCompressedOops int2 sizeOf : 24=16（head）+ 4*2 fullSizeOf : 24
        // -UseCompressedOops int2 sizeOf : 32 fullSizeOf : 32
        System.out.println("int2 sizeOf : " + ObjectSizeUtil.sizeOf(int2) + " fullSizeOf : " + ObjectSizeUtil.fullSizeOf(int2)+ "\n");

        int[] int3 = new int[3];
        // intHead 长度为0，即对象头的大小
        // +UseCompressedOops int3 sizeOf : 32 fullSizeOf : 32
        // -UseCompressedOops int3 sizeOf : 40 fullSizeOf : 40
        System.out.println("int3 sizeOf : " + ObjectSizeUtil.sizeOf(int3) + " fullSizeOf : " + ObjectSizeUtil.fullSizeOf(int3)+ "\n");
    }

    private static void referenceTest() {
        Explorer explorer = new Explorer();
        // explorer 自身只有一个头
        // +UseCompressedOops explorer sizeOf : 16=12（self） + 4(int) fullSizeOf : 16
        // -UseCompressedOops explorer sizeOf : 24=16 （self）+ 4(int) + 4(padding) fullSizeOf : 24
        System.out.println("explorer sizeOf : " + ObjectSizeUtil.sizeOf(explorer) + " fullSizeOf : " + ObjectSizeUtil.fullSizeOf(explorer)+ "\n");

        EmptyExplorer emptyExplorer = new EmptyExplorer();
        // emptyExplorer 自身只有一个头
        // +UseCompressedOops emptyExplorer sizeOf : 16=12（self） + 4（padding） fullSizeOf : 16
        // -UseCompressedOops emptyExplorer sizeOf : 16=16（self） fullSizeOf : 16
        System.out.println("emptyExplorer sizeOf : " + ObjectSizeUtil.sizeOf(emptyExplorer) + " fullSizeOf : " + ObjectSizeUtil.fullSizeOf(emptyExplorer)+ "\n");

        //64位机器上reference类型占用8个字节，开启指针压缩后占用4个字节。
        NestExplorer nestExplorer = new NestExplorer();
        // nestExplorer 自身只有一个头
        // +UseCompressedOops nestExplorer sizeOf : 16=12+4(reference) fullSizeOf : 16
        // -UseCompressedOops nestExplorer sizeOf : 24=16+8(reference) fullSizeOf : 24
        System.out.println("nestExplorer sizeOf : " + ObjectSizeUtil.sizeOf(nestExplorer) + " fullSizeOf : " + ObjectSizeUtil.fullSizeOf(nestExplorer)+ "\n");
    }

    private static void primitiveTest() {
        int integer = 90;
        // int 自身4byte
        // +UseCompressedOops Integer sizeOf : 16=12（head）+ 4（self） fullSizeOf : 16
        // -UseCompressedOops Integer sizeOf : 24=16（head）+ 4（self） + 4（padding） fullSizeOf : 24
        System.out.println("Integer sizeOf : " + ObjectSizeUtil.sizeOf(integer) + " fullSizeOf : " + ObjectSizeUtil.fullSizeOf(integer)+ "\n");

        long a = 100L;
        // long 自身8byte
        // +UseCompressedOops Long sizeOf : 24 fullSizeOf : 24
        // -UseCompressedOops Long sizeOf : 24 fullSizeOf : 24
        System.out.println("Long sizeOf : " + ObjectSizeUtil.sizeOf(a) + " fullSizeOf : " + ObjectSizeUtil.fullSizeOf(a)+ "\n");

        byte b = 1;
        // byte 自身1byte
        // +UseCompressedOops byte sizeOf : 16 fullSizeOf : 16
        // -UseCompressedOops byte sizeOf : 24 fullSizeOf : 24
        System.out.println("byte sizeOf : " + ObjectSizeUtil.sizeOf(b) + " fullSizeOf : " + ObjectSizeUtil.fullSizeOf(b)+ "\n");

        char c = '2';
        // char 自身2byte
        // +UseCompressedOops char sizeOf : 16 fullSizeOf : 16
        // -UseCompressedOops char sizeOf : 24 fullSizeOf : 24
        System.out.println("char sizeOf : " + ObjectSizeUtil.sizeOf(c) + " fullSizeOf : " + ObjectSizeUtil.fullSizeOf(c)+ "\n");
    }

    private static void objectTest() {
        Object o = new Object();
        // +UseCompressedOops Object sizeOf : 16=12（head）+ 4（padding） fullSizeOf : 16
        // -UseCompressedOops Object sizeOf : 16=16（head） fullSizeOf : 16
        System.out.println("Object sizeOf : " + ObjectSizeUtil.sizeOf(o) + " fullSizeOf : " + ObjectSizeUtil.fullSizeOf(o) + "\n");
    }
}
