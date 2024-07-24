package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsPage {
	WebDriver driver;
	public OrganizationsPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath = "//img[@alt='Create Organization...']")
	private WebElement createOrg;
	@FindBy(id = "search_txt")
	private WebElement searchbox;
	
	public WebElement getSearchbox() {
		return searchbox;
	}

	public WebElement getCreateOrg() {
		return createOrg;
	}
	

}
