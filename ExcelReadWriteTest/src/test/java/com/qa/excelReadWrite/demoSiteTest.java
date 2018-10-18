package com.qa.excelReadWrite;

import static org.junit.Assert.*;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class demoSiteTest {
	
	public WebDriver driver = null;
	
	public ExtentReports extent;
	public ExtentTest test;
	
	XSSFCell cell;
	
	DemoSiteElements elementGetter;
	ExcelElements excelGetter;
	
	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", 
				"C:\\Users\\Admin\\Documents\\TestingJava\\chromedriver.exe\\");
		
		driver = new ChromeDriver();

		extent = new ExtentReports("ExcelReadWriteReport.html",true);
	}
	
	@Test
	public void test() {
		test = extent.startTest("Testing excel inputs and outputs for logging into site");
		
		driver.get(Constants.websiteURL);
		
		test.log(LogStatus.INFO, "Web page loaded");
		
		elementGetter = PageFactory.initElements(driver, DemoSiteElements.class);
		
		excelGetter = PageFactory.initElements(driver, ExcelElements.class);
	
		// ------------------- Registration page
		elementGetter.clickAddUserPage();
		
		elementGetter
			.getusernameBox()
			.sendKeys(excelGetter.getUsername(1).getStringCellValue());
		
		elementGetter
			.getpasswordBox()
			.sendKeys(excelGetter.getExpected(1).getStringCellValue());
		
		elementGetter.clickSubmit();
		
		test.log(LogStatus.INFO, "Account created using details");
		
		// -------------------- Login page
		elementGetter.clickLoginPage();
		
		elementGetter
			.getusernameBox()
			.sendKeys(excelGetter.getUsername(1).getStringCellValue());
	
		elementGetter
			.getpasswordBox()
			.sendKeys(excelGetter.getExpected(1).getStringCellValue());
		
		elementGetter.clickSubmit();
		
		test.log(LogStatus.INFO, "Account log on token submitted using details");
		
		String verificationText = elementGetter.getverificationtext();
		String verificationTextExcel = excelGetter.getExpected(1).getStringCellValue();
		
		excelGetter.setActual(1, verificationText);
		
		test.log(LogStatus.INFO, "Verification text has been input into the excel sheet");
		
		if(verificationTextExcel.equals(verificationText)) {
			test.log(LogStatus.PASS, "Account has succesfully created and been logged into using details from excel sheet");
		}
		else {
			test.log(LogStatus.FAIL, "Test has failed");
		}
		
		assertEquals(verificationTextExcel,verificationText);
	}
	

	@After
	public void teardown() {
		extent.endTest(test);
		extent.flush();
		extent.close();
		
		excelGetter.closeFile();
		
		driver.quit();
	}

}
