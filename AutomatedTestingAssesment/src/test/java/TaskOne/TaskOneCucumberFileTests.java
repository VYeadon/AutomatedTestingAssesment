package TaskOne;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TaskOneCucumberFileTests {
	
public WebDriver driver = null;
	
	public ExtentReports extent;
	public ExtentTest test;
	
	WebsiteElements elementGetter;
	ExcelElements excelSetter;
	
	String username = "newuser";
	String password = "password";
	String fullname = "New User";
	String email = "user@new.com";
	
	String message;
	
	public int i = 1;
	
	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", 
				"C:\\Users\\Admin\\Documents\\TestingJava\\chromedriver.exe\\");
		
		driver = new ChromeDriver();

		extent = new ExtentReports("C:\\Users\\Admin\\Documents\\TestingJava\\AssesmentTaskOneCucumber_scenario4.html",true);
		test = extent.startTest("Automated Testing Assesment");
		
		elementGetter = PageFactory.initElements(driver, WebsiteElements.class);
		
		excelSetter = PageFactory.initElements(driver, ExcelElements.class);
		excelSetter.openFile();
	}
	
	@Given("^that you are on the create UserScreen$")
	public void that_you_are_on_the_create_UserScreen() {
		message = "First Sceario started: Add user to database";
		test.log(LogStatus.INFO, message);
		excelSetter.setLogValue(i++, 1, message);
		
		driver.get(Constants.websiteURL);
		
		message = "Web page loaded";
		test.log(LogStatus.INFO, message);
		excelSetter.setLogValue(i++, 1, message);
		
		elementGetter.signIn();
		
		message = "Admin has logged onto the webpage";
		test.log(LogStatus.INFO, message);
		excelSetter.setLogValue(i++, 1, message);

		
		elementGetter.getManageJenkinsButton().click();
		elementGetter.getManageUsersButton().click();
		elementGetter.getCreateUsersButton().click();
		
		try {
			elementGetter.getCreateUserForm();
			message = "Succesfully navigated to the sign up page";
			test.log(LogStatus.PASS, message);
			excelSetter.setLogValue(i++, 1, message);

		}
		catch (NoSuchElementException e) {
			message = "test has not been able to succesfully navigate to the create user page";
			test.log(LogStatus.FAIL, message);
			excelSetter.setLogValue(i++, 1, message);
			fail();
		}
	}

	@When("^the User details are entered on the Create UserScreen$")
	public void the_User_details_are_entered_on_the_Create_UserScreen() {
		elementGetter.setValuesforCreateUser(username, password, fullname, email);
		message = "User details have been entered on screen";
		test.log(LogStatus.PASS, message);
		excelSetter.setLogValue(i++, 1, message);
		excelSetter.setInputValue(i, 1, "username, password, fullname, email");

	}

	@When("^the details are submitted on the Create UserScreen$")
	public void the_details_are_submitted_on_the_Create_UserScreen() throws Throwable {
	    elementGetter.submitCreateUserForm();
	    message = "User details have been submitted";
	    test.log(LogStatus.PASS, message);
		excelSetter.setLogValue(i++, 1, message);

	}

	@Then("^the Username should be visible on the UsersScreen$")
	public void the_Username_should_be_visible_on_the_UsersScreen() {
		try {
			if(elementGetter.getCreateUserSubmitButton().isDisplayed()) {
				// if User form present it means the web site has not accepted the details
				message = "Details supplied are incorrect or the user already exists";
				test.log(LogStatus.FAIL, message);
				excelSetter.setLogValue(i++, 1, message);

				fail();
			}
		}
		// if no such element exists it means that the create user form has been successfully submitted
		catch(NoSuchElementException e1) {
			if (elementGetter.getUsersTable().getText().contains(username)) {
				message = "Succesfully created a new user and said user is visible on userscreen";
				test.log(LogStatus.PASS, message);
				excelSetter.setLogValue(i++, 1, message);
				excelSetter.setOutputValue(i, 1, username);

			}
			else {
				message = "Account has not been succesfully created";
				test.log(LogStatus.FAIL, message);
				excelSetter.setLogValue(i++, 1, message);

				fail();
			}
			assertTrue(elementGetter.getUsersTable().getText().contains(username));
		}	
	}

	@When("^the User details \"([^\"]*)\" username, \"([^\"]*)\" password, \"([^\"]*)\" confirm Password, and \"([^\"]*)\" Fullname are entered on the Create UserScreen$")
	public void the_User_details_username_password_confirm_Password_and_Fullname_are_entered_on_the_Create_UserScreen(String arg1, String arg2, String arg3, String arg4) throws Throwable {
		elementGetter.setValuesforCreateUser(arg1, arg2, arg3, email);
		message = "User details given in feature sheat have been entered on screen";
		test.log(LogStatus.INFO, message);
		excelSetter.setLogValue(i++, 1, message);
		excelSetter.setLogValue(i, 1, (arg1 + arg2 + arg3 + email));


	}

	@Then("^the \"([^\"]*)\" username should be visible on the UsersScreen$")
	public void the_username_should_be_visible_on_the_UsersScreen(String arg1) throws Throwable {
			try {
				WebElement user = elementGetter.getUsersTable().findElement(By.linkText(arg1));
				
				if (user.toString().contains(arg1)) {
					message = "Succesfully created a new user and said user is visible on userscreen";
					test.log(LogStatus.PASS, message);
					excelSetter.setLogValue(i++, 1, message);
					excelSetter.setOutputValue(i, 1, arg1);

				}
				else {
					message = "Account is not visible on the UserScreen";
					test.log(LogStatus.FAIL, message);
					excelSetter.setLogValue(i++, 1, message);

				}
				assertTrue(user.toString().contains(arg1));
			} 
			catch (NoSuchElementException e7) {
				
			}
	}

	@Given("^the \"([^\"]*)\" username is visible on the UsersScreen$")
	public void the_username_is_visible_on_the_UsersScreen(String arg1) throws Throwable {
		message = "Third scenario started: view the details of a user on the database";
		test.log(LogStatus.INFO, message);
		excelSetter.setLogValue(i++, 2, message);

		
		driver.get(Constants.websiteURL);
		
		message = "Web page loaded";
		test.log(LogStatus.INFO, message);
		excelSetter.setLogValue(i++, 2, message);

		
		elementGetter.signIn();
		
		message = "Admin has logged onto the webpage";
		test.log(LogStatus.INFO, message);
		excelSetter.setLogValue(i++, 2, message);

		
		elementGetter.getManageJenkinsButton().click();
		elementGetter.getManageUsersButton().click();
		
		try {
		    driver.findElement(By.linkText(arg1));
		    message = "Username: " + arg1 + " is visible on UserScreen ";
		    test.log(LogStatus.PASS, message);
			excelSetter.setLogValue(i++, 2, message);
			excelSetter.setOutputValue(i, 2, arg1);

		}
		catch (NoSuchElementException e2) {
			message = "Username: " + arg1 + " is not visible on UserScreen ";
			test.log(LogStatus.FAIL, message);
			excelSetter.setLogValue(i++, 2, message);

			fail();
		}
	}

	@When("^the \"([^\"]*)\" username is clicked on the UserScreen$")
	public void the_username_is_clicked_on_the_UserScreen(String arg1) {
	    try {
	    	driver.findElement(By.linkText(arg1)).click();
	    }
	    catch(NoSuchElementException e8) {
	    	message = "No username link to click has been found";
	    	test.log(LogStatus.FAIL, message);
			excelSetter.setLogValue(i++, 2, message);

	    }
	}

	@Then("^the User Profile should display the \"([^\"]*)\" username on the ProfileScreen$")
	public void the_User_Profile_should_display_the_username_on_the_ProfileScreen(String arg1) {
	    if (elementGetter.getuserID().getText().contains(arg1)) {
	    	message = "User profile displays the correct username";
	    	test.log(LogStatus.PASS, message);
			excelSetter.setLogValue(i++, 2, message);
			excelSetter.setOutputValue(i, 2, arg1);

	    }
	    else {
	    	message = "User profile doesn't displays the correct username";
	    	test.log(LogStatus.FAIL, message);
			excelSetter.setLogValue(i++, 1, message);

	    }
		assertTrue(elementGetter.getuserID().getText().contains(arg1));
	}

	@Given("^the \"([^\"]*)\" Username's profile page has been loaded$")
	public void the_Username_s_profile_page_has_been_loaded(String arg1) {
		message = "Fourth scenario started: Updating full name of user";
		test.log(LogStatus.INFO, message);
		excelSetter.setLogValue(i++, 3, message);

		
		driver.get(Constants.websiteURL);
		
		message = "Web page loaded";
		test.log(LogStatus.INFO, message);
		excelSetter.setLogValue(i++, 3, message);

		
		elementGetter.signIn();
		
		message = "Admin has logged onto the webpage";
		test.log(LogStatus.INFO, message);
		excelSetter.setLogValue(i++, 3, message);

		
		elementGetter.getManageJenkinsButton().click();
		elementGetter.getManageUsersButton().click();
		
		try {
		    driver.findElement(By.linkText(arg1));
		    message = "Username: " + arg1 + " is visible on UserScreen ";
		    test.log(LogStatus.INFO, message);
			excelSetter.setLogValue(i++, 3, message);
			excelSetter.setOutputValue(i, 3, arg1);
		    
		    driver.findElement(By.linkText(arg1)).click();
		    message = "Succesfully navigated to specified user profile";
		    test.log(LogStatus.PASS, message);
			excelSetter.setLogValue(i++, 3, message);

		}
		catch (NoSuchElementException e2) {
			message = "Username: " + arg1 + " is not visible on UserScreen ";
			test.log(LogStatus.FAIL, message);
			excelSetter.setLogValue(i++, 3, message);
			fail();
		}
	}

	@Given("^the configure button has been clicked on the profile page$")
	public void the_configure_button_has_been_clicked_on_the_profile_page() {
		try {
			elementGetter.getuserProfileConfigurenButton().click();
			message = "Configure button has been clicked on";
	    	test.log(LogStatus.PASS, message);	
			excelSetter.setLogValue(i++, 3, message);

		}
		catch(NoSuchElementException e9) {
			message =  "No configure button found";
			test.log(LogStatus.FAIL,message);
			excelSetter.setLogValue(i++, 3, message);

			fail();
		}
	}

	@When("^I change the old FullName on the Configure Page to a new FullName \"([^\"]*)\"$")
	public void i_change_the_old_FullName_on_the_Configure_Page_to_a_new_FullName(String arg1) {
		try {
			  elementGetter.getfullnameTextBox().clear();;
			  elementGetter.getfullnameTextBox().sendKeys(arg1);
			  
			  message = "New name: " + arg1 + " for user has been enetered";
			  test.log(LogStatus.PASS, message);
			  excelSetter.setLogValue(i++, 3, message);
			  excelSetter.setInputValue(i, 3, arg1);

			  
			  //assertTrue(elementGetter.getfullnameTextBox().getText().equals(arg1));
		}
		catch (NoSuchElementException e4){
			message = "No username box found to change users name";
			test.log(LogStatus.FAIL, message);
			excelSetter.setLogValue(i++, 3, message);

			fail();
		}
	}

	@When("^I save the changes to the Configure Page$")
	public void i_save_the_changes_to_the_Configure_Page() {
		try {
			elementGetter.saveUserProfileChangesbutton.click();
			 
			message = "New name for user has been submitted";
			test.log(LogStatus.PASS, message); 
			excelSetter.setLogValue(i++, 3, message);

		}
		catch (NoSuchElementException e4){
			message = "No save changes button found";
			test.log(LogStatus.FAIL, message);
			excelSetter.setLogValue(i++, 3, message);

			fail();
		}
	    
	}

	@Then("^the Configure Page should show the NewFullName \"([^\"]*)\"$")
	public void the_Configure_Page_should_show_the_NewFullName(String arg1) {
		try {
			elementGetter.getuserFullName().getText().contains(arg1); 
			message = "User profile displays the correct username";
			test.log(LogStatus.PASS, message);
			excelSetter.setLogValue(i++, 3, message);
			excelSetter.setOutputValue(i, 3, arg1);
	    }
	    catch (NoSuchElementException e10) {
	    	message = "User profile doesn't displays the correct username";
	    	test.log(LogStatus.FAIL, message);
			excelSetter.setLogValue(i++, 3, message);

	    }
		assertTrue(elementGetter.getuserFullName().getText().contains(arg1));
	}
	
	@After
	public void teardown() {
		extent.endTest(test);
		extent.flush();
		extent.close();
		
		driver.quit();
	}

}
