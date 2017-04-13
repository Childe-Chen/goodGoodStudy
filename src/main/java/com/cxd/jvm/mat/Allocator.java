package com.cxd.jvm.mat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by childe on 17/3/23.
 */
public class Allocator {

    private  List<Listener> list1 = new ArrayList<>();

   public void add() {
       list1.add(new Listener());
   }
}
