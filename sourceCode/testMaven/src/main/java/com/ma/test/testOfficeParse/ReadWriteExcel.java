package com.ma.test.testOfficeParse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ma.utils.MaFileUtils;

public class ReadWriteExcel {

	private static final String EXCEL_XLS = "xls";
	private static final String EXCEL_XLSX = "xlsx";

	/**
	 * 判断Excel的版本,获取Workbook
	 * 
	 * @param in
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static Workbook getWorkbok(InputStream in, File file) throws IOException {
		Workbook wb = null;
		if (MaFileUtils.isNull(file)) {
			return wb;
		}
		if (file.getName().endsWith(EXCEL_XLS)) { // Excel 2003
			wb = new HSSFWorkbook(in);
		} else if (file.getName().endsWith(EXCEL_XLSX)) { // Excel 2007/2010
			wb = new XSSFWorkbook(in);
		}
		return wb;
	}

	/**
	 * 判断文件是否是excel
	 * 
	 * @throws Exception
	 */
	public static void isExcel(File file) throws Exception {
		if (file == null) {
			throw new Exception("file对象为空错误");
		}
		if (!file.exists()) {
			throw new Exception("文件不存在");
		}
		if (!(file.isFile() && (file.getName().endsWith(EXCEL_XLS) || file.getName().endsWith(EXCEL_XLSX)))) {
			throw new Exception("文件不是Excel");
		}
	}



	public static Map<String, List<List<String>>> readExcel(String path)throws Exception {
		//创建对象
		Map<String, List<List<String>>> sheets=new HashMap<String, List<List<String>>>();
		// 获取excel文件
		String[] fileType = { EXCEL_XLS, EXCEL_XLSX };
		File excelFile = MaFileUtils.getFile(path, fileType);
		if (excelFile == null) {
			throw new Exception("路径[" + path + "]不存在或文件不是excel文件！");
		}
		// 获取excel文件流
		FileInputStream is = new FileInputStream(excelFile);
		Workbook workbook = getWorkbok(is, excelFile);
		// Workbook workbook = WorkbookFactory.create(is); // 这种方式
		// Excel2003/2007/2010都是可以处理的
		if (workbook == null) {
			throw new Exception("获取workbook对象失败！");
		}
		/**
		 * 设置当前excel中sheet的下标：0开始
		 */
		for (Sheet sheet : workbook) {
			sheets.put(sheet.getSheetName(), readSheet(sheet));
		}
		return sheets;
	}
	public static Map<String, List<List<String>>> readExcel(File excelFile)throws Exception {
		//创建对象
		Map<String, List<List<String>>> sheets=new HashMap<String, List<List<String>>>();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		// 获取excel文件
		String[] fileType = { EXCEL_XLS, EXCEL_XLSX };
		if (MaFileUtils.ValidateFile(excelFile, fileType)) {
			throw new Exception("路径[" + excelFile.getAbsolutePath() + "]不存在或文件不是excel文件！");
		}
		// 获取excel文件流
		FileInputStream is = new FileInputStream(excelFile);
		Workbook workbook = getWorkbok(is, excelFile);
		// Workbook workbook = WorkbookFactory.create(is); // 这种方式
		// Excel2003/2007/2010都是可以处理的
		if (workbook == null) {
			throw new Exception("获取workbook对象失败！");
		}
		/**
		 * 设置当前excel中sheet的下标：0开始
		 */
		for (Sheet sheet : workbook) {
			sheets.put(sheet.getSheetName(), readSheet(sheet));
		}
		return sheets;
	}
	
	public static List<List<String>> readSheet(Sheet sheet)throws Exception {
		List<List<String>> rows=new ArrayList<List<String>>();
		for (Row row : sheet) {
			rows.add(readRow(row));
		}
		return null;
	}	
	public static List<String> readRow(Row row)throws Exception {
		List<String> cells = new ArrayList<String>();
		for (Cell cell : row) {
			cells.add(readCell(cell));
		}
		return cells;
	}	
	
	public static String readCell(Cell cell)throws Exception {
		String cellValue=null;
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		CellType cellType = cell.getCellTypeEnum();
		switch (cellType) {
		case STRING: // 文本
			cellValue=cell.getRichStringCellValue().getString();
			break;
		case NUMERIC: // 数字、日期
			if (DateUtil.isCellDateFormatted(cell)) {
				cellValue=fmt.format(cell.getDateCellValue());
			} else {
				cell.setCellType(CellType.STRING);
				cellValue=String.valueOf(cell.getRichStringCellValue().getString());
			}
			break;
		case BOOLEAN: // 布尔型
			cellValue=String.valueOf(cell.getBooleanCellValue());
			break;
		case BLANK: // 空白
			cellValue=cell.getStringCellValue();
			break;
		case ERROR: // 错误
			cellValue="错误#";
			break;
		case FORMULA: // 公式
			// 得到对应单元格的公式
			// cellValue = cell.getCellFormula() + "#";
			// 得到对应单元格的字符串
			cell.setCellType(CellType.STRING);
			cellValue=String.valueOf(cell.getRichStringCellValue().getString());
			break;
		default:
			cellValue="#";
		}
		return cellValue;
	}	
	
	/**
	 * 读取Excel测试，兼容 Excel 2003/2007/2010
	 * 
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		String[] fileType = { EXCEL_XLS, EXCEL_XLSX };
		String sourcePath = "C:\\Users\\98447_000\\Desktop\\新建 Microsoft Excel 工作表.xlsx";
		File xlsxfile = MaFileUtils.getFile(sourcePath, fileType);
		if (xlsxfile == null) {
			throw new Exception("路径[" + sourcePath + "]不存在或文件不是excel文件！");
		}
		Map<String, List<List<String>>> sheetsValue = readExcel(xlsxfile);
		for (Map.Entry<String,List<List<String>>> entry : sheetsValue.entrySet()) {
			String sheetName = entry.getKey();
			System.out.println(sheetName+"：");
			List<List<String>> rows = entry.getValue();
			for (List<String> list : rows) {
				for (String string : list) {
					System.out.print("\t");
					System.out.print(string);
				}
				System.out.print("\n");
			}
		}
	}

}
