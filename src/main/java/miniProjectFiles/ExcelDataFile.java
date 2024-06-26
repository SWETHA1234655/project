package miniProjectFiles;

import java.io.File;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataFile {
	
	public static String orginFrom;
	
	public static String destinationTo;
	
	static XSSFCell orgin;
	
	static XSSFCell destination;
	
       //Read the data from excel file
		
		public static void ExcelData() {
			
		try {
			
			File excelData=new File("C:\\Users\\2318570\\eclipse-workspace\\project\\testData\\FlightExcelData.xlsx");
			
			FileInputStream input=new FileInputStream(excelData);
			
			XSSFWorkbook workbook=new XSSFWorkbook(input);
			
			XSSFSheet sheet=workbook.getSheetAt(0);
			
			XSSFRow row=sheet.getRow(1);
			
			orgin=row.getCell(0);
			
			destination=row.getCell(1);
			
			orginFrom=orgin.getStringCellValue();
			
			destinationTo=destination.getStringCellValue();
			
		}
		
		
		catch(Exception e) {
			
			System.out.println(e);
		}
		
	}
		
		//Write a data to excel file
		
		public static void setExcelValue(String expected,String actual) throws Exception{
				
			FileOutputStream file=new FileOutputStream("C:\\Users\\2318570\\eclipse-workspace\\project\\testData\\setValue.xlsx");
				
			XSSFWorkbook wb=new XSSFWorkbook();
				
			XSSFSheet sheet=wb.createSheet("output");
				
			XSSFRow row=sheet.createRow(0);
				
			row.createCell(0).setCellValue("Actual_output");
			row.createCell(1).setCellValue("Expected_output");
			row.createCell(2).setCellValue("Expected_Result");
			row.createCell(3).setCellValue("Actual_Result");
				
			XSSFRow row1=sheet.createRow(1);
				
			row1.createCell(0).setCellValue(actual);
			row1.createCell(1).setCellValue(expected);
			row1.createCell(2).setCellValue("PASS");
			row1.createCell(3).setCellValue((actual.equals(expected))?"PASS":"FAILED");
				
			wb.write(file);

			file.close();
				
				
		}
			
			
}
		
		
	


