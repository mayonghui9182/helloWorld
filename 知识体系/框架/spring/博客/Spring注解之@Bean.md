# Spring注解之@Bean

​	前两天遇到一个需求，前段显示金额，百分比的等数值类型的时候，需要用千分位分割。而这些数字在后端都是BigDecimal类型。所以就想着在Jackson序列化的时候对BigDecimal数据类型做处理。三种方法：

