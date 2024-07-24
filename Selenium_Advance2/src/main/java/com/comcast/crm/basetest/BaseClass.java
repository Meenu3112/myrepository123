package com.comcast.crm.basetest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.comcast.crm.generic.databaseutility.DatabaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.webdriverutility.JavaUtility;
import com.comcast.crm.webdriverutility.UtilityClassObject;
import com.comcast.crm.webdriverutility.WebDriverUtility;

public class BaseClass {
	public DatabaseUtility dlib=new DatabaseUtility();
	public ExcelUtility elib=new ExcelUtility();
	public FileUtility flib=new FileUtility();
	public JavaUtility jlib=new JavaUtility();
	WebDriverUtility wlib=new WebDriverUtility();
	public WebDriver driver;
	public static WebDriver sdriver;
	@BeforeSuite(groups= {"SmokeTest","RegressionTest"})
	public void configBS()
	{
		System.out.println("****DB Connection****");
		dlib.getConnection();
	}
	@AfterSuite(groups= {"SmokeTest","RegressionTest"})
	public void configAS()
	{
		System.out.println("*******Close Connection********");
	}
	
	@BeforeClass
	public void configBC() throws Throwable
	{
		System.out.println("Launch Browser");
		String BROWSER = flib.getDataFromProp("browser");
				
		if (BROWSER.equals("chrome")) {
			driver=new ChromeDriver();
		}
		else if (BROWSER.equals("firefox")) {
			driver=new FirefoxDriver();
		}
		else if (BROWSER.equals("edge")) {
			driver=new EdgeDriver();
		}
		else
		{
			driver=new ChromeDriver();
		}
		sdriver=driver;
		UtilityClassObject.setDriver(driver);
		Assert.assertNotNull(driver, "Browser is launched");
		Reporter.log("Browser is launched",true);
	}
	@AfterClass()
	public void configAC() {
		System.out.println("Close Browser");
		driver.quit();
	}
	@BeforeMethod
	public void configBM() throws Throwable{
		System.out.println("Login to App");
		String URL = flib.getDataFromProp("url");
		wlib.waitForPageToLoad(driver);
		String USERNAME = flib.getDataFromProp("username");
		String PASSWORD = flib.getDataFromProp("password");
		LoginPage lp=new LoginPage(driver);
		lp.loginToApp(URL,USERNAME, PASSWORD);
	}
	@AfterMethod()
	public void configAM()
	{
		System.out.println("Logout from the app");
		HomePage hp=new HomePage(driver);
		hp.logOut();
	}
}
