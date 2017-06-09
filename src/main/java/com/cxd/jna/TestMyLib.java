package com.cxd.jna;

import com.sun.jna.Callback;
import com.sun.jna.Library;
import com.sun.jna.Native;

/**
 * -Djna.library.path=/Users/childe/Documents/workspace/goodGoodStudy/src/main/java/com/cxd/jna
 * Created by childe on 2017/6/9.
 */
public class TestMyLib {

    public interface RLibrary extends Library {
        RLibrary INSTANCE =  Native.loadLibrary("MyLib",
                        RLibrary.class);

        // 定义接口JavaCallbackAdd，继承自com.sun.jna.Callback
        interface JavaCallbackAdd extends Callback {
            int CallbackAdd(int a, int b);
        }

        // 动态库的函数声明
        void RegisterAdd(JavaCallbackAdd callback);

        void DoAddByCallback(int a, int b);
    }

    public static void main(String[] args) {
        RLibrary.JavaCallbackAdd callback = new RLibrary.JavaCallbackAdd() {
            // 实现CallbackAdd函数
            public int CallbackAdd(int a, int b) {
                return a + b;
            }
        };
        // 调用动态库的函数
        RLibrary.INSTANCE.RegisterAdd(callback);
        // 由动态库执行CallbackAdd回调函数
        RLibrary.INSTANCE.DoAddByCallback(1, 2);
    }
}
