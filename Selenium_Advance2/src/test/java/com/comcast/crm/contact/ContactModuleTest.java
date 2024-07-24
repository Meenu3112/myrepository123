package com.comcast.crm.contact;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.objectrepositoryutility.ContactInfoPage;
import com.comcast.crm.objectrepositoryutility.ContactsPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrgPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;
import com.comcast.crm.webdriverutility.JavaUtility;
import com.comcast.crm.webdriverutility.UtilityClassObject;
import com.comcast.crm.webdriverutility.WebDriverUtility;
/**
 * @author Meenu
 * Contains 3 test cases
 * 1. create contact
 * 2. create contact with support date
 * 3. create contact with org
 */
public class ContactModuleTest extends BaseClass {
	/**
	 * Scenario : login()===> navigate to contact===> createcontact===> verify
	 * @throws Throwable
	 */
	@Test(groups = "SmokeTest")
	public void createContactTest() throws Throwable
	{
		UtilityClassObject.getTest().log(Status.INFO,"read data from Excel");
		 /* read test script data from Excel file */
		
		String lastname=elib.getDataFromExcelFile("contact", 1, 2);
		
		/* step 2 : navigate to Contact module */
		UtilityClassObject.getTest().log(Status.INFO,"navigate to Contact Page");
		HomePage hp=new HomePage(driver);
		hp.getContactLink().click();
		
		/* step 3 : click on "create Contact" Button */
		UtilityClassObject.getTest().log(Status.INFO,"navigate to create ContactPage");
		ContactsPage cp=new ContactsPage(driver);
		cp.getCreateContact().click();
		
		/* step 5 : Create contact by specifying all the details */
		UtilityClassObject.getTest().log(Status.INFO,"Contact created");
		CreatingNewContactPage cncp=new CreatingNewContactPage(driver);
		cncp.getLastname().sendKeys(lastname);
		cncp.getCreateContact().click();
		
		UtilityClassObject.getTest().log(Status.INFO,"Contact created");
		/* verify     */
		ContactInfoPage cip=new ContactInfoPage(driver);
		cip.verifyContact(lastname);

	}
	
	
	@Test(groups = "RegressionTest")
	
	public  void createContactWithSupportDateTest() throws Throwable {
		
		/* step 1 : generate random number */
		JavaUtility jlib=new JavaUtility();
		
		/* step 2 : read data from excel file */
		UtilityClassObject.getTest().log(Status.INFO,"read data from Excel");
		ExcelUtility elib=new ExcelUtility();
		String lastname = elib.getDataFromExcelFile("contact", 1, 2)+jlib.getRandomNumber();
		String days = elib.getDataFromExcelFile("contact", 1, 3);
		/* days.replace('-', ' '); */
		int days1=Integer.parseInt(days);
		/* step 3 : Fetching the Current Date */
		String startDate = jlib.getSystemDateYYYYMMDD();
		String endDate = jlib.getRequiredDate(days1);
		
		/* step 4 : navigate to Contact Page */
		UtilityClassObject.getTest().log(Status.INFO,"navigate to Contact Page");
		HomePage hp=new HomePage(driver);
		hp.getContactLink().click();
		
		/* step 5 : click on "create Contact" Button */
		UtilityClassObject.getTest().log(Status.INFO,"navigate to create ContactPage");
		ContactsPage cp=new ContactsPage(driver);
		cp.getCreateContact().click();
		/* step 5 : Create contact by specifying all the details */
		UtilityClassObject.getTest().log(Status.INFO,"create contact with support date");
		CreatingNewContactPage cncp=new CreatingNewContactPage(driver);
		cncp.createContactWithDate(lastname, startDate, endDate);
		
		/* Step 6 : Verify contact date */
		
		ContactInfoPage cip=new ContactInfoPage(driver);
		String actStartDate = cip.getStartDateInfo().getText();
		boolean status = actStartDate.trim().equals(jlib.getSystemDateYYYYMMDD());
		Assert.assertEquals(status,true); 
		 
	}
	@Test(groups="RegressionTest")
	public void createContactWithOrgTest() throws Throwable {
		
		/* step : 1 generating random number */
		
		JavaUtility jlib=new JavaUtility();
		
		/* step : 2 fetching data from Excel file */
		
		ExcelUtility elib=new ExcelUtility();
		String lastname = elib.getDataFromExcelFile("contact", 7, 3)+jlib.getRandomNumber();
		String orgname = elib.getDataFromExcelFile("contact", 7, 2)+jlib.getRandomNumber();
		
		/* step : 3 Navigate to Org Link */
		
		HomePage hp=new HomePage(driver);
		hp.getOrgLink().click();
		OrganizationsPage op=new OrganizationsPage(driver);
		op.getCreateOrg().click();
		
		/* step : 6 Creating an Org */
		CreatingNewOrgPage cnop=new CreatingNewOrgPage(driver);
		cnop.createOrg(orgname);
		
		/* step : 7 verifying the Org */
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		oip.verifyOrg(orgname);

		/* step : 8 Creating a contact */
		//Thread.sleep(1000);
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
		//Thread.sleep(2000);
		wlib.switchToNewTabOnURL(driver, "module=Contacts&action");
		cncp.getCreateContact().click();
		/* Verify the created contact */
		ContactInfoPage cip=new ContactInfoPage(driver);
		cip.verifyContact(lastname);
		cip.verifyContactOrg(orgname);

	}


}
