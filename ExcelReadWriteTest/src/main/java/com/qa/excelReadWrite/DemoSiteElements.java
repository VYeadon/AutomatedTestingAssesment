package com.qa.excelReadWrite;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DemoSiteElements {
	
	@FindBy(xpath = "/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[3]")
	WebElement addUserPageButton;
	
	@FindBy(xpath = "/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[4]")
	WebElement loginPageButton;

	@FindBy(name = "username")
	WebElement usernameBox;
	
	@FindBy(name = "password")
	WebElement passwordBox;
	
	@FindBy(name = "FormsButton2")
	WebElement submitButton;
	
	@FindBy(xpath = "/html/body/table/tbody/tr/td[1]/big/blockquote/blockquote/font/center/b")
	WebElement verificationText;
	
	public void clickAddUserPage() {
		addUserPageButton.click();
	}
	public void clickLoginPage() {
		loginPageButton.click();
	}
	public void clickSubmit() {
		submitButton.click();
	}
	
	public WebElement getusernameBox() {
		return usernameBox;
	}
	public WebElement getpasswordBox() {
		return passwordBox;
	}
	public String getverificationtext() {
		return verificationText.getText();
	}
}
