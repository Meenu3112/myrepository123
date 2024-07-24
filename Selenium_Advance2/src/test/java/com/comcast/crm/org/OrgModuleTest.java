package com.comcast.crm.org;

import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrgPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;
import com.comcast.crm.webdriverutility.JavaUtility;
/**
 * @author Meenu
 * Contains 3 test cases
 * 1. Create org test
 * 2. Create org with Industry
 * 3. Create org with phone number
 */
public class OrgModuleTest extends BaseClass {
	@Test(groups="SmokeTest")
	public void createOrgTest() throws Throwable
	{
		/* generate the random number */
		JavaUtility jlib=new JavaUtility();
		int ranInt=jlib.getRandomNumber();
		
		/* read test script data from Excel file */
		
		ExcelUtility elib=new ExcelUtility();
		String orgname=elib.getDataFromExcelFile("Org", 1, 2)+ranInt;
		
		/* step 2 : navigate to Organization module */
		HomePage hp=new HomePage(driver);
		hp.getOrgLink().click();
		
		/* step 3 : click on "create Organization" Button */
		OrganizationsPage op=new OrganizationsPage(driver);
		op.getCreateOrg().click();
		
		/* step 4 : enter all the details and create new Organization */
		CreatingNewOrgPage cnp=new CreatingNewOrgPage(driver);
		cnp.createOrg(orgname);
	
		/* step 5 : Verify Header msg expected result */
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		oip.verifyOrg(orgname);

		/* step 5 : Verify OrgName expected result */
		oip.verifyOrgName(orgname);
}
	@Test(groups="RegressionTest")
	public void createorgWithIndTest() throws Throwable {
		JavaUtility jlib=new JavaUtility();
		int ranInt=jlib.getRandomNumber();
		
		/*  get data from Excel */
		
		ExcelUtility elib=new ExcelUtility();
		String orgname=elib.getDataFromExcelFile("Org", 4, 2)+ranInt;
		String indname=elib.getDataFromExcelFile("Org", 4, 3);
		String type=elib.getDataFromExcelFile("Org", 4, 4);
		
		/* step 2 : navigate to Organization module */
		HomePage hp=new HomePage(driver);
		hp.getOrgLink().click();
		
		/* step 3 : click on "create Organization" Button */
		OrganizationsPage op=new OrganizationsPage(driver);
		op.getCreateOrg().click();
		
		/* step 4 : enter all the details and create new Organization */
		CreatingNewOrgPage cnp=new CreatingNewOrgPage(driver);
		cnp.createOrg(orgname, indname,type);
		
		/* step 5 : Verify Industry name expected result */
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		oip.verifyorgInd(indname);
		oip.verifyOrgtype(type);

	}
	@Test(groups = "RegressionTest")
	public  void createOrgWithPhone() throws Throwable {
		
		/* step 1 Generate random number */
		
		JavaUtility jlib=new JavaUtility();
		int ranInt=jlib.getRandomNumber();
		
		/* Step 2 Fetch data from Excel */
		
		ExcelUtility elib=new ExcelUtility();
		String orgname=elib.getDataFromExcelFile("Org", 7, 2)+ranInt;
		String phoneno=elib.getDataFromExcelFile("Org", 7, 3);
		
		/* Step 3 Navigate to Organization Link */
		
		HomePage hp=new HomePage(driver);
		hp.getOrgLink().click();
		
		/*  Step 4 Create new Organization */
		
		OrganizationsPage op=new OrganizationsPage(driver);
		op.getCreateOrg().click();
		CreatingNewOrgPage cnp=new CreatingNewOrgPage(driver);
		cnp.createOrg(orgname, phoneno);
		
		/* step 5 : Verify org phone no expected result */
		
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		oip.verifyOrgPhone(phoneno);
				
	}

}
