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
        System.out.println("Primitive---------------------------------------------------------"+ "\n");
        primitiveTest();
//
//        System.out.println("Reference-------64位机器上reference类型占用8个字节，开启指针压缩后占用4个字节------------" + "\n");
//        referenceTest();

//        System.out.println("数组---------64位机器上，数组对象的对象头占用24个字节，启用压缩之后占用16个字节。之所以比普通对象占用内存多是因为需要额外的空间存储数组的长度----------------" + "\n");
//        arrayTest();

//        System.out.println("特别的(复合对象) String----------------String存在常量池----------------" + "\n");
//        stringTest();
    }

    private static void stringTest() {
        String s = new String("c");
        //
        // +UseCompressedOops new String shallowSizeOf : 24 retainedSizeOf : 48
        // -UseCompressedOops new String shallowSizeOf : 32 retainedSizeOf : 64
        System.out.println("new String shallowSizeOf : " + ObjectSizeUtil.shallowSizeOf(s) + " retainedSizeOf : " + ObjectSizeUtil.retainedSizeOf(s)+ "\n");

        // 走了常量池，ObjectSizeUtil.retainedSizeOf()会跳过计算
        String ss = "c";
        //
        // +UseCompressedOops String shallowSizeOf : 24=12(head) + 4(hash int) +  retainedSizeOf : 0
        // -UseCompressedOops String shallowSizeOf : 32=12(head) + 4(hash int) +  retainedSizeOf : 0
        System.out.println("String shallowSizeOf : " + ObjectSizeUtil.shallowSizeOf(ss) + " retainedSizeOf : " + ObjectSizeUtil.retainedSizeOf(ss)+ "\n");
    }

    private static void arrayTest() {
        Integer[] integerHead = new Integer[0];
        // integerHead 长度为0，即对象头的大小
        // +UseCompressedOops integerHead shallowSizeOf : 16 retainedSizeOf : 16
        // -UseCompressedOops integerHead shallowSizeOf : 24 retainedSizeOf : 24
        System.out.println("integerHead shallowSizeOf : " + ObjectSizeUtil.shallowSizeOf(integerHead) + " retainedSizeOf : " + ObjectSizeUtil.retainedSizeOf(integerHead)+ "\n");

        Integer[] integers1 = new Integer[1];
        // integers1
        // +UseCompressedOops integers1 shallowSizeOf : 24 retainedSizeOf : 24
        // -UseCompressedOops integers1 shallowSizeOf : 32 retainedSizeOf : 32
        System.out.println("integers1 shallowSizeOf : " + ObjectSizeUtil.shallowSizeOf(integers1) + " retainedSizeOf : " + ObjectSizeUtil.retainedSizeOf(integers1)+ "\n");

        Integer[] integers2 = new Integer[2];
        // integers2
        // +UseCompressedOops integers2 shallowSizeOf : 24=16(head) + 4*2(两个引用) retainedSizeOf : 24
        // -UseCompressedOops integers2 shallowSizeOf : 40=24(head) + 8*2(两个引用) retainedSizeOf : 40
        System.out.println("integers2 shallowSizeOf : " + ObjectSizeUtil.shallowSizeOf(integers2) + " retainedSizeOf : " + ObjectSizeUtil.retainedSizeOf(integers2)+ "\n");

        Integer[] integers3 = new Integer[3];
        // integers3
        // +UseCompressedOops integers3 shallowSizeOf : 32 retainedSizeOf : 32
        // -UseCompressedOops integers3 shallowSizeOf : 48 retainedSizeOf : 48
        System.out.println("integers3 shallowSizeOf : " + ObjectSizeUtil.shallowSizeOf(integers3) + " retainedSizeOf : " + ObjectSizeUtil.retainedSizeOf(integers3)+ "\n");

        int[] intHead = new int[0];
        // intHead 长度为0，即对象头的大小
        // +UseCompressedOops intHead shallowSizeOf : 16 retainedSizeOf : 16
        // -UseCompressedOops intHead shallowSizeOf : 24 retainedSizeOf : 24
        System.out.println("intHead shallowSizeOf : " + ObjectSizeUtil.shallowSizeOf(intHead) + " retainedSizeOf : " + ObjectSizeUtil.retainedSizeOf(intHead)+ "\n");

        int[] int1 = new int[1];
        // intHead 长度为0，即对象头的大小
        // +UseCompressedOops int1 shallowSizeOf : 24=16（head）+ 4 + 4（padding） retainedSizeOf : 24
        // -UseCompressedOops int1 shallowSizeOf : 32 retainedSizeOf : 32
        System.out.println("int1 shallowSizeOf : " + ObjectSizeUtil.shallowSizeOf(int1) + " retainedSizeOf : " + ObjectSizeUtil.retainedSizeOf(int1)+ "\n");

        int[] int2 = new int[2];
        // intHead 长度为0，即对象头的大小
        // +UseCompressedOops int2 shallowSizeOf : 24=16（head）+ 4*2 retainedSizeOf : 24
        // -UseCompressedOops int2 shallowSizeOf : 32 retainedSizeOf : 32
        System.out.println("int2 shallowSizeOf : " + ObjectSizeUtil.shallowSizeOf(int2) + " retainedSizeOf : " + ObjectSizeUtil.retainedSizeOf(int2)+ "\n");

        int[] int3 = new int[3];
        // intHead 长度为0，即对象头的大小
        // +UseCompressedOops int3 shallowSizeOf : 32 retainedSizeOf : 32
        // -UseCompressedOops int3 shallowSizeOf : 40 retainedSizeOf : 40
        System.out.println("int3 shallowSizeOf : " + ObjectSizeUtil.shallowSizeOf(int3) + " retainedSizeOf : " + ObjectSizeUtil.retainedSizeOf(int3)+ "\n");
    }

    private static void referenceTest() {
        Explorer explorer = new Explorer();
        // explorer 自身只有一个头
        // +UseCompressedOops explorer shallowSizeOf : 16=12（self） + 4(int) retainedSizeOf : 16
        // -UseCompressedOops explorer shallowSizeOf : 24=16 （self）+ 4(int) + 4(padding) retainedSizeOf : 24
        System.out.println("explorer shallowSizeOf : " + ObjectSizeUtil.shallowSizeOf(explorer) + " retainedSizeOf : " + ObjectSizeUtil.retainedSizeOf(explorer)+ "\n");

        EmptyExplorer emptyExplorer = new EmptyExplorer();
        // emptyExplorer 自身只有一个头
        // +UseCompressedOops emptyExplorer shallowSizeOf : 16=12（self） + 4（padding） retainedSizeOf : 16
        // -UseCompressedOops emptyExplorer shallowSizeOf : 16=16（self） retainedSizeOf : 16
        System.out.println("emptyExplorer shallowSizeOf : " + ObjectSizeUtil.shallowSizeOf(emptyExplorer) + " retainedSizeOf : " + ObjectSizeUtil.retainedSizeOf(emptyExplorer)+ "\n");

        //64位机器上reference类型占用8个字节，开启指针压缩后占用4个字节。
        NestExplorer nestExplorer = new NestExplorer();
        // nestExplorer 自身只有一个头
        // +UseCompressedOops nestExplorer shallowSizeOf : 16=12+4(reference) retainedSizeOf : 16
        // -UseCompressedOops nestExplorer shallowSizeOf : 24=16+8(reference) retainedSizeOf : 24
        System.out.println("nestExplorer shallowSizeOf : " + ObjectSizeUtil.shallowSizeOf(nestExplorer) + " retainedSizeOf : " + ObjectSizeUtil.retainedSizeOf(nestExplorer)+ "\n");
    }

    private static void primitiveTest() {
        int integer = 90;
        // int 自身4byte
        // +UseCompressedOops Integer shallowSizeOf : 16=12（head）+ 4（self） retainedSizeOf : 16
        // -UseCompressedOops Integer shallowSizeOf : 24=16（head）+ 4（self） + 4（padding） retainedSizeOf : 24
        System.out.println("Integer shallowSizeOf : " + ObjectSizeUtil.shallowSizeOf(integer) + " retainedSizeOf : " + ObjectSizeUtil.retainedSizeOf(integer)+ "\n");

        long a = 100L;
        // long 自身8byte
        // +UseCompressedOops Long shallowSizeOf : 24=12 + 8 + 4(padding) retainedSizeOf : 24
        // -UseCompressedOops Long shallowSizeOf : 24 retainedSizeOf : 24
        System.out.println("Long shallowSizeOf : " + ObjectSizeUtil.shallowSizeOf(a) + " retainedSizeOf : " + ObjectSizeUtil.retainedSizeOf(a)+ "\n");

        byte b = 1;
        // byte 自身1byte
        // +UseCompressedOops byte shallowSizeOf : 16 retainedSizeOf : 16
        // -UseCompressedOops byte shallowSizeOf : 24 retainedSizeOf : 24
        System.out.println("byte shallowSizeOf : " + ObjectSizeUtil.shallowSizeOf(b) + " retainedSizeOf : " + ObjectSizeUtil.retainedSizeOf(b)+ "\n");

        char c = '2';
        // char 自身2byte
        // +UseCompressedOops char shallowSizeOf : 16 retainedSizeOf : 16
        // -UseCompressedOops char shallowSizeOf : 24 retainedSizeOf : 24
        System.out.println("char shallowSizeOf : " + ObjectSizeUtil.shallowSizeOf(c) + " retainedSizeOf : " + ObjectSizeUtil.retainedSizeOf(c)+ "\n");

        boolean d = true;
        // http://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html
        // boolean 大小没有明确指定，和虚拟机具体实现有关
        // +UseCompressedOops shallowSizeOf : 16 retainedSizeOf : 16
        // -UseCompressedOops shallowSizeOf : 24 retainedSizeOf : 24
        System.out.println("boolean shallowSizeOf : " + ObjectSizeUtil.shallowSizeOf(d) + " retainedSizeOf : " + ObjectSizeUtil.retainedSizeOf(d)+ "\n");
    }

    private static void objectTest() {
        Object o = new Object();
        // +UseCompressedOops Object shallowSizeOf : 16=12（head）+ 4（padding） retainedSizeOf : 16
        // -UseCompressedOops Object shallowSizeOf : 16=16（head） retainedSizeOf : 16
        System.out.println("Object shallowSizeOf : " + ObjectSizeUtil.shallowSizeOf(o) + " retainedSizeOf : " + ObjectSizeUtil.retainedSizeOf(o) + "\n");
    }
}
