package com.ma.test.Class.annotation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class TestAnnotation {

    @Test
    /** 只能获取本类中中的注解，不能获取父类中的注解 by mayh*/
    private  void testAnnotations() {
        Annotation[] annotations = AnnotationClass.class.getAnnotations();
        for (int i = 0; i < annotations.length; i++) {
            System.out.println(annotations[i].toString());
        }
        Method[] methods = AnnotationClass.class.getMethods();
        Annotation[] annotations1 = methods[1].getAnnotations();
        for (int i = 0; i < annotations1.length; i++) {
            System.out.println(annotations1[i].toString());
        }
        Parameter[] parameters = methods[1].getParameters();
        Annotation[] annotations2 = parameters[0].getAnnotations();
        for (int i = 0; i < annotations2.length; i++) {
            System.out.println(annotations2[i].toString());
        }
    }

    @Test
    /** 只能获取本类的接口，不能获取父类中的接口 by mayh*/
    public  void testGetInterfaces() {
        Class<?>[] interfaces = AnnotationClass.class.getInterfaces();
        for (int i = 0; i < interfaces.length; i++) {
            Method[] declaredMethods = interfaces[i].getDeclaredMethods();
            for (int j = 0;  j< declaredMethods.length; j++) {
                System.out.println(declaredMethods[j].toString());
            }
        }
    }

    /** 只返回本来的方法 by mayh*/
    @Test
    public  void testGetMethod() {
        Method[] declaredMethods = AnnotationClass.class.getDeclaredMethods();
        for (int i = 0; i < declaredMethods.length; i++) {
            System.out.println(declaredMethods[i].toString());
        }
    }


}
