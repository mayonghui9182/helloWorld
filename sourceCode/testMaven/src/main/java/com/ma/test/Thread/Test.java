package com.ma.test.Thread;


/**
 * @see https://www.cnblogs.com/dolphin0520/p/3920407.html
 * 通过Thread.currentThread();获取当前线程对象currentThread
 * 每个Thread都有一个ThreadLocal.ThreadLocalMap threadLocals对象，一个Thread的内部类
 * 内部类有一个entry继承了 WeakReference，
 * 将当前ThreadLoacl对象作为key存进ThreadLocal.ThreadLocalMap threadLocals里面，
 * get的时候根据当前ThreadLocal获取存入的本地变量
 * 每个线程Thread都有一个
 */
@SuppressWarnings("ALL")
public class Test {
    ThreadLocal<Long> longLocal = new ThreadLocal<Long>();
    ThreadLocal<String> stringLocal = new ThreadLocal<String>();


    public void set() {
        longLocal.set(Thread.currentThread().getId());
        stringLocal.set(Thread.currentThread().getName());
    }

    public long getLong() {
        return longLocal.get();
    }

    public String getString() {
        return stringLocal.get();
    }

    public static void main(String[] args) throws InterruptedException {
        final Test test = new Test();


        test.set();
        System.out.println(test.getLong());
        System.out.println(test.getString());


        Thread thread1 = new Thread(){
            @Override
            public void run() {
                test.set();
                System.out.println(test.getLong());
                System.out.println(test.getString());
            };
        };
        thread1.start();
        thread1.join();

        System.out.println(test.getLong());
        System.out.println(test.getString());
    }
}
