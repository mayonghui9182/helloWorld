package com.utils.string;

import org.apache.commons.lang3.StringUtils;

public class MaStringUtils {
	/*
	 * 比较字符串函数
	 * blank字符串视为相等
	 */
	 public static boolean equalString(String str1,String str2){
    	if(StringUtils.isBlank(str1)){
    		if(StringUtils.isBlank(str2)){
    			return true;
    		}else{
    			return false;
    		}
    	}
    	if(str1.equals(str2)){
    		return true;
    	}
    	return false;
    }
}
