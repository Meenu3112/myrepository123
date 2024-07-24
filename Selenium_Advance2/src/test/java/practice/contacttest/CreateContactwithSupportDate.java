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
import com.comcast.crm.webdriverutility.JavaUtility;

public class CreateContactwithSupportDate extends BaseClass {
	
	@Test
	public  void createContactWithSupportDateTest() throws Throwable {
		
		//step 2 : generate random number
		JavaUtility jlib=new JavaUtility();
		
		//step 3 : read data from excel file
		ExcelUtility elib=new ExcelUtility();
		String lastname = elib.getDataFromExcelFile("contact", 1, 2)+jlib.getRandomNumber();
		String days = elib.getDataFromExcelFile("contact", 1, 3);
		//days.replace('-', ' ');
		int days1=Integer.parseInt(days);
		//Fetching the Current Date
		String startDate = jlib.getSystemDateYYYYMMDD();
		String endDate = jlib.getRequiredDate(days1);
		
		HomePage hp=new HomePage(driver);
		hp.getContactLink().click();
		
		ContactsPage cp=new ContactsPage(driver);
		cp.getCreateContact().click();
		//step 5 : Create contact by specifying all the details
		CreatingNewContactPage cncp=new CreatingNewContactPage(driver);
		cncp.createContactWithDate(lastname, startDate, endDate);
		
		//Step 6 : Verify contact date
		
		ContactInfoPage cip=new ContactInfoPage(driver);
		cip.verifyContactStartDate(startDate);
		cip.verifyContactEndDate(days1);
		
	}

}
