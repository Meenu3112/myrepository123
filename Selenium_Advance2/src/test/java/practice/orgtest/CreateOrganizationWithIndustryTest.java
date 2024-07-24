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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrgPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;
import com.comcast.crm.webdriverutility.JavaUtility;

public class CreateOrganizationWithIndustryTest extends BaseClass {
	@Test
	public void createorgTest() throws Throwable {
		JavaUtility jlib=new JavaUtility();
		int ranInt=jlib.getRandomNumber();
		
		// get data from Excel
		
		ExcelUtility elib=new ExcelUtility();
		String orgname=elib.getDataFromExcelFile("Org", 4, 2)+ranInt;
		String indname=elib.getDataFromExcelFile("Org", 4, 3);
		String type=elib.getDataFromExcelFile("Org", 4, 4);
		
		//step 2 : navigate to Organization module
		HomePage hp=new HomePage(driver);
		hp.getOrgLink().click();
		
		//step 3 : click on "create Organization" Button
		OrganizationsPage op=new OrganizationsPage(driver);
		op.getCreateOrg().click();
		
		//step 4 : enter all the details and create new Organization
		CreatingNewOrgPage cnp=new CreatingNewOrgPage(driver);
		cnp.createOrg(orgname, indname,type);
		
		//step 5 : Verify Industry name expected result
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		oip.verifyorgInd(indname);
		oip.verifyOrgtype(type);

	}
	}
