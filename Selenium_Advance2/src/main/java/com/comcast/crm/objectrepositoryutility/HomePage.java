package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.webdriverutility.WebDriverUtility;

public class HomePage {
	
	WebDriver driver;
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	//Rule -3 Oject Identification
	
	@FindBy(xpath="//a[.='Organizations']")
	private WebElement orgLink;
	@FindBy(linkText = "Products")
	private WebElement productLink;
	@FindBy(linkText = "Contacts")
	private WebElement contactLink;
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminimg;
	@FindBy(id="ondemand_sub")
	private WebElement signoutlink;
	
	public WebElement getProductLink() {
		return productLink;
	}

	public void setProductLink(WebElement productLink) {
		this.productLink = productLink;
	}

	public WebElement getAdminimg() {
		return adminimg;
	}

	public WebElement getSignoutlink() {
		return signoutlink;
	}

	public WebElement getOrgLink() {
		return orgLink;
	}

	public WebElement getContactLink() {
		return contactLink;
	}
	public void logOut()
	{
		WebDriverUtility wlib=new WebDriverUtility();
		wlib.mouseMoveToElement(driver, adminimg);
		signoutlink.click();
		
	}
	
	
}
