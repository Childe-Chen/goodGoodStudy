package com.cxd.javaSourceCode.exception;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * desc
 *
 * @author childe
 * @date 2019/2/19 21:40
 **/
public class Main {

    public static void main(String[] args) {
        String str = new Main().openFile();
        System.out.println(str);

    }

    public String openFile() {
        try {
            FileInputStream inputStream = new FileInputStream("d:/a.txt");
            int ch = inputStream.read();
            System.out.println("aaa");
            return "step1";
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
            return "step2";
        }catch (IOException e) {
            System.out.println("io exception");
            return "step3";
        }finally{
            System.out.println("finally block");
            return "finally";
        }
    }

    class CheckException extends Exception {

    }

    class UnCheckException extends RuntimeException {

    }
}
