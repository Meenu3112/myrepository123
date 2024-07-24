package VTiger_TestCases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
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
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.webdriverutility.JavaUtility;
import com.mysql.cj.x.protobuf.MysqlxExpect.Open.Condition.Key;

public class CreateContactWithOrgTest extends BaseClass{

	
	@Test
	public void createContactTest() throws Throwable
	{
		//generate the random number
		JavaUtility jlib=new JavaUtility();
		int ranInt=jlib.getRandomNumber();
		
		//read test script data from Excel file
		
		ExcelUtility elib=new ExcelUtility();
		String lastname=elib.getDataFromExcelFile("contact", 1, 2);
		String orgname = elib.getDataFromExcelFile("contact", 7, 2)+ranInt;
		//step 2 : navigate to Contact module
		HomePage hp=new HomePage(driver);
		hp.getContactLink().click();
		
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		//step : 6 Creating an Org
		driver.findElement(By.name("accountname")).sendKeys(orgname);
		driver.findElement(By.xpath("(//input[@name='button' and @accesskey='S'])[1]")).click();
		
		
		//step : 7 verifying the Org
		String actheader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (actheader.contains(orgname)) {
			System.out.println(orgname+" header info is verified===PASS");
		}
		else
		{
			System.out.println(orgname+" header info is not verified===FAIL");
		}
		
		//step 3 : click on "create Contact" Button
		
		ContactsPage cp=new ContactsPage(driver);
		cp.getCreateContact().click();
		
		//step 5 : Create contact by specifying all the details
		CreatingNewContactPage cncp=new CreatingNewContactPage(driver);
		cncp.getLastname().sendKeys(lastname);
		cncp.getCreateContact().click();
		
		ContactInfoPage cip=new ContactInfoPage(driver);
		cip.verifyContact(lastname);

		
		String parentwin = driver.getWindowHandle();
		//step 3 : click on "create Organization" Button
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		//step 4 : enter all the details and create new Organization
		driver.findElement(By.name("accountname")).sendKeys(orgname);
		driver.findElement(By.xpath("(//input[@name='button' and @accesskey='S'])[1]")).click();
		
		
		driver.navigate().to("http://localhost:8888/index.php?action=DetailView&module=Accounts&parenttab=Marketing&record=19&viewname=0&start=");
		Thread.sleep(1000);
		//step 5 : navigate to Contacts module
		driver.findElement(By.linkText("Contacts")).click();
		
		//step 6 : click on "create Contact" Button
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
				
				
				
				//step 4 : enter all the details and create new Organization
				driver.findElement(By.name("lastname")).sendKeys(lastname);
				driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
				Set<String> winHandles = driver.getWindowHandles();
				for (String childWin : winHandles) {
					driver.switchTo().window(childWin);
					String winTitle = driver.getTitle();
					System.out.println(winTitle);
				}
				driver.navigate().to("http://localhost:8888/index.php?module=Accounts&action=Popup&popuptype=specific_contact_account_address&form=TasksEditView&form_submit=false&fromlink=&recordid=");
				String newOrg = orgname;
				WebElement ele = driver.findElement(By.id("search_txt"));
				ele.sendKeys(newOrg);
				ele.submit();
				driver.findElement(By.linkText(newOrg)).click();
				Thread.sleep(2000);
				
				//driver.findElement(By.xpath("(//input[@name='button' and @accesskey='S'])[1]")).click();
				
		
		
		
		//step 5 : Verify OrgName expected result
		driver.navigate().to("http://localhost:8888/index.php?module=Contacts&action=EditView&return_action=DetailView&parenttab=Marketing");
		String actOrgName = driver.findElement(By.name("account_name")).getText();
		if (actOrgName.equals(orgname)) {
			System.out.println(orgname+" is verified===PASS");
		}
		else {
			System.out.println(orgname+" is not verified===FAIL");
		}
		//step 6 : Logout
		driver.quit();
	}

}
