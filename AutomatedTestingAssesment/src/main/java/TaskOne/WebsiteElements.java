package TaskOne;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WebsiteElements {
	
	@FindBy(name = "j_username")
	WebElement initialSignInBox;
	@FindBy(name = "j_password")
	WebElement initialPasswordBox;
	
	public void signIn() {
		initialSignInBox.sendKeys("admin");
		initialPasswordBox.sendKeys("9024c14df8ab43f8872660d374271489");
		// sending return submits the form
		initialPasswordBox.sendKeys(Keys.RETURN);
	}
	

	@FindBy(xpath = "//*[@id=\"tasks\"]/div[4]/a[2]")
	WebElement manageJenkinsButton;
	
	@FindBy(xpath = "//*[@id=\"main-panel\"]/div[16]/a")
	WebElement manageUsersButton;
	
	@FindBy(xpath = "//*[@id=\"tasks\"]/div[3]/a[2]")
	WebElement createUsersButton;
	
	@FindBy(name = "username")
	WebElement usernameBox;
	@FindBy(name = "password1")
	WebElement passwordBox;
	@FindBy(name = "password2")
	WebElement passwordConfirmBox;
	@FindBy(name = "fullname")
	WebElement fullNameBox;
	@FindBy(name = "email")
	WebElement emailBox;
	@FindBy(id =  "yui-gen1-button")
	WebElement createUserSubmitButton;
	
	public WebElement getManageJenkinsButton() {
		return manageJenkinsButton;
	}
	public WebElement getManageUsersButton() {
		return manageUsersButton;
	}
	public WebElement getCreateUsersButton() {
		return createUsersButton;
	}
	
	public void setValuesforCreateUser(String username, String password, String fullname, String email) {
		usernameBox.sendKeys(username);
		passwordBox.sendKeys(password);
		passwordConfirmBox.sendKeys(password);
		fullNameBox.sendKeys(fullname);
		emailBox.sendKeys(email);

	}
	public void submitCreateUserForm() {
		createUserSubmitButton.click();
	}
	public WebElement getCreateUserSubmitButton() {
		return createUserSubmitButton;
	}
	
	
	@FindBy(xpath = "//*[@id=\"main-panel\"]/form")
	WebElement createUserForm;
	
	public WebElement getCreateUserForm() {
		return createUserForm;
	}
	
	@FindBy(id = "people")
	WebElement usersTable;
	
	public WebElement getUsersTable() {
		return usersTable;
	}
	
	@FindBy(xpath = "//*[@id=\"main-panel\"]/div[2]")
	WebElement userID;
	
	public WebElement getuserID() {
		return userID;
	}
	
	@FindBy(linkText = "Configure")
	WebElement userProfileConfigureButton;
	
	public WebElement getuserProfileConfigurenButton() {
		return userProfileConfigureButton;
	}
	
	@FindBy(name = "_.fullName")
	WebElement fullnameTextBox;
	
	public WebElement getfullnameTextBox() {
		return fullnameTextBox;
	}
	
	@FindBy(id = "yui-gen2-button")
	WebElement saveUserProfileChangesbutton;
	
	public WebElement getSaveUserProfileChangesButton () {
		return saveUserProfileChangesbutton;
	}
	
	@FindBy(xpath = "//*[@id=\"main-panel\"]/h1")
	WebElement userFullName;
	
	public WebElement getuserFullName() {
		return userFullName;
	}
	
	
}
