package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class OrganizationInfoPage {

	WebDriver driver;
	public OrganizationInfoPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement orgheader;
	@FindBy(id="dtlview_Organization Name")
	private WebElement orgname;
	@FindBy(id="dtlview_Industry")
	private WebElement industryname;
	@FindBy(id="dtlview_Type")
	private WebElement type;
	@FindBy(id="dtlview_Phone")
	private WebElement phone;
	public WebElement getPhone() {
		return phone;
	}

	public WebElement getOrgname() {
		return orgname;
	}

	public WebElement getOrgheader() {
		return orgheader;
	}

	public WebElement getIndustryname() {
		return industryname;
	}

	public WebElement getType() {
		return type;
	}
	public void verifyOrg(String orgname)
	{
		
		String headerinfo = orgheader.getText();
		if (headerinfo.contains(orgname)) {
			System.out.println(orgname+" is created===PASS");
		}
		else {
			System.out.println(orgname+" is not created===FAIL");
		}
	}
	public void verifyorgInd(String indname)
	{
		String actIndName =industryname.getText();
		Assert.assertEquals(actIndName,indname);
//		if (actIndName.equals(indname)) {
//			System.out.println(indname+" information is verified===PASS");
//		}
//		else {
//			System.out.println(indname+" information is not verified===FAIL");
//		}
	}
	public void verifyOrgtype(String type1)
	{
		String actType = type.getText();
		Assert.assertEquals(actType,type1);
//		if (actType.equals(type1)) {
//			System.out.println(type1+" information is verified===PASS");
//		}
//		else {
//			System.out.println(type1+" is not verified===FAIL");
//		}
	}
	public void verifyOrgPhone(String phoneno)
	{
		String actPhoneno =phone.getText();
		Assert.assertEquals(actPhoneno,phoneno);
//		if (actPhoneno.equals(phoneno)) {
//			System.out.println(phoneno+" information is verified===PASS");
//		}
//		else {
//			System.out.println(phoneno+" information is not verified===FAIL");
//		}
	}
	public void verifyOrgName(String name) {
		String actOrgName = orgname.getText();
		Assert.assertEquals(actOrgName,name);
//		if (actOrgName.equals(name)) {
//			System.out.println(name+" is verified===PASS");
//		}
//		else {
//			System.out.println(name+" is not verified===FAIL");
//		}
//		
	}
}
