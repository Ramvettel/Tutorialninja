package com.tutorialninja.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.tutorialninja.qa.utilities.Utilities;

public class Base {
	
	public WebDriver driver;
	public static Properties prop;
	Properties dataProp;
	
	public static Properties loadPropertiesFile() {
		
		
		prop = new Properties();
		File f = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialninja\\qa\\config\\Config.properties");
		FileInputStream fis;
		try {
			fis = new FileInputStream(f);
			prop.load(fis);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		return prop;
		
	}
	
	public Properties loadDataPropertiesFile() {
		
		
		dataProp = new Properties();
		File f = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialninja\\qa\\testdata\\TestData.properties");
		FileInputStream fis;
		try {
			fis = new FileInputStream(f);
			dataProp.load(fis);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		return dataProp;
		
	}
	
	
	public WebDriver initializeBrowserAndNavigateToUrl(String browserName) {
		
		if (browserName.equalsIgnoreCase("chrome")) {
			
			driver = new ChromeDriver();
			
		}else if (browserName.equalsIgnoreCase("edge")) {
			
			driver = new EdgeDriver();
			
		}else if (browserName.equalsIgnoreCase("firefox")) {
			
			driver = new FirefoxDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICITY_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGELOAD_WAIT_TIME));
		driver.manage().deleteAllCookies();
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		
		return driver;
	}

}
