package com.ma.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import java.math.BigDecimal;

@Configuration
public class JacksonConfig2 {
    @Bean
    @Primary
    public ObjectMapper bigDecimalObjectMapper(Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder){
        ObjectMapper build = jackson2ObjectMapperBuilder.build();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(BigDecimal.class,new NumberDeserializers.BigDecimalDeserializer());
        simpleModule.addSerializer(BigDecimal.class,new BigDecimalSerializer())
        return jackson2ObjectMapperBuilder.build();
    }
}
