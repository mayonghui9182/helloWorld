package com.ma.unicode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.util.List;

public class TestUnicode<@test T extends String&List> {
    //测试java是否支持编译Big5编码文件.
    public static void main(String[] args) {
        String 变量="123";
        System.out.println("Big5编码"+变量);
    }
}
@Target(ElementType.TYPE_PARAMETER)
@interface test{}
