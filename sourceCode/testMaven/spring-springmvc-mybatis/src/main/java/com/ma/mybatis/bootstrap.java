package com.ma.mybatis;

import com.ma.mapper.WebQueryLogMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.platform.commons.util.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;

public class bootstrap {
    public static Logger logger=LoggerFactory.getLogger(bootstrap.class.getName());
    public static void main(String[] args) {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        session.getConfiguration().addMapper(WebQueryLogMapper.class);
        WebQueryLogMapper mapper = session.getMapper(WebQueryLogMapper.class);
        mapper.get().forEach((key,value)->{
            System.out.println(key+value.toString());
        });
        logger.info("end---------------------");
    }
}
