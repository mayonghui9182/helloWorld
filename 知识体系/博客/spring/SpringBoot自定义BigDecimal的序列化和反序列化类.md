## SpringBoot自定义BigDecimal的序列化和反序列化类

​	前两天遇到一个需求，要求前端显示金额，百分比等数值类型的时候，需要用千分位分割。而这些数值在后端都是BigDecimal类型。SpirngBoot默认的序列化和反序列化框架是Jackson，所以就想着在Jackson序列化的时候对BigDecimal数据类型做处理。四种方法，三种作用域全局，一种作用于局部：

首先写序列化和反序列化类：

序列化器：

```java
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.boot.jackson.JsonComponent;
import java.io.IOException;
import java.math.BigDecimal;

@JsonComponent
public class BigDecimalDeSerializer extends JsonDeserializer<BigDecimal> {
    @Override
    public BigDecimal deserialize(JsonParser jsonParser, 
                                  DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        //千分位分隔的数值从前端到后端是需要反序列化为BigDecimal。需要去掉“,”
        return new BigDecimal(jsonParser.getText().replaceAll(",",""));
    }
}

```

​	反序列化器

```java
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import org.springframework.boot.jackson.JsonComponent;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Objects;

@JsonComponent
public class BigDecimalSerializer extends JsonSerializer<BigDecimal> implements ContextualSerializer {
    private String format = "###,##0.00";

    @Override
    public void serialize(BigDecimal bigDecimal, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(new DecimalFormat(format).format(bigDecimal));
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        if(beanProperty !=null ){
            if(Objects.equals(beanProperty.getType().getRawClass(),BigDecimal.class)){
                BigDecimalFormat bigDecimalFormat = beanProperty.getAnnotation((BigDecimalFormat.class));
                if(bigDecimalFormat == null){
                    bigDecimalFormat = beanProperty.getContextAnnotation(BigDecimalFormat.class);
                }
                BigDecimalSerializer bigDecimalSerializer = new BigDecimalSerializer();
                if(bigDecimalFormat != null){
                    bigDecimalSerializer.format = bigDecimalFormat.value();
                }
                return bigDecimalSerializer;
            }
            return serializerProvider.findValueSerializer(beanProperty.getType(),beanProperty);
        }
        return serializerProvider.findNullValueSerializer(beanProperty);
    }
}
```

## 自定义注解实现（局部实用）：

​	写一个注解，如下：@BigDecimalFormat，在你需要使用该序列化和反序列化器的时候直接在字段上面注释即可。这样只有被注解的字段才会使用自定义的序列化和反序列化器。

~~~java
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@JacksonAnnotationsInside
@JsonSerialize(using = BigDecimalSerializer.class)
@JsonDeserialize(using = BigDecimalDeSerializer.class)
public @interface BigDecimalFormat {
    String value() default "###,##0.00";
}
~~~

## 用@JsonComponent

​		这是最简单的方法，直接作用于全局，所有对BigDecima序列化和反序列化的时候都会使用这两个序列化和反序列类，简单实用。为了方便，在上面的序列化和反序列化类上直接加上了@JsonComponent。加了@JsonComponent的类会被一个JsonComponentModual加载。

## 集成SimpleModual

​	在没有在类上加上@JsonComponent的时候，可以通过下面方式实现。编写一个SimpleModule的子类，在modual里面加上自定义的序列化器。这个类会被Jackson2ObjectMapperBuilder加载自定义modual的时候加载。

~~~java
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
~~~



## 在`Jackson2ObjectMapperBuilder` 中添加序列化和反序列化器

下面类定义的@bean方法bigDecimalObjectMapper()，并不是为了构建一个Bean，而是为了将两个序列化器添加到Jackson2ObjectMapperBuilder中，这样，其他所有的Jackson2ObjectMapperBuilder构建和配置的modual都会拥有这两个序列化和反序列器。

~~~java

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
~~~

## SpringBoot官网（未成功）：

springboot官网有下面一种实现：

```
If you want to replace the default ObjectMapper completely, either define a @Bean of that type and mark it as @Primary or, if you prefer the builder-based approach, define a Jackson2ObjectMapperBuilder @Bean. Note that, in either case, doing so disables all auto-configuration of the ObjectMapper.
```

和Jackson2ObjectMapperBuilder实现很像。我的实验代码如下：

```java
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.context.annotation.Primary;
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
```

但是这个代码却未起到效果，我不太明白是哪里理解错了。我试过将方法名命名为objectMapper和bigDecimalObjectMapper等，但是都没有效果，

## 总结

​	在使用自定义注解实现的时候，你可以自定义格式化格式字符串，对一些字段做特殊处理。这个实现可以和其他三个全局实现一起使用，以便对字段做特殊处理。其他三种全局实现任选一种即可。个人推荐@JsonComponent。

​	