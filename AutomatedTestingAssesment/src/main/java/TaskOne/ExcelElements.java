package TaskOne;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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
			file = new FileInputStream (Constants.excelFileLocation);
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
	
	
	public void setInputValue(int num, int test, String input)
	{
		try {
			cell = sheet.getRow(num+1).getCell( (3*test)-3, MissingCellPolicy.RETURN_BLANK_AS_NULL);
		
			if (cell == null) {
				cell = sheet.getRow(num+1).createCell((3*test)-3);
	
				cell.setCellValue(input);
			}
			else {
				cell.setCellValue(input);
			}
			saveCellValue();
		}
		catch (Exception e) {}
		
	}
	public void setOutputValue(int num, int test, String input)
	{
		try { 
				cell = sheet.getRow(num+1).getCell( ((3*test)-2), MissingCellPolicy.RETURN_BLANK_AS_NULL);
		
			if (cell == null) {
				cell = sheet.getRow(num+1).createCell((3*test)-2);
				
				cell.setCellValue(input);
			}
			else {
				cell.setCellValue(input);
			}
			saveCellValue();
		}
		catch (Exception e) {}
	}
	
	public void setLogValue(int num, int test, String message)
	{
		try {
			cell = sheet.getRow(num+1).getCell(( (3*test)-1), MissingCellPolicy.RETURN_BLANK_AS_NULL);
		
			if (cell == null) {
				cell = sheet.getRow(num+1).createCell((3*test)-1);
				
				cell.setCellValue(message);;
			}
			else {
				cell.setCellValue(message);
			}
			saveCellValue();
		
		}
		catch (Exception e) {
			
		}
		
	}
	
	
	
	
	public void saveCellValue() {
		try {
			FileOutputStream fileOut = new FileOutputStream(Constants.excelFileLocation);
			workbook.write(fileOut);
			fileOut.flush();
			fileOut.close();
		}
		catch (IOException e) {
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
