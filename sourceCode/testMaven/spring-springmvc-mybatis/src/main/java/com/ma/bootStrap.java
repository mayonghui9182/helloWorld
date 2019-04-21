package com.ma;

import com.ma.config.App;
import com.ma.service.WebQueryLogService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class bootStrap {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(App.class);
        WebQueryLogService bean = ctx.getBean(WebQueryLogService.class);
        bean.get();
    }
}
