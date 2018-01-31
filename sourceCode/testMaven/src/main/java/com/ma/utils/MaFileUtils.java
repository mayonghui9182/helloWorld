package com.ma.utils;

import java.io.File;

public class MaFileUtils {
	public File gitFile(String path){
		if(path==null){
			return null;
		}
		File file=new File(path);
		if(!file.exists()){
			return null;
		}
		return file;
	}
}
