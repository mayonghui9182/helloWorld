package com.ma.test.poiTest;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class poiTest {
    public static void main(String[] args) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\myh\\Desktop\\test.xls");
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet sheet = hssfWorkbook.createSheet();
        CellStyle cellStyle = hssfWorkbook.createCellStyle();
        for (int i = 0; i < 5000; i++) {
            HSSFRow row = sheet.createRow(i);
            for (int j = 0; j < 10; j++) {
                HSSFCell cell = row.createCell(j);
                cell.setCellValue("123");
                cell.setCellStyle(cellStyle);
            }
        }
        hssfWorkbook.write(fileOutputStream);
    }
}
