package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/HelloRequestMapping")
public class HelloRequestMapping {

    @RequestMapping("/sayHello")
    @ResponseBody
    public String sayHello(){
        return "hello";
    }
}
