package com.ma.test.Thread;

import java.util.concurrent.TimeUnit;

public class testSynchronized {
    public synchronized static void accessResource(){
        try {
            TimeUnit.SECONDS.sleep(2);
            System.out.println(Thread.currentThread().getName()+"is running!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        for (int i = 0; i < 5 ; i++) {
            new Thread(testSynchronized::accessResource).start();
        }
    }

}
