package com.tutorialninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	public WebDriver driver;
	
	public HomePage(WebDriver driver){
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(linkText = "My Account")
	private WebElement myAccount;
	
	@FindBy(linkText = "Login")
	private WebElement loginOption;
	
	public LoginPage selectLoginOption() {
		myAccount.click();
		loginOption.click();
		return new LoginPage(driver);
	}

}
