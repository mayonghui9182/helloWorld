package com.junit5;


import com.ma.DemoApplication;


import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = { DemoApplication.class })
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BaseTestClass {
    @BeforeAll
    public static void beforeClass(){
        System.out.println("beforeClass");
        ArrayList<?> strList = new ArrayList<>();
    }
    @BeforeEach
    public void before(){
        System.out.println("before");
    }
    @AfterEach
    public void after(){
        System.out.println("after");
    }
    @AfterAll
    public static  void afterClass(){
        System.out.println("afterClass");
    }
    @Test
    @Order(1)
    public void test(){
        System.out.println("BaseTestClass");
    }
    public static class Outer<T>{
        class Inner<S> {
            S s;
        }
        public Inner<String> test(){
            return new Inner<String>();
        }
    }

    public static void main(String[] args) {
        Outer.Inner test = (new Outer()).test();
    }
}
