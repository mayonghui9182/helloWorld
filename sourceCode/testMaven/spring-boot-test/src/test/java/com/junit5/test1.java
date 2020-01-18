package com.junit5;

import org.junit.jupiter.api.*;
public class test1 extends BaseTestClass {
    @BeforeAll
    public static void beforeClass1(){
        System.out.println("test1 beforeClass");
    }
    @BeforeEach
    public void before(){
        System.out.println("test1 before");
    }
    @AfterEach
    public void after(){
        System.out.println("test1 after");
    }
    @AfterAll
    public static void afterClass1(){
        System.out.println("test1 afterClass");
    }
    @Test
    @Order(9)
    public void test1(){
        System.out.println("test1");
    }
    @Test
    @Order(4)
    public void test2(){
        System.out.println("test2");
    }
    @Test
    @Order(5)
    public void test3(){
        System.out.println("test3");
    }
    @Test
    @Order(6)
    public void test4(){
        System.out.println("test4");
    }
}
