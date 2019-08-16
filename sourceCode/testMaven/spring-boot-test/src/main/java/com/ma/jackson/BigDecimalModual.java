package com.ma.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class BigDecimalModual extends SimpleModule {
    public BigDecimalModual(){
        addSerializer(BigDecimal.class,new BigDecimalSerializer());
        addDeserializer(BigDecimal.class,new BigDecimalDeSerializer());
    }
}
