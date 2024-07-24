package practice.contacttest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.objectrepositoryutility.ContactInfoPage;
import com.comcast.crm.objectrepositoryutility.ContactsPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewContactPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;
import com.comcast.crm.webdriverutility.JavaUtility;

public class CreateContact extends BaseClass {

	@Test
	public void createContactTest() throws Throwable
	{
		//generate the random number
		JavaUtility jlib=new JavaUtility();
		int ranInt=jlib.getRandomNumber();
		
		//read test script data from Excel file
		
		ExcelUtility elib=new ExcelUtility();
		String lastname=elib.getDataFromExcelFile("contact", 1, 2);
		
		//step 2 : navigate to Contact module
		HomePage hp=new HomePage(driver);
		hp.getContactLink().click();
		
		//step 3 : click on "create Contact" Button
		
		ContactsPage cp=new ContactsPage(driver);
		cp.getCreateContact().click();
		
		//step 5 : Create contact by specifying all the details
		CreatingNewContactPage cncp=new CreatingNewContactPage(driver);
		cncp.getLastname().sendKeys(lastname);
		cncp.getCreateContact().click();
		
		ContactInfoPage cip=new ContactInfoPage(driver);
		cip.verifyContact(lastname);

	}

}
