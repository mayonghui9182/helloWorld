package com.ma.test.Class.annotation;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class AnnotationClass extends AnnotationSupClass implements Serializable,FactoryBean<String>
{
    @Bean
    public static void TestStaticMethodAnnotation(@Param("123")String testParamAnnotation) {

    }
    @Override
    @Bean
    public void TestMethodAnnotation(@Param("123")String testParamAnnotation) {

    }
    @Bean
    public void TestMethodAnnotation(@Param("123")String testParamAnnotation,String string) {

    }
    @Bean
    public void TestSubMethodAnnotation(@Param("123")String testParamAnnotation) {

    }

    @Override
    public String getObject() throws Exception {
        return null;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }
}
