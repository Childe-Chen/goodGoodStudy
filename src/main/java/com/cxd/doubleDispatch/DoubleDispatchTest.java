package com.cxd.doubleDispatch;

/**
 * desc
 *
 * @author childe
 * @date 2018/2/1 17:28
 **/
public class DoubleDispatchTest {

    public static void main(String[] args) {
//        functionOverload();

//        singleDynamicDispatch();

        someError();
    }

    /**
     * 函数重载
     */
    public static void functionOverload(){
        Problem problem = new Problem();
        SpecialProblem specProblem = new SpecialProblem();

        Supporter supporter = new Supporter();
        supporter.solve(problem);
        supporter.solve(specProblem);

//        一级支持解决一般问题！
//        一级支持解决特殊问题
    }

    /**
     * 单次动态分派
     */
    public static void singleDynamicDispatch(){
        Problem problem = new Problem();
        SpecialProblem specProblem = new SpecialProblem();

        Supporter supporter = new SeniorSupporter();
        supporter.solve(problem);
        supporter.solve(specProblem);

        //资深支持解决一般问题！
        //资深支持解决特殊问题！

        //能够正确路由
    }

    public static void someError() {
        Problem problem = new Problem();
        Problem specProblem = new SpecialProblem();

        Supporter supporter = new SeniorSupporter();

        supporter.solve(problem);
        supporter.solve(specProblem);

        //资深支持解决一般问题！
        //资深支持解决一般问题！

        //出错了，与我们意图不一样
    }
}
