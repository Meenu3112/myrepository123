package practice.contacttest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

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
import com.comcast.crm.objectrepositoryutility.CreatingNewOrgPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;
import com.comcast.crm.webdriverutility.JavaUtility;
import com.comcast.crm.webdriverutility.WebDriverUtility;

public class Createconwithorg extends BaseClass{
	
	@Test
	public void createContactWithOrgTest() throws Throwable {
		
		//step : 1 generating random number
		
		JavaUtility jlib=new JavaUtility();
		
		//step : 2 fetching data from Excel file
		
		ExcelUtility elib=new ExcelUtility();
		String lastname = elib.getDataFromExcelFile("contact", 7, 3)+jlib.getRandomNumber();
		String orgname = elib.getDataFromExcelFile("contact", 7, 2)+jlib.getRandomNumber();
		
		//step : 3 Navigate to Org Link
		
		HomePage hp=new HomePage(driver);
		hp.getOrgLink().click();
		OrganizationsPage op=new OrganizationsPage(driver);
		op.getCreateOrg().click();
		
		//step : 6 Creating an Org
		CreatingNewOrgPage cnop=new CreatingNewOrgPage(driver);
		cnop.createOrg(orgname);
		
		//step : 7 verifying the Org
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		oip.verifyOrg(orgname);

		//step : 8 Creating a contact

		hp.getContactLink().click();
		ContactsPage cp=new ContactsPage(driver);
		cp.getCreateContact().click();
		
		CreatingNewContactPage cncp=new CreatingNewContactPage(driver);
		cncp.getLastname().sendKeys(lastname);
		cncp.getOrgname().click();
		WebDriverUtility wlib=new WebDriverUtility();
		wlib.switchToNewTabOnURL(driver,"module=Accounts&action");
		
		WebElement ele = op.getSearchbox();
		ele.sendKeys(orgname);
		ele.submit();
		driver.findElement(By.xpath("//a[text()='"+orgname+"']")).click();
		Thread.sleep(2000);
		wlib.switchToNewTabOnURL(driver, "module=Contacts&action");
		cncp.getCreateContact().click();
		ContactInfoPage cip=new ContactInfoPage(driver);
		cip.verifyContact(lastname);
		cip.verifyContactOrg(orgname);

	}

}
