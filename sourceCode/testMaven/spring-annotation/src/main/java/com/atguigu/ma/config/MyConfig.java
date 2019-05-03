package com.atguigu.ma.config;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
@ControllerAdvice
@ComponentScan("com.atguigu.ma")
public class MyConfig implements WebMvcConfigurer {
    @InitBinder()
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new MyCustomDateEditor());
    }
}
class MyCustomDateEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text){
        Date date = new Date(Long.parseLong("1556702238625"));
        setValue(date);
    }

}