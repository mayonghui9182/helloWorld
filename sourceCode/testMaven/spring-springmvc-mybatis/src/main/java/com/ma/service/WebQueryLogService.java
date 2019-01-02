package com.ma.service;

import com.ma.mapper.WebQueryLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class WebQueryLogService {

    @Autowired
    private WebQueryLogMapper mapper;

    public void get(){
        Map<String, Object> stringObjectMap = mapper.get();
        stringObjectMap.forEach((key, value)->{
            System.out.println(key+value.toString());
        });
    }
}
