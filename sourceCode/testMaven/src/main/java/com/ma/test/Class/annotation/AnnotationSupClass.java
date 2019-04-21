package com.ma.test.Class.annotation;

import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AnnotationSupClass implements Comparable,Cloneable
{
    @Bean
    public static void TestStaticMethodAnnotation(@Param("123")String testParamAnnotation) {

    }
    @Bean
    public void TestMethodAnnotation(@Param("123")String testParamAnnotation) {

    }
    @Bean
    public void TestSupMethodAnnotation(@Param("123")String testParamAnnotation) {

    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
