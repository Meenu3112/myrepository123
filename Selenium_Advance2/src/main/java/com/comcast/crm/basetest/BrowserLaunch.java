package com.comcast.crm.basetest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BrowserLaunch {
	@Test
	public void createContactTest() {
		
		//Spark report config
		
		ExtentSparkReporter spark=new ExtentSparkReporter("./AdvanceReports/report.html");
		spark.config().setDocumentTitle("CRM Test Suite Results");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);
		
		//Add Env. Information & Create Test
		
		ExtentReports report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows-10");
		report.setSystemInfo("BROWSER", "Chrome-100");
		ExtentTest test = report.createTest("CreateContactTest");
		
		test.log(Status.INFO,"Login to app");
		test.log(Status.INFO,"navigate to contact Page");
		test.log(Status.INFO,"Create Contact");
		if ("HDFC".equals("HDFC")) {
			test.log(Status.PASS,"Contact is created");
		}
		else {
		test.log(Status.FAIL,"Contact is not created");
		}
		report.flush();
}
}