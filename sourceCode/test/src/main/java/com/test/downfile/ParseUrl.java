package com.test.downfile;

import com.alibaba.druid.util.StringUtils;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

public class ParseUrl {
    final Logger logger = LoggerFactory.getLogger(ParseUrl.class);

    /* */

    /**
     * 从输入流中获取字符串
     *
     * @param inputStream
     * @return
     * @throws IOException
     *//*
    public Long ParseZip(String path) throws IOException {
        ZipFile zipFile = new ZipFile(path);
        InputStream in = new BufferedInputStream(new FileInputStream(path));
        ZipInputStream zip = new ZipInputStream(in);
        Long line = 0L;
        String lineStr;
        ZipEntry ze;
        while ((ze = zip.getNextEntry()) != null) {
            if (!ze.isDirectory()) {
                System.err.println("file:" + ze.getName() + "\nsize:" + ze.getSize() + "bytes");
                if (ze.getSize() > 0) {
                    BufferedReader reader;
                    try {
                        reader = new BufferedReader(new InputStreamReader(zipFile.getInputStream(ze), "GB2312"));
                        while ((lineStr = reader.readLine()) != null) {
                            line++;
                        }
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        logger.info("文件解析完成");
        return line;
    }*/
    public String DownloadNet(String urlStr) throws MalformedURLException {
        // 下载网络文件
        int bytesum = 0;
        int byteread = 0;
        URL url = new URL(urlStr);
        String path = "D:\\SogouQ.reduced.tar.gz";
        try {
            URLConnection conn = url.openConnection();
            InputStream inStream = conn.getInputStream();
            FileOutputStream fs = new FileOutputStream(path);

            byte[] buffer = new byte[1204];
            while ((byteread = inStream.read(buffer)) != -1) {
                bytesum += byteread;
                fs.write(buffer, 0, byteread);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("文件下载完成");
        return path;
    }


    public int ParseFile(String file) {
        dbOperator dbOperator = new dbOperator();
        List<String[]> list = new ArrayList(100);
        dbOperator.dbConnection();
        int LineNum = 0;
        //jdk1.7 try语句块自动释放资源
        try (
                FileInputStream fis = new FileInputStream(new File(file));
                GZIPInputStream is = new GZIPInputStream(new BufferedInputStream(fis));
                ArchiveInputStream in = new ArchiveStreamFactory().createArchiveInputStream("tar", is);
                InputStreamReader inr = new InputStreamReader(is, "GB2312");//考虑到编码格式
                BufferedReader reader = new BufferedReader(inr)
        ) {
            //获取压缩包的文件
            TarArchiveEntry entry = (TarArchiveEntry) in.getNextEntry();
            String lineTxt = null;
            String[] datas;
            String[] keys;
            int i = 1;
            while (entry != null) {
                String name = entry.getName();//文件名
                while ((lineTxt = reader.readLine()) != null) {
                    if (StringUtils.isEmpty(lineTxt)) {
                        continue;
                    }
                    keys = lineTxt.split("\t");
                    list.add(keys);
                    LineNum++;
                    if (list.size() == 100) {
                        dbOperator.insertUrlQueryLog(list);
                        list.clear();
                    }
                }
                entry = (TarArchiveEntry) in.getNextEntry();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            dbOperator.insertUrlQueryLog(list);
        } catch (ParseException e) {
            System.out.println(e);
        }
        dbOperator.dbDisConnection();
        return LineNum;
    }

    public Integer countUrlQueryLog() {
        dbOperator dbOperator = new dbOperator();
        dbOperator.dbConnection();
        ResultSet resultSet = null;
        Integer count = null;
        try {
            resultSet = dbOperator.countUrlQueryLog("SELECT count(1) as count FROM webquerylog");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            if (resultSet != null && resultSet.next()) {
                count = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbOperator.dbDisConnection();
        return count;
    }

    public Integer getTop20UserID() {
        dbOperator dbOperator = new dbOperator();
        dbOperator.dbConnection();
        ResultSet resultSet = null;
        Integer count = null;
        try {
            resultSet = dbOperator.countUrlQueryLog("select userId,sum,RANK() OVER (ORDER BY t1.sum desc) RANK from (select * from (select userId,count(ID) sum from webquerylog group by userId) t order by t.sum desc) t1 where rownum<21");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            if (resultSet != null) {
                System.out.println("排名\tuserId\t记录数");
                while (resultSet.next()) {
                    System.out.print(resultSet.getInt("rank"));
                    System.out.print("\t");
                    System.out.print(resultSet.getString("userId"));
                    System.out.print("\t");
                    System.out.println(resultSet.getInt("sum"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public Integer getTop10Websit() {
        dbOperator dbOperator = new dbOperator();
        dbOperator.dbConnection();
        ResultSet resultSet = null;
        Integer count = null;
        try {
            resultSet = dbOperator.countUrlQueryLog("SELECT  RANK() OVER(ORDER BY t1.sum desc) RANK,address,sum  FROM (SELECT address,count(1) sum FROM (SELECT urlStr, CASE WHEN instr(urlStr, 'baidu.com') > 0 THEN 'baidu.com'  WHEN instr(urlStr, 'google.com') > 0 THEN  'google.com'  WHEN instr(urlStr, '360.com') > 0 THEN '360.com' END address FROM webquerylog t WHERE REGEXP_LIKE(t.urlStr, 'baidu.com|google.com|360.com')) t GROUP BY address ORDER BY sum desc) t1");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("排名\twebsit\t记录数");
            if (resultSet != null ) {
                while (resultSet.next()) {
                    int rank = resultSet.getInt("rank");
                    if(rank!=4){
                        System.out.print(rank);
                        System.out.print("\t");
                        String address = resultSet.getString("address");
                        System.out.print(address);
                        System.out.print("\t");
                        int sum = resultSet.getInt("sum");
                        System.out.println(sum);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public Integer getPerHourSum() {
        dbOperator dbOperator = new dbOperator();
        dbOperator.dbConnection();
        ResultSet resultSet = null;
        Integer count = null;
        try {
            resultSet = dbOperator.countUrlQueryLog("SELECT hours, COUNT(1) SUM  FROM (SELECT subStr(t.querydate,0,2) hours   FROM webquerylog t) t1 GROUP BY t1.hours ORDER BY t1.hours");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("时间\t记录数");
            if (resultSet != null ) {
                while (resultSet.next()) {
                    System.out.print(resultSet.getInt("hours"));
                    System.out.print("\t");
                    System.out.println(resultSet.getInt("SUM"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

}
