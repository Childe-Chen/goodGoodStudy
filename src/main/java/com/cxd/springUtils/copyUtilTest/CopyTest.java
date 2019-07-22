package com.cxd.springUtils.copyUtilTest;

import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * 泛型时导致copy类型非期望值
 *
 * @author fanyin
 * @date 2019/5/24 15:15
 **/
public class CopyTest {

    public static void main(String[] args) {

//        CartListMall cartListMall = new CartListMall();
//
//        List<ItemMall> itemMallList = new ArrayList<>(10);
//        for (int i = 0; i < 10; i++) {
//            ItemMall itemMall = new ItemMall();
//            itemMall.setName(i);
//
//            itemMallList.add(itemMall);
//        }
//
//        cartListMall.setItems(itemMallList);
//
//        List<Long> invalidItemMallList = new ArrayList<>(10);
//        for (long i = 0L; i < 10; i++) {
//            invalidItemMallList.add(i);
//        }
//
//        cartListMall.setInvalidItems(invalidItemMallList);
//
//        CartList cartList = new CartList();
//
//        BeanUtils.copyProperties(cartListMall, cartList);
//
//        System.out.println();

        ArrayList<Integer> list = new ArrayList<>();

        //这样调用 add 方法只能存储整形，因为泛型类型的实例为 Integer
        list.add(1);

        try {
            list.getClass().getMethod("add", Object.class).invoke(list, "asd");
        } catch (IllegalAccessException|InvocationTargetException|NoSuchMethodException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

    }
}

