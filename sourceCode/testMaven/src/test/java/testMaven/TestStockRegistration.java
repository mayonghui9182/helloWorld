package testMaven;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.ma.test.testOfficeParse.ReadWriteExcel;

public class TestStockRegistration {
	private static String path = "E:\\document\\96\\4.产品代码分配表-模板20180202.xlsx";
	public static void main(String[] args) {
		// 获取excel文件流
		Map<String, List<List<String>>> rows=null;
		try {
			rows = ReadWriteExcel.readExcel(path);
		} catch (Exception e) {
			System.out.println("读取excel文件失败！");
		}
		List<List<String>> sheet = rows.get("产品信息确认模板");
		if(sheet==null){
			return null;
		}
	}
}
