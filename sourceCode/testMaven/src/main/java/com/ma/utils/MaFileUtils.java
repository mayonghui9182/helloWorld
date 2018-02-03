package com.ma.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import jdk.nashorn.internal.ir.ForNode;

public class MaFileUtils {
	/*
	 * 根据路径path获取fileType类型的文件，
	 * 如果path不是FileType类型，返回null
	 * 如果fileType为null或者长度为0，匹配任意类型的文件
	 */
	public static File getFile(String path,String[] fileType){
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
	/*
	 * 根据路径path获取fileType类型的文件，
	 * 如果path不是FileType类型，返回null
	 * 如果fileType为null或者长度为0，匹配任意类型的文件
	 */
	public static boolean ValidateFile(File file,String[] fileType){
		if(file==null){
			return false;
		}
		if(!file.exists()){
			return false;
		}
		if(!file.isFile()){
			return false;
		}
		if(fileType!=null&& fileType.length!=0){
			for (String type : fileType) {
				if(file.getName().endsWith(type))
					return true;
			}
			return false;
		}
		return true;
	}
	
	public static FileInputStream  getFileInputStream(String path){
		FileInputStream fileInputStream =null;
		File file = getFile(path,null);
		if(file!=null){
			try {
				fileInputStream = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				return fileInputStream;
			}
		}
		return fileInputStream;
	}
	
	/*
	 * 判断path对应的文件或文件夹是不是存在
	 * null时为true
	 * 不存在为true
	 */
	public static boolean isNull(String path){
		if(path==null){
			return true;
		}
		File file=new File(path);
		if(!file.exists()){
			return true;
		}
		return false;
		
	}
	/*
	 * 判断path对应的文件或文件夹是不是存在
	 * null时为true
	 * 不存在为true
	 */
	public static boolean isNull(File file){
		if(file==null){
			return true;
		}
		if(!file.exists()){
			return true;
		}
		return false;
		
	}
}
