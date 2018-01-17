package com.ma.test.testJson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.alibaba.fastjson.JSONArray;

public class TestJson {

	public static void main(String[] args) {
		  File file=new File("E:\\update\\20180117");
		  FileInputStream fileInputStream = null;
          BufferedReader bufferedReader = null;
          File[] flist=file.listFiles();
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
            	  System.out.println(""+file2.getName());
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
