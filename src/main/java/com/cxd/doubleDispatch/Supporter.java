package com.cxd.doubleDispatch;

/**
 * desc
 *
 * @author childe
 * @date 2018/2/1 17:27
 **/
public class Supporter {
    public void solve(Problem problem){
        System.out.println("一级支持解决一般问题！");
    }

    public void solve(SpecialProblem problem){
        System.out.println("一级支持解决特殊问题");
    }
}
