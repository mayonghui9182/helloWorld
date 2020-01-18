## spring的几个类：

### profiles

轮廓，筛选出你定义类时构建的特定轮廓。environment与profile相关的作用是决定那些轮廓被激活，以及默认激活那些轮廓，以及默认要激活那些轮廓。

### *Properties* 

属性，在所有程序中都扮演者重要角色，jdk类properties就是用来专门存储该接口的，spring将不同来源的properties进行了整合，包括properties文件，jvm系统属性，系统环境变量，jndi，servlet上下文参数，ad-hoc属性对象，Maps等等。environment为属性的配置和解析提供了相应的接口

### PropertyResolver和ConfigurablePropertyResolver

提供对properties的查询、判断、解析占位符${}的功能，即读取功能。ConfigurablePropertyResolver提供了对属性的配置和自定义功能，即写入功能。

### ConversionService

类型转换服务。	

### ConverterRegistry

类型转换服务注册器。向系统添加类型转换服务。

### ConfigurableConversionService

大多数（如果不是全部）ConversionService类型都将实现配置接口。 合并ConversionService的转换操作和ConverterRegistry的增删操作，以允许方便地临时添加和删除Converter。 当在应用程序上下文引导代码中处理ConfigurableEnvironment实例时，后者特别有用。

### TypeDescriptor

类型转换上下文。

### Environment和ConfigurableEnvironment

spring的环境类： *profiles* and *properties*以及访问他们的方法，即读取功能。ConfigurableEnvironment接口实现类配置和自定义功能，即写入功能。

### ApplicationContext

​	spring的程序上下文类：他的功能包括，作为beanfactory，事件发布器，资源解析器，访问环境类

### AnnotationConfigApplicationContext

```
AnnotationConfigApplicationContext
```