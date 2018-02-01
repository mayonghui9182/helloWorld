package com.myh.sum;

import java.io.File;
import java.io.FileFilter;
import java.nio.file.DirectoryStream.Filter;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class SumFileCount {

	public static void main(String[] args) {
		int sum=0;
		FileFilter fileFilter=new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				return pathname.isDirectory();
			}
		};
		String currentDate = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
		String SyncPath="D:"+File.separator.charAt(0) +currentDate;
        //确认存放产品信息文件夹存在
        File product=new File(SyncPath);
        if(!product.exists()){
           System.out.println("不存在附件路径："+product+"！");
        }
        //根据产品文件夹获取所有产品信息文件夹
        File[] productInfos=product.listFiles(fileFilter);
        //遍历所有产品信息
        for (File productInfo : productInfos) {
            //如果不是文件夹，跳过循环
            if(!productInfo.isDirectory()){
                continue;
            }
            //获取单个产品的最新产品信息，即文件夹名最大的文件夹
            File[] listProductInfo = productInfo.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    return pathname.isDirectory();
                }
            });
            File newProductInfo=listProductInfo[0];
            for (int i = 1; i < listProductInfo.length; i++) {
                if(listProductInfo[i].getName().compareTo(newProductInfo.getName())>0){
                    newProductInfo=listProductInfo[i];
                }
            }
            File accessoryFolder=new File(newProductInfo.getAbsoluteFile().toString()+File.separator.charAt(0)+"datafiles");
			if(!accessoryFolder.exists()){
				continue;
			}
            sum+=accessoryFolder.listFiles().length;
        }
        System.out.println(sum);
	}
}
