package com.comcast.crm.listenerutility;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.webdriverutility.JavaUtility;
import com.comcast.crm.webdriverutility.UtilityClassObject;

public class ListenerImp implements ITestListener,ISuiteListener {
	
	 public ExtentReports report;
	 public static ExtentTest test;
	 JavaUtility jlib=new JavaUtility();
	
	@Override
	public void onStart(ISuite suite) {
		ISuiteListener.super.onStart(suite);
		System.out.println("Report Configuration");
		String time = jlib.getCurrentTime();
		ExtentSparkReporter spark=new ExtentSparkReporter("./AdvanceReports/report_"+time+".html");
		spark.config().setDocumentTitle("CRM Test Suite Results");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);
		report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows-10");
		report.setSystemInfo("BROWSER","Chrome-100");
	}

	@Override
	public void onFinish(ISuite suite) {
		ISuiteListener.super.onFinish(suite);
		System.out.println("Report Backup");
		report.flush();
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ITestListener.super.onTestSuccess(result);
		String methodname = result.getMethod().getMethodName();
		System.out.println("-------"+methodname+"------Completed-------");
		test.log(Status.INFO, methodname+"--------Completed-------");
	
	}

	public void onTestStart(ITestResult result) {
		String methodname = result.getMethod().getMethodName();
		System.out.println("-------"+methodname+"------START-------");
		test=report.createTest(methodname);
		UtilityClassObject.setTest(test);
		test.log(Status.INFO, methodname+"--------Started--------");
	}
	
	public void onTestFailure(ITestResult result) {
		String testname = result.getMethod().getMethodName();
		TakesScreenshot eDriver=(TakesScreenshot)BaseClass.sdriver;
		String filepath = eDriver.getScreenshotAs(OutputType.BASE64);
		String time = jlib.getCurrentTime();
		test.addScreenCaptureFromBase64String(filepath,testname+"_"+time);
		test.log(Status.FAIL, testname+"-------Failed---------");
		
		
	}
}
