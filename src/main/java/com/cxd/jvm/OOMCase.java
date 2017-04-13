package com.cxd.jvm;

import com.cxd.jvm.mat.Controller;

/**
 * Created by childe on 17/3/23.
 */
public class OOMCase {

    public static void main(String[] args) {

        Controller controller = new Controller();

        controller.start();

        try {
            Thread.currentThread().join();
        } catch (Exception e) {

        }
    }
}
