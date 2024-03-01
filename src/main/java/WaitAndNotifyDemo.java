import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.locks.LockSupport;

/**
 * @author fanyin
 * @date 2023/5/22 14:42
 */
class MyThread extends Thread {
    private Object object;

    public MyThread(Object object) {
        this.object = object;
    }

    public void run() {
        System.out.println("before unpark   1" + LockSupport.getBlocker(Thread.currentThread()));
        // 释放许可
        LockSupport.unpark((Thread) object);
        System.out.println("after unpark   2" + LockSupport.getBlocker(Thread.currentThread()));
    }
}

class GenericClass<T> {

}


public class WaitAndNotifyDemo {
    public static void main(String[] args) {
//        GenericClass<Integer> g = new GenericClass<Integer>(){};
//        Type genericType = g.getClass().getGenericSuperclass();
//        System.out.println(((ParameterizedType)genericType).getActualTypeArguments()[0]);
        MyThread myThread = new MyThread(Thread.currentThread());
        myThread.start();
        try {
            // 主线程睡眠3s
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("before park");
        // 获取许可
        LockSupport.park("ParkAndUnparkDemo");
        System.out.println("after park");

        System.out.println("Blocker info " + LockSupport.getBlocker(Thread.currentThread()));
    }
}