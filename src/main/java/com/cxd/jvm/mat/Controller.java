package com.cxd.jvm.mat;

/**
 * Created by childe on 17/3/24.
 */
public class Controller {

    Thread thread1 = new Thread("allocator1") {
        Allocator allocator = new Allocator();
        @Override
        public void run() {
            while (true) {
                allocator.add();
            }
        }
    };

    Thread thread2 = new Thread("allocator2") {
        @Override
        public void run() {
            Allocator allocator = new Allocator();
            while (true) {
                allocator.add();
                try {
                    Thread.sleep(400);
                } catch (Exception e) {
                }
            }
        }
    };

    Thread thread3 = new Thread("allocator3") {
        @Override
        public void run() {
            Allocator allocator = new Allocator();
            while (true) {
                allocator.add();
                try {
                    Thread.sleep(800);
                } catch (Exception e) {
                }
            }
        }
    };

    Thread thread4 = new Thread("allocator4") {
        @Override
        public void run() {
            Allocator allocator = new Allocator();
            while (true) {
                allocator.add();
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                }
            }
        }
    };

    public Controller() {

    }

    public void start() {
        this.thread1.start();
        this.thread2.start();
        this.thread3.start();
        this.thread4.start();
    }
}
