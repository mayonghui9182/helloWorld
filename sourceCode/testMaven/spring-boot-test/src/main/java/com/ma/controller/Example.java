package com.ma.controller;

import org.springframework.boot.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/Example")
public class Example {

    @RequestMapping("/home")
    public String home() {
        return "Hello World!";
    }
    @RequestMapping("/getJSP")
    public String getJSP() {
        return "index";
    }
}