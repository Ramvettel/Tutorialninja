package com.tutorialninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	public WebDriver driver;
	
	public LoginPage(WebDriver driver){
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(id = "input-email")
	private WebElement email;
	
	@FindBy(id = "input-password")
	private WebElement password;
	
	@FindBy(xpath = "//input[@value='Login']")
	private WebElement loginBtn;
	
	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement invalidLoginWarningMsg;
	
	
	public AccountPage validUserLogin(String validEmail, String validPassword) {
		
		email.sendKeys(validEmail);
		password.sendKeys(validPassword);
		loginBtn.click();
		return new AccountPage(driver);
	}
	
	public String invalidLoginWarningMsg() {
		
		return invalidLoginWarningMsg.getText();
	}
	
}
