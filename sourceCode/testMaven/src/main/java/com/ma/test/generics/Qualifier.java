package com.ma.test.generics;

import java.util.Comparator;

public class Qualifier<T> {
    public T testTypeClear(T t){
        return t;
    }
    public <S> T testGenericMethod(T t){
        return t;
    }
    public <U extends Comparator&Runnable> T testDoubleQualifier(T t){
        U u=null;
        u.run();
        return t;
    }
    public static <T> T testStaticTypeClear(T t){
        return t;
    }

    public static void main(String[] args) {
        Qualifier<String> stringQualifier = new Qualifier<>();
        stringQualifier.<Integer>testGenericMethod("test");
        System.out.println();
    }
}
