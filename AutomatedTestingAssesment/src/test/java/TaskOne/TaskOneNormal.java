package TaskOne;


import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class TaskOneNormal {
	
	public WebDriver driver = null;
	
	public ExtentReports extent;
	public ExtentTest test;
	
	WebsiteElements elementGetter;
	
	String username = "user";
	String password = "password";
	String fullname = "New User";
	String email = "user@new.com";
	
	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", 
				"C:\\Users\\Admin\\Documents\\TestingJava\\chromedriver.exe\\");
		
		driver = new ChromeDriver();

		extent = new ExtentReports("C:\\Users\\Admin\\Documents\\TestingJava\\AssesmentTaskOneNormal.html",true);
		
		elementGetter = PageFactory.initElements(driver, WebsiteElements.class);
	}
	
	@Test
	public void test() {
		test = extent.startTest("Task one using normal inputs (non-cucumber)");
		
		driver.get(Constants.websiteURL);
		
		test.log(LogStatus.INFO, "Web page loaded");
		
		elementGetter.signIn();
		
		test.log(LogStatus.INFO, "Admin has logged onto the webpage");
		
		elementGetter.getManageJenkinsButton().click();
		elementGetter.getManageUsersButton().click();
		elementGetter.getCreateUsersButton().click();
		
		try {
			elementGetter.getCreateUserForm();
			test.log(LogStatus.INFO, "Succesfully navigated to the sign up page");
		}
		catch (NoSuchElementException e) {
			test.log(LogStatus.FAIL, "test has not been able to succesfully navigate to the create user page");
			fail();
		}
		
		elementGetter.setValuesforCreateUser(username, password, fullname, email);
		elementGetter.submitCreateUserForm();
		
		// checks to see if account has been created by checking to see if on the UserScreen
		try {
			if(elementGetter.getCreateUserSubmitButton().isDisplayed()) {
				// if User form present it means the web site has not accepted the details
				test.log(LogStatus.FAIL, "Details supplied are incorrect or the user already exists");
				fail();
			}
		}
		// if no such element exists it means that the create user form has been successfully submitted
		catch(NoSuchElementException e1) {
			if (elementGetter.getUsersTable().getText().contains(username)) {
				test.log(LogStatus.PASS, "Succesfully created a new user using given details");
			}
			else {
				test.log(LogStatus.FAIL, "Account has not been succesfully created");
				fail();
			}
			assertTrue(elementGetter.getUsersTable().getText().contains(username));
		}	
	}
	
	@After
	public void teardown() {
		extent.endTest(test);
		extent.flush();
		extent.close();
		
		driver.quit();
	}
	
}
