package com.tutorialninja.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialninja.qa.utilities.ExtentReporter;
import com.tutorialninja.qa.utilities.Utilities;

public class Mylisteners implements ITestListener{
	
	ExtentReports extentreport;
	ExtentTest extentTest;
	
	@Override
	public void onStart(ITestContext context) {
		
		extentreport = ExtentReporter.generateExtentReport();
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		System.out.println(result.getName());
		extentTest = extentreport.createTest(result.getName());
		extentTest.log(Status.INFO, result.getName()+"got start Executing");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		extentTest.log(Status.PASS, result.getName()+"got Passed");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		WebDriver driver = null;
		
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String scrPath = Utilities.captureScreenShot(driver, result.getName());
		extentTest.addScreenCaptureFromPath(scrPath);
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, result.getName()+"got failed");
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, result.getName()+"got Skipped");
	}

	@Override
	public void onFinish(ITestContext context) {
		
		extentreport.flush();
		
		String reportPath = System.getProperty("user.dir")+"\\test-output\\ExtentReports\\ExtentReport.html";
		File eReport = new File(reportPath);
		try {
			Desktop.getDesktop().browse(eReport.toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
