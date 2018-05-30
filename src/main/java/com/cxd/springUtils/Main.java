package com.cxd.springUtils;





import java.math.BigDecimal;

/**
 * desc
 *
 * @author childe
 * @date 2017/10/16 14:59
 **/
public class Main {
    public static void main(String[] args) throws Exception{
        APeople aPeople = new APeople();
        aPeople.setCrash(new BigDecimal(10.33));

        BPeople bPeople = new BPeople();

//        BeanUtils.copyProperties(bPeople,aPeople);

        System.out.println(bPeople.getCrash());
    }
}
