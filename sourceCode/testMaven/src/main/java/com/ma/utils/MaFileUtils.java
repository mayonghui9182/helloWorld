package com.ma.utils;

import java.io.File;

public class MaFileUtils {
	/*
	 * 根据路径path获取fileType类型的文件，
	 * 如果path不是FileType类型，返回null
	 * 如果fileType为null或者长度为0，匹配任意类型的文件
	 */
	public static File gitFile(String path,String[] fileType){
		if(path==null){
			return null;
		}
		File file=new File(path);
		if(!file.exists()){
			return null;
		}
		if(!file.isFile()){
			return null;
		}
		if(fileType!=null&& fileType.length!=0){
			for (String type : fileType) {
				if(file.getName().endsWith(type))
					return file;
			}
			return null;
		}
		return file;
	}
}
