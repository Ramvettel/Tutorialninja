package com.tutorialninja.qa.testcases;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialninja.qa.base.Base;
import com.tutorialninja.qa.pages.AccountPage;
import com.tutorialninja.qa.pages.HomePage;
import com.tutorialninja.qa.pages.LoginPage;
import com.tutorialninja.qa.utilities.Utilities;

public class LoginTest extends Base{
	
	public WebDriver driver;
	Properties prop;
	Properties dataProp;
	HomePage homePage;
	LoginPage loginPage;
	AccountPage accountPage;
	
	@BeforeMethod
	public void setup() {
		
		prop = loadPropertiesFile();
		dataProp = loadDataPropertiesFile();
		driver = initializeBrowserAndNavigateToUrl(prop.getProperty("browsername"));
		
	}
	
	@AfterMethod
	public void tearDown() {
		
		driver.quit();
	}
	
	@Test(priority=1)
	public void verifyUserAbleToLoginWithValidCredentials() {
		
		homePage = new HomePage(driver);
		loginPage = homePage.selectLoginOption();
		
		//LoginPage loginPage = new LoginPage(driver);
		accountPage = loginPage.validUserLogin(prop.getProperty("validemail"), prop.getProperty("validpassword"));
		Assert.assertTrue(accountPage.myAccountTxtIsDisplayed(),"My Account Txt is not Displayed");
		
	}
	
	@Test(priority=3,dataProvider = "ValidCredentials")
	public void verifyUserAbleToLoginWithMultipleValidCredentials(String email,String password) {
		
		homePage = new HomePage(driver);
		loginPage = homePage.selectLoginOption();
		
		//LoginPage loginPage = new LoginPage(driver);
		accountPage = loginPage.validUserLogin(email, password);
		Assert.assertTrue(accountPage.myAccountTxtIsDisplayed(),"My Account Txt is not Displayed");
		
	}
	
	@DataProvider(name="ValidCredentials")
	public Object[][] validCredentialTestData() {
		
		Object[][] data = Utilities.getTestDataFromExcelSheet("TestData", "ValidCredentials");
		return data;
		
	}
	
	@Test(priority=2, dependsOnMethods = "verifyUserAbleToLoginWithValidCredentials")
	public void verifyUserAbleToLoginWithInvalidCredentials() {
		
		homePage = new HomePage(driver);
		loginPage = homePage.selectLoginOption();
		
		//LoginPage loginPage = new LoginPage(driver);
		accountPage = loginPage.validUserLogin(dataProp.getProperty("invalidemail"), dataProp.getProperty("invalidpassword"));
		Assert.assertTrue(loginPage.invalidLoginWarningMsg().contains(dataProp.getProperty("invalidloginwarningmsg")),"Invalid Login Warning Msg is not Displayed");
		
	}
	
	@Test(priority=4,dataProvider="InvalidCredentials")
	public void verifyUserAbleToLoginWithMultipleInvalidCredentials(String email,String password) {
		
		homePage = new HomePage(driver);
		loginPage = homePage.selectLoginOption();
		
		//LoginPage loginPage = new LoginPage(driver);
		accountPage = loginPage.validUserLogin(email, password);
		Assert.assertTrue(loginPage.invalidLoginWarningMsg().contains(dataProp.getProperty("invalidloginwarningmsg")),"Invalid Login Warning Msg is not Displayed");
		
	}
	
	@DataProvider(name="InvalidCredentials")
	public Object[][] inValidCredentialTestData() {
		
		Object[][] data = Utilities.getTestDataFromExcelSheet("TestData", "InvalidCredentials");
		return data;
		
	}

}
