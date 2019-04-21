package com.atguigu.ma.controller;

import com.atguigu.bean.Blue;
import org.hibernate.annotations.Parameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

@Controller
@CrossOrigin
@RequestMapping("/HelloController")
public class HelloContoller {

    @RequestMapping("/downFile")
    public ResponseEntity downFile(@RequestParam(required = false) Date date, HttpServletRequest request) {
        byte[] bytes = null;
        try {
            bytes = Files.readAllBytes(new File("C:\\Users\\ma\\Desktop\\test.xlsx").toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        URI uri = null;
        try {
            uri = new URI(request.getRequestURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return ResponseEntity.created(uri)
                .header("Access-Control-Allow-Headers", "Content-Type")
                .header("Access-Control-Expose-Headers", "File")
                .header("Access-Control-Allow-Headers", "File")
                .header("File", "File")
                .body(bytes);
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add("Access-Control-Allow-Headers", "Content-Type");
//        httpHeaders.add("Access-Control-Expose-Headers", "File");
//        httpHeaders.add("Access-Control-Allow-Headers", "File");
//        httpHeaders.add("File", "test");
//        System.out.println("调用了");
//        return new ResponseEntity(bytes,httpHeaders,HttpStatus.OK);
    }

    @RequestMapping("/sayHello")
    public String sayHello(@RequestParam(required = false) Date date) {
        System.out.println("sayHello:");
        return "hello";
    }
    @RequestMapping("/testMessageConvertor")
    @ResponseBody
    public String testMessageConvertor(Date date, Blue blue) {
        System.out.println("sayHello:");
        return "hello";
    }
    @RequestMapping("/testObjectParameter")
    @ResponseBody
    public String testObjectParameter(@RequestParam Blue param,@RequestBody Blue body) {
        System.out.println("sayHello:");
        return "hello";
    }
}
