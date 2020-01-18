package com.junit4;


import com.ma.DemoApplication;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = DemoApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@Suite.SuiteClasses({test2.class,test1.class})
public class BaseTestClass {
    @BeforeAll
    public static void beforeClass(){
        System.out.println("beforeClass");
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
    @Test public void test(){
        System.out.println("BaseTestClass");
    }

}
