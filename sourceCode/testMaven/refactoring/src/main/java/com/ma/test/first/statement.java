package com.ma.test.first;

import java.util.Map;
import java.util.Properties;

public class statement {
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            System.out.print(args[i]+" ");
        }

        System.out.println("============");

        Properties properties = System.getProperties();
        properties.forEach((key,value)->{
            System.out.println(key +"===="+ value);
        });
        System.out.println("============");
        Map envs = System.getenv();
        envs.forEach((key,value)->{
            System.out.println(key +"===="+ value);
        });
    }
}
