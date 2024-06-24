package com.tutorialninja.qa.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utilities {
	
	public static final int IMPLICITY_WAIT_TIME = 10;
	
	public static final int PAGELOAD_WAIT_TIME = 5;
	
	public static String generateEmailWithTimeStamp() {
		
		Date d = new Date();
		return "ram"+d.toString().replace(" ", "_").replace(":", "_")+"@gmail.com";
	}
	
	public static Object[][] getTestDataFromExcelSheet(String fileName,String sheetName) {
		
		String filePath = "\\src\\main\\java\\com\\tutorialninja\\qa\\testdata\\"+fileName+".xlsx";
		File file = new File(System.getProperty("user.dir")+filePath);
		FileInputStream fis;
		XSSFWorkbook workbook = null;
		try {
			fis = new FileInputStream(file);
			workbook = new XSSFWorkbook(fis);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		XSSFSheet sheet = workbook.getSheet(sheetName);
		int rows = sheet.getLastRowNum();
		int cols = (int)sheet.getRow(0).getLastCellNum();
		
		Object[][] data = new Object[rows][cols];
		
		for (int i = 0; i < rows; i++) {
			
			XSSFRow row = sheet.getRow(i+1);
			
			for (int j = 0; j < cols; j++) {
				
				XSSFCell cell = row.getCell(j);
				
				CellType cellType = cell.getCellType();
				
				switch (cellType) {
				
				case STRING:
					data[i][j] = cell.getStringCellValue();
					break;

				case NUMERIC:
					data [i][j]= Integer.toString((int)cell.getNumericCellValue());
					break;
				
				case BOOLEAN:
					data [i][j]= Boolean.toString(cell.getBooleanCellValue());
					break;
				}
			}
		}
		
		return data;
	}
	
	public static String captureScreenShot(WebDriver driver, String fileName) {
		
		String des = System.getProperty("user.dir")+"\\Screenshots\\"+fileName+".png";
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		try {
			FileHandler.copy(src, new File(des));
		} catch (IOException e) {
	
			e.printStackTrace();
		}
		
		return des;
	}

}
