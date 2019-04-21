package com.atguigu;

import com.atguigu.ma.config.MyConfig;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class SpringmvcBootStrap {
    public static void main(String[] args) {
        //配置stomcat
        Tomcat tomcat = new Tomcat();
        tomcat.addContext("/","D:\\softdata\\study\\testData\\tomcat\\" );
        tomcat.setPort(8080);
        //配置springmvc
        // Load Spring web application configuration
        AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
        ac.register(MyConfig.class);
        //ac.refresh();

        // Create and register the DispatcherServlet
        DispatcherServlet servlet = new DispatcherServlet(ac);
        Wrapper wrapper = tomcat.addServlet("/", "app", servlet);
        //web容器启动执行创建和init方法
        wrapper.setLoadOnStartup(1);
        wrapper.addMapping("/app/*");

        try {
            tomcat.start();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
        tomcat.getServer().await();;
    }
}
