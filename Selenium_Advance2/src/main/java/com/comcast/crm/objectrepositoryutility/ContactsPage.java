package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/**
 * @author Meenu
 * Contains webelements to navigate to create contact page
 */
public class ContactsPage {
	
	WebDriver driver;
	public ContactsPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath = "//img[@alt='Create Contact...']")
	private WebElement createContact;
	public WebElement getCreateContact() {
		return createContact;
	}
	
}
