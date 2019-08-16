package com.ma.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import java.math.BigDecimal;

@Configuration
public class JacksonConfig {
    @Bean
    public ObjectMapper bigDecimalObjectMapper(Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder){
        jackson2ObjectMapperBuilder.serializerByType(BigDecimal.class,new BigDecimalSerializer());
        jackson2ObjectMapperBuilder.deserializerByType(BigDecimal.class,new NumberDeserializers.BigDecimalDeserializer());
        return jackson2ObjectMapperBuilder.build();
    }
}
