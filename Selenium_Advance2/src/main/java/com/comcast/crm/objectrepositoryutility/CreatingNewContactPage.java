package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/**
 * @author Meenu
 * Contains elements and business lib to create Contact
 */
public class CreatingNewContactPage {

	WebDriver driver;
	public CreatingNewContactPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(name = "lastname")
	private WebElement lastname;
	@FindBy(xpath = "//input[@class='crmButton small save']")
	private WebElement createContact;
	@FindBy(xpath = "//input[@name='account_name']/following-sibling::img")
	private WebElement orgname;
	@FindBy(name = "support_start_date")
	private WebElement startDate;
	@FindBy(name = "support_end_date")
	private WebElement endDate;
	public WebElement getOrgname() {
		return orgname;
	}
	public WebElement getLastname() {
		return lastname;
	}
	public WebElement getCreateContact() {
		return createContact;
	}
	public WebElement getStartDate() {
		return startDate;
	}
	public WebElement getEndDate() {
		return endDate;
	}
	/**
	 * creates contact with start date and end date
	 * @param name
	 * @param startd
	 * @param endD
	 */
	public void createContactWithDate(String name,String startd,String endD)
	{	
		lastname.sendKeys(name);
		WebElement ele1 = getStartDate();
		ele1.clear();
		ele1.sendKeys(startd);
		WebElement ele2 = getEndDate();
		ele2.clear();
		ele2.sendKeys(endD);
		createContact.click();
	}
}
