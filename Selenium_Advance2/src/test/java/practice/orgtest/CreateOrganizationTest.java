package practice.orgtest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrgPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;
import com.comcast.crm.webdriverutility.JavaUtility;

public class CreateOrganizationTest extends BaseClass{
		@Test
		public void createOrgTest() throws Throwable
		{
			//generate the random number
			JavaUtility jlib=new JavaUtility();
			int ranInt=jlib.getRandomNumber();
			
			//read test script data from Excel file
			
			ExcelUtility elib=new ExcelUtility();
			String orgname=elib.getDataFromExcelFile("Org", 1, 2)+ranInt;
			
			//step 2 : navigate to Organization module
			HomePage hp=new HomePage(driver);
			hp.getOrgLink().click();
			
			//step 3 : click on "create Organization" Button
			OrganizationsPage op=new OrganizationsPage(driver);
			op.getCreateOrg().click();
			
			//step 4 : enter all the details and create new Organization
			CreatingNewOrgPage cnp=new CreatingNewOrgPage(driver);
			cnp.createOrg(orgname);
		
		//step 5 : Verify Header msg expected result
			OrganizationInfoPage oip=new OrganizationInfoPage(driver);
			oip.verifyOrg(orgname);

		//step 5 : Verify OrgName expected result
			oip.verifyOrgName(orgname);
	}

}
