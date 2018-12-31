package com.ma.test.InvokeC;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * Date 2018/12/30
 *
 * @Author ma
 * description:
 * update：{[author:date:description:]}
 **/
public class PrintObjectAdress {
    public static void main(String[] args) {
        System.loadLibrary("PrintAdress");

        //testInvokeC();
        testString();
    }

    private static void testInvokeC() {
        NativeMethodTest nmt = new NativeMethodTest();

        int square = nmt.intMethod(5);
        boolean bool = nmt.booleanMethod(true);
        String text = nmt.stringMethod("java");
        nmt.stringMethod("java");

        nmt.stringMethod(new String("java"));
        int sum = nmt.intArrayMethod(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 13});

        System.out.println("intMethod: " + square);
        System.out.println("booleanMethod:" + bool);
        System.out.println("stringMethod:" + text);
        System.out.println("intArrayMethod:" + sum);
    }

    public static void testString() {
        String str1 = "java";
        String str2 = "java";
        String str3 = new String("java");
        System.out.println("Str1==Str2" + (str1 == str2));
        System.out.println("Str1==Str3" + (str1 == str3));
        System.out.println("Str2==Str3" + (str2 == str3));
        NativeMethodTest nativeMethodTest = new NativeMethodTest();
        nativeMethodTest.stringMethod(str1);
        nativeMethodTest.stringMethod(str2);
        nativeMethodTest.stringMethod(str3);
    }

    public static void testStringByJava() throws Exception{
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);
        long oneHundred = 100;
        byte size = 1;

        /*
         * 调用allocateMemory分配内存
         */
        long memoryAddress = unsafe.allocateMemory(size);

        /*
         * 将100写入到内存中
         */
        unsafe.putAddress(memoryAddress, oneHundred);

        /*
         * 内存中读取数据
         */
        long readValue = unsafe.getAddress(memoryAddress);

        System.out.println("Val : " + readValue);
        byte[] data = new byte[10];
        System.out.println(Arrays.toString(data));
        int byteArrayBaseOffset = unsafe.arrayBaseOffset(byte[].class);

        System.out.println("byteArrayBaseOffset:" + byteArrayBaseOffset);
        unsafe.putByte(data, byteArrayBaseOffset, (byte) 1);
        unsafe.putByte(data, byteArrayBaseOffset + 5, (byte) 5);

        System.out.println(Arrays.toString(data));
    }
}

class NativeMethodTest {
    public native int intMethod(int n);

    public native boolean booleanMethod(boolean bool);

    public native String stringMethod(String text);

    public native int intArrayMethod(int[] intArray);
}
