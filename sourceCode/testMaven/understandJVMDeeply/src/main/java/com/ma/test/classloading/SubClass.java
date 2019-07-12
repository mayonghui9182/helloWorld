package com.ma.test.classloading;

public class SubClass extends SuperClass{
    static {
        System.out.println("SuClassb init!");
    }
}
