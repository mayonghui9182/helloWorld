package com.ma.test.Class;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.annotation.Annotation;
@Component
public class Clazz {
    public static void main(String[] args) {
        Annotation[] annotations = Clazz.class.getAnnotations();
        for (Annotation a:annotations) {
            System.out.println(a.annotationType().getName());
        }
    }

    @Bean
    public Clazz getClazz(@RequestParam String s){
        return new Clazz();
    }

}
