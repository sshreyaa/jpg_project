package com.selenium.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ReadExcel {
	

	
	
	public Map<String, String> getKeyValuePair(String targetPath) throws IOException{
		FileInputStream file = new FileInputStream(new File(targetPath));
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet inputSheet = workbook.getSheet("Sheet1");
		int rowCount = inputSheet.getLastRowNum();
		System.out.println("total rows: " + rowCount);
		DataFormatter formatter = new DataFormatter();
		Map<String, String> keyValuePair = new HashMap<>();
		for (int rowNumber = 1; rowNumber <= rowCount ; rowNumber++) {
			XSSFRow row = inputSheet.getRow(rowNumber);
			keyValuePair.put(formatter.formatCellValue(row.getCell(0)), formatter.formatCellValue(row.getCell(1)));
		}
		return keyValuePair;
	}
}
