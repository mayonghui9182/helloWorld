package com.atguigu.ma.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan("com.atguigu.ma")
public class MyConfig implements WebMvcConfigurer {


}
