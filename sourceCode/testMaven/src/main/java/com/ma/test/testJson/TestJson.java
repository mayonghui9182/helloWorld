package com.ma.test.testjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.ss.formula.functions.T;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestJson {

    public static void main(String[] args) {
        jsonArrayToList();
    }

    private static void jsonToMap(){
        String jsonStr="{\"adress\":\"北京市海淀区\",\"age\":18,\"name\":\"马永辉\",\"sex\":\"男\"}";
        Object object = JSONObject.parse(jsonStr);
        Map<String,Object> map=(Map)object;
        map.forEach((key,value)->{
            System.out.println(key+":"+value);
        });
    }
    private static void jsonArrayToList(){
        String jsonStr="[{\"adress\":\"北京市海淀区\",\"age\":18,\"name\":\"马永辉\",\"sex\":\"男\"},{\"adress\":\"北京市海淀区\",\"age\":18,\"name\":\"马永辉\",\"sex\":\"男\"}]";
        List  list = (List) JSON.parse(jsonStr);
        System.out.println((list instanceof List));
        list.forEach(map -> {
            System.out.println((map instanceof Map));
            ((Map)map).forEach((key,value)->{
                System.out.println(value instanceof String);
                System.out.println(value instanceof List);
                System.out.println(key+":"+value);
            });
        });
    }

    private static void arrayToJSONTest() {
        ArrayList<TestBean> testBeans = new ArrayList<>();
        TestBean testBean = new TestBean();
        testBean.setName("马永辉");
        testBean.setAge(18);
        testBean.setSex("男");
        testBean.setAdress("北京市海淀区");
        testBeans.add(testBean);

        TestBean testBean1 = new TestBean();
        testBean1.setName("马永辉");
        testBean1.setAge(18);
        testBean1.setSex("男");
        testBean1.setAdress("北京市海淀区");
        testBeans.add(testBean1);
        System.out.println(JSON.toJSONString(testBeans));

    }

    private static void beanToJSONTest() {
        TestBean testBean = new TestBean();
        testBean.setName("马永辉");
        testBean.setAge(18);
        testBean.setSex("男");
        testBean.setAdress("北京市海淀区");
        System.out.println(JSON.toJSONString(testBean));

    }

    private static void testJSON() {
        File file = new File("E:\\update\\20180117");
        FileInputStream fileInputStream = null;
        BufferedReader bufferedReader = null;
        File[] flist = file.listFiles();
        for (File file2 : flist) {
            try {
                fileInputStream = new FileInputStream(file2);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
                bufferedReader = new BufferedReader(inputStreamReader);
                String text = null;
                StringBuffer sb = new StringBuffer();
                while ((text = bufferedReader.readLine()) != null) {
                    sb.append(text);
                }
                JSONArray.parseArray(sb.toString());
                fileInputStream.close();
                bufferedReader.close();
            } catch (Exception e) {
                System.out.println("" + file2.getName());
                e.printStackTrace();
            } finally {
                //关闭文件流
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException io) {
                        io.printStackTrace();
                    }
                }
                //关闭文件输入流
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException io) {
                        io.printStackTrace();
                    }
                }
            }
        }
    }
}
