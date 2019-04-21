package com.ma.test.classloader;

public class FieldIni {
    private String str=null;

    public FieldIni(String str){
        this.str=str;
        System.out.println("初始化"+str);
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
