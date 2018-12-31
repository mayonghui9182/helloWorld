package com.test.downfile;


import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;


public class dbOperator {
    Connection conn = null;
    PreparedStatement pstmt = null;

    public void insertUrlQueryLog(List<String[]> strList) throws ParseException {
        try {
            StringBuffer sqlBf = new StringBuffer();
            sqlBf.setLength(0);

            sqlBf.append("insert into webquerylog (ID, QUERYDATE, USERID, QUERYWORDS, URLPLACE, CLICKORDER, URLSTR)\n");
            sqlBf.append("values(SEQ_webquerylog.Nextval,?,?,?,?,?,?)");
            pstmt = conn.prepareStatement(sqlBf.toString());
            pstmt.clearParameters();

            for (int i = 0; i < strList.size(); i++) {
                //处理空行
                try {
                    pstmt.setString(1, strList.get(i)[0]);
                    pstmt.setString(2, strList.get(i)[1]);
                    pstmt.setString(3, strList.get(i)[2]);
                    pstmt.setString(4, strList.get(i)[3].split(" ")[0]);
                    if(strList.get(i)[3].split(" ").length==2){
                        pstmt.setString(5, strList.get(i)[3].split(" ")[1]);
                    }else{
                        pstmt.setNull(5, Types.VARCHAR);
                    }
                    pstmt.setString(6, strList.get(i)[4]);
                }catch (Exception e){
                    continue;
                }
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            conn.commit();//执行
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 连接数据库
    public  Connection dbConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
            String user = "member";
            String password = "member";
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connection 开启！");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    // 关闭数据库
    public void dbDisConnection() {
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Connection 关闭！");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

     public ResultSet countUrlQueryLog(String sql) throws ParseException {
         ResultSet resultSet =null;
         try {
            StringBuffer sqlBf = new StringBuffer();
            sqlBf.setLength(0);

            sqlBf.append(sql);
            pstmt = conn.prepareStatement(sqlBf.toString());
            resultSet = pstmt.executeQuery();
        } catch (Exception e) {
            System.out.println(e);
        }
         return resultSet;
     }
}
