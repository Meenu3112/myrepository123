package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.comcast.crm.webdriverutility.JavaUtility;
/**
 * @author Meenu
 * contains information about the created contacts
 */
public class ContactInfoPage {

	WebDriver driver;
	JavaUtility jlib=new JavaUtility();
	public ContactInfoPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement contactheader;
	@FindBy(id="dtlview_Support Start Date")
	private WebElement startDate;
	@FindBy(id="dtlview_Support End Date")
	private WebElement endDate;
	@FindBy(name = "account_name")
	private WebElement orgname;
	@FindBy(id="mouseArea_Support Start Date")
	private WebElement startDateInfo;
	public WebElement getStartDateInfo() {
		return startDateInfo;
	}

	public void setStartDateInfo(WebElement startDateInfo) {
		this.startDateInfo = startDateInfo;
	}

	@FindBy(id = "mouseArea_Organization Name")
	private WebElement orgname2;

	public WebElement getOrgname2() {
		return orgname2;
	}

	public WebElement getContactheader() {
		return contactheader;
	}

	public WebElement getStartDate() {
		return startDate;
	}

	public WebElement getEndDate() {
		return endDate;
	}

	public WebElement getOrgname() {
		return orgname;
	}
	/**
	 * verifies if contact is created with the given name
	 * @param name
	 */
	public void verifyContact(String name) {

		String actContactheader = contactheader.getText();
		if (actContactheader.contains(name)) {
			System.out.println(name + " header info is verified===PASS");
		} else {
			System.out.println(name + " header info is not verified===FAIL");
		}
	}
	/**
	 * verifies if the contact is created from a given organization
	 * @param name
	 */
	public void verifyContactOrg(String name)
	{
		String actOrg1 = orgname2.getText();
		boolean status = actOrg1.contains(name);
		Assert.assertTrue(status);
	}
	public void verifyContactStartDate(String date)
	{
			
//			 if(actStartDate.equals(date)) { 
//			  System.out.println(date+" information is verified===PASS"); } else
//			  {  System.out.println(date+" information is not verified===FAIL");
//			  }
			 	}
	public void verifyContactEndDate(int days)
		{
			String actStartDate = endDate.getText();
			boolean status = actStartDate.trim().equals(jlib.getRequiredDate(days));
			Assert.assertEquals(status,true); 
			
		}
}
