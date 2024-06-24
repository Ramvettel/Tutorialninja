package com.tutorialninja.qa.utilities;

import java.io.File;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.tutorialninja.qa.base.Base;

public class ExtentReporter {
	
	public static Properties prop = Base.loadPropertiesFile();
	
	public static ExtentReports generateExtentReport() {
		
		ExtentReports extentReport = new ExtentReports();
		
		File extentReportFile = new File(System.getProperty("user.dir")+"//test-output//ExtentReports//ExtentReport.html");
		
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
		
		sparkReporter.config().setTheme(Theme.STANDARD);
		sparkReporter.config().setReportName("TutorialsNinja Test Report");
		sparkReporter.config().setDocumentTitle("Automation Report");
		sparkReporter.config().setTimeStampFormat("DD/mm/YYYY hh:mm:ss");
		
		extentReport.attachReporter(sparkReporter);
		
		extentReport.setSystemInfo("User", System.getProperty("user.name"));
		extentReport.setSystemInfo("OperatingSystem", System.getProperty("os.name"));
		extentReport.setSystemInfo("OS Version", System.getProperty("os.version"));
		extentReport.setSystemInfo("WebBrowser",prop.getProperty("browsername"));
		extentReport.setSystemInfo("Application", prop.getProperty("url"));
		extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));
		extentReport.setSystemInfo("Tested By", "Ram");
		
		return extentReport;
	}
	

}
