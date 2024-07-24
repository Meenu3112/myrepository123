package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.webdriverutility.WebDriverUtility;

/**
 * @author Meenu
 * Contains elements and libraries to create new org
 */
public class CreatingNewOrgPage extends WebDriverUtility {
	WebDriver driver;
	public CreatingNewOrgPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	
	@FindBy(name="accountname")
	private WebElement orgname;
	@FindBy(xpath="(//input[@name='button' and @accesskey='S'])[1]")
	private WebElement saveOrg;
	@FindBy(xpath="//select[@name='industry']")
	private WebElement industryDD; 
	@FindBy(xpath="//select[@name='accounttype']")
	private WebElement typeDD;
	@FindBy(xpath="(//input[@name='button' and @accesskey='S'])[1]")
	private WebElement saveBtn;
	@FindBy(id="phone")
	private WebElement phoneno;
	public WebElement getOrgname() {
		return orgname;
	}
	public WebElement getSaveOrg() {
		return saveOrg;
	}
	public WebElement getIndustryDD() {
		return industryDD;
	}
	
	public WebElement getTypeDD() {
		return typeDD;
	}
	
	public WebElement getSaveBtn() {
		return saveBtn;
	}
	public WebElement getPhoneno() {
		return phoneno;
	}
	public void createOrg(String name,String phone)
	{	
		orgname.sendKeys(name);
		phoneno.sendKeys(phone);
		saveBtn.click();
	}
	/**
	 * creates org with name
	 * @param name
	 */
	public void createOrg(String name)
	{	
		orgname.sendKeys(name);
		saveBtn.click();
	}
	/**
	 * creates org with Industry
	 * @param name
	 * @param industry
	 * @param type
	 */
	public void createOrg(String name,String industry,String type)
	{	
		orgname.sendKeys(name);
		select(industryDD, industry);
		select(typeDD, type);
		saveBtn.click();
	}

}
