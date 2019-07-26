package com.ma.test.classloading;

public class SuperClass {
    static {
        System.out.println("SupClass init!");
    }
    public static int value  = 123;
}
