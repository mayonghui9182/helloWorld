package com.utils.file;

import java.io.File;
import java.io.FileFilter;

public class Fileutils {
	private static FileFilter directoryFilter= new FileFilter() {
        @Override
        public boolean accept(File pathname) {
            return pathname.isDirectory();
        }
    }; 
    private static FileFilter fileFilter= new FileFilter() {
    	@Override
    	public boolean accept(File pathname) {
    		return pathname.isFile();
    	}
    }; 
    /*
     * 返回子文件夹列表
     */
    public File[] directorys(File file){
    	if(file==null){
    		return null;
    	}
    	if(!file.exists()){
    		return null;
    	}
    	return file.listFiles(directoryFilter);
    	
    }
    /*
     * 返回子文件列表
     */
    public File[] files(File file){
    	if(file==null){
    		return null;
    	}
    	if(!file.exists()){
    		return null;
    	}
    	return file.listFiles(fileFilter);
    	
    }
}
