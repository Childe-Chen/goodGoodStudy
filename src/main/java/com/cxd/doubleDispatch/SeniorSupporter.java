package com.cxd.doubleDispatch;

/**
 * desc
 *
 * @author childe
 * @date 2018/2/1 17:28
 **/
public class SeniorSupporter extends Supporter {

    @Override
    public void solve(Problem problem){
        System.out.println("资深支持解决一般问题！");
    }

    @Override
    public void solve(SpecialProblem specProblem){
        System.out.println("资深支持解决特殊问题！");
    }

}
