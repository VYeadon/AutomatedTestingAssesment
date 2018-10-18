package com.qa.excelReadWrite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelElements {

	FileInputStream file;
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	XSSFCell cell;
	
	public ExcelElements() {
		openFile();
	}
	
	public void openFile()
	{
		try {
			file = new FileInputStream (Constants.fileLocation);
			workbook = new XSSFWorkbook(file);
			sheet = workbook.getSheetAt(0);
		} 
		catch (FileNotFoundException e) {
			System.out.println("No file found at location");
			e.printStackTrace();
		} catch (IOException e2) {
			System.out.println("Incorrect file format");
			e2.printStackTrace();
		}
	}
	
	public XSSFCell getUsername(int num)
	{
		cell = sheet.getRow(num).getCell(0);
		return cell;
	}
	public XSSFCell getPassword(int num)
	{
		cell = sheet.getRow(num).getCell(1);
		return cell;
	}
	public XSSFCell getExpected(int num)
	{
		cell = sheet.getRow(num).getCell(2);
		return cell;
	}
	
	public void setActual(int num, String input)
	{
		cell = sheet.getRow(num).getCell(3, MissingCellPolicy.RETURN_BLANK_AS_NULL);
		
		if (cell == null) {
			cell = sheet.getRow(num).createCell(3);

			cell.setCellValue(input);
		}
		else {
			cell.setCellValue(input);
		}
		
		
		try {
			FileOutputStream fileOut = new FileOutputStream(Constants.fileLocation);
			workbook.write(fileOut);
			fileOut.flush();
			fileOut.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void closeFile()
	{
		try {
			workbook.close();
			file.close();
		} 
		catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	
}
