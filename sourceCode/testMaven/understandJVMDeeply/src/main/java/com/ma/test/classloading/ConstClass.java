package com.ma.test.classloading;

public class ConstClass {
    static {
        System.out.println("ConstClass init!");
        str="";
        System.out.println(str);
    }
    public static  String str= "ConstStr";
}
