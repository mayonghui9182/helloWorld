package test;

import static org.junit.Assert.assertTrue;

import com.test.downfile.ParseUrl;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.util.Date;

/**
 * Unit test for simple App.
 */
public class AppTest {
    final Logger logger = LoggerFactory.getLogger(ParseUrl.class);
    private ParseUrl parseUrl = new ParseUrl();
    private String url = "http://download.labs.sogou.com/dl/sogoulabdown/SogouQ/SogouQ.reduced.tar.gz";

    /**
     * 下载文件并解析到数据库
     */
    @Test
    public void testParseUrlToDB() {
        Long startTime = new Date().getTime();
        String url = "http://download.labs.sogou.com/dl/sogoulabdown/SogouQ/SogouQ.reduced.tar.gz";
        int lineNum = 0;
        //System.out.println(parseUrl.ParseFile("D:\\SogouQ.reduced.tar.gz"));
        try {
            lineNum = parseUrl.ParseFile(parseUrl.DownloadNet(url));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Long endTime = new Date().getTime();
        logger.info("总共有" + lineNum + "行数据");
        logger.info("总共耗时" + (endTime - startTime) + "ms");
    }

    /**
     * 获取前20用户ID总记录数
     */
    @Test
    public void getTop20UserID() {
        Long startTime = new Date().getTime();
        Integer count = parseUrl.getTop20UserID();
        Long endTime = new Date().getTime();

        logger.info("总共耗时" + (endTime - startTime) + "ms");
    }

    /**
     * 获取各个小时时间段总记录数
     */
    @Test
    public void getPerHourSum() {
        Long startTime = new Date().getTime();
        Integer count = parseUrl.getPerHourSum();
        Long endTime = new Date().getTime();

        logger.info("总共耗时" + (endTime - startTime) + "ms");
    }

    /**
     * 获取总记录数
     */
    @Test
    public void getTop10Websit() {
        Long startTime = new Date().getTime();
        Integer count = parseUrl.getTop10Websit();
        Long endTime = new Date().getTime();

        logger.info("总共耗时" + (endTime - startTime) + "ms");
    }

    /**
     * 获取总记录数
     */
    @Test
    public void count() {
        Long startTime = new Date().getTime();
        Integer count = parseUrl.countUrlQueryLog();
        Long endTime = new Date().getTime();

        System.out.println("总记录数:" + count);
        logger.info("总共耗时" + (endTime - startTime) + "ms");
    }
}
