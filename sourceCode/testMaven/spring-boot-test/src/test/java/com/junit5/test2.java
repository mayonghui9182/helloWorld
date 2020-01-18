package com.junit5;

import org.junit.jupiter.api.*;

public class test2 extends BaseTestClass {
    @BeforeAll
    public static void beforeClass2(){
        System.out.println("test2 beforeClass");
    }
    @BeforeEach
    public void before(){
        System.out.println("test2 before");
    }
    @AfterEach
    public void after(){
        System.out.println("test2 after");
    }
    @AfterAll
    public static void afterClass2(){
        System.out.println("test2 afterClass");
    }

    @Test
    @Order(2)
    public void test(){
        System.out.println("test2");
    }
}
