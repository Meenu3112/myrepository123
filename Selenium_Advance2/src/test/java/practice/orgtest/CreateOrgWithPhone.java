package practice.orgtest;

import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrgPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;
import com.comcast.crm.webdriverutility.JavaUtility;

public class CreateOrgWithPhone extends BaseClass{
	
	@Test
	public  void createOrgWithPhone() throws Throwable {
		
		// step 1 Generate random number
		
		JavaUtility jlib=new JavaUtility();
		int ranInt=jlib.getRandomNumber();
		
		// Step 2 Fetch data from Excel
		
		ExcelUtility elib=new ExcelUtility();
		String orgname=elib.getDataFromExcelFile("Org", 7, 2)+ranInt;
		String phoneno=elib.getDataFromExcelFile("Org", 7, 3);
		
		//Step 3 Navigate to Organization Link
		
		HomePage hp=new HomePage(driver);
		hp.getOrgLink().click();
		
		// Step 4 Create new Organization
		
		OrganizationsPage op=new OrganizationsPage(driver);
		op.getCreateOrg().click();
		CreatingNewOrgPage cnp=new CreatingNewOrgPage(driver);
		cnp.createOrg(orgname, phoneno);
		
		//step 5 : Verify org phone no expected result
				OrganizationInfoPage oip=new OrganizationInfoPage(driver);
				oip.verifyOrgPhone(phoneno);
				
	}

}
