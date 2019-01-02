package com.ma.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.ma")
@MapperScan("com.ma.mapper")
public class App {
    @Bean
    public DataSource getDataSource(){
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        driverManagerDataSource.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
        driverManagerDataSource.setUsername("member");
        driverManagerDataSource.setPassword("member");
        return driverManagerDataSource;
    }

    @Bean
    public SqlSessionFactoryBean getSessionFactory(){
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(getDataSource());
        return sessionFactoryBean;
    }
}
