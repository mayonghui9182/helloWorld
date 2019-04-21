package com.ma.test.classloader;

public class TestClassLoader extends SupClass {
    public static FieldIni staticField1=new FieldIni("staticField1");
    public static final FieldIni staticFinalField1=new FieldIni("staticFinalField1");
    static{
        new FieldIni("staticMethod1");
    }
    public static FieldIni staticField2=new FieldIni("staticField2");
    static{
        new FieldIni("staticMethod2");
    }

    public TestClassLoader(){
        System.out.println("TestClassLoader");
    }

    public  FieldIni field1=new FieldIni("field1");
    public  FieldIni field2=new FieldIni("field2");


    public static void main(String[] args) {
        System.out.println("main start");
        System.out.println("Start instantiating");
        new TestClassLoader();
    }

}
class SupClass{
    public static FieldIni staticField1=new FieldIni("sup staticField1");
    static{
        new FieldIni("sup staticMethod1");
    }
    public static FieldIni staticField2=new FieldIni("sup staticField2");
    static{
        new FieldIni("sup staticMethod2");
    }

    public SupClass(){
        System.out.println("SupClass");
    }
    public  FieldIni field1=new FieldIni("sup field1");
    public  FieldIni field2=new FieldIni("sup field2");

}