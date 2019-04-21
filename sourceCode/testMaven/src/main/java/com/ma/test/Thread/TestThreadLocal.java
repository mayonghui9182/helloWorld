package com.ma.test.Thread;

public class TestThreadLocal {
    public static void main(String[] args) {
        ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();
        ThreadLocal<String> stringThreadLoca2 = new ThreadLocal<>();
        stringThreadLocal.set("123");
        stringThreadLocal.set("456");
        stringThreadLoca2.set("789");
        stringThreadLoca2.set("098");
        System.out.println(stringThreadLocal.get());
        System.out.println(stringThreadLocal.get());
        System.out.println(stringThreadLoca2.get());
        System.out.println(stringThreadLoca2.get());
    }
}
