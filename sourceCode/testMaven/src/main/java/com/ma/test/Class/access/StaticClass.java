package com.ma.test.Class.access;

public class StaticClass {
}

class DefaultClass{
    private DefaultClass(){
    }
    public static DefaultClass getDefaultClassInstance() {
         return new DefaultClass();
    }

}