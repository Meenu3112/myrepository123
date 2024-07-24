package VTiger_TestCases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

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

public class CreateOrganizationTest {

	public static void main(String[] args) throws Throwable {
		//read common data from properties file
		FileInputStream fis=new FileInputStream("./src/test/resources/CommonData.properties");
		Properties pro=new Properties();
		pro.load(fis);
		
		String BROWSER=pro.getProperty("browser");
		String URL=pro.getProperty("url");
		String USERNAME=pro.getProperty("username");
		String PASSWORD=pro.getProperty("password");
		
		//generate the random number
		Random random=new Random();
		int randomInt=random.nextInt(1000);
		
		//read test script data from Excel file
		FileInputStream fis1=new FileInputStream("./src/test/resources/Test_Data/Test_Script_Data.xlsx");
		Workbook book = WorkbookFactory.create(fis1);
		Sheet sheetorg = book.getSheet("Org");
		Row roworg = sheetorg.getRow(1);
		String orgname = roworg.getCell(2).toString()+randomInt;
		String indname = roworg.getCell(3).toString();
		String type = roworg.getCell(4).toString();
		Sheet sheetcontact = book.getSheet("contact");
		Row rowcontact = sheetcontact.getRow(1);
		String lastname = roworg.getCell(2).toString();
		Sheet sheetcampaign = book.getSheet("Campaign");
		Row rowcampaign = sheetcampaign.getRow(1);
		String campaignname = rowcampaign.getCell(2).toString();
		Sheet sheetproduct = book.getSheet("Product");
		Row rowproduct = sheetproduct.getRow(1);
		String productname = rowproduct.getCell(2).toString();
		Sheet sheetopportunities = book.getSheet("Opportunities");
		Row rowopportunities = sheetopportunities.getRow(1);
		String opportunityname = rowopportunities.getCell(2).toString();
		
		WebDriver driver=null;
		if (BROWSER.equals("chrome")) {
			driver=new ChromeDriver();
		}
		else if(BROWSER.equals("firefox")) {
			driver=new FirefoxDriver();
		}
		else if(BROWSER.equals("edge")) {
			driver=new EdgeDriver();
		}
		else {
			driver=new ChromeDriver();
		}
		//step 1 : Login to app
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.linkText("More")).click();
		Thread.sleep(1000);
		//Create Campaign
		
		driver.findElement(By.linkText("Campaigns")).click();
		driver.findElement(By.xpath("//img[@title='Create Campaign...']")).click();
		driver.findElement(By.name("campaignname")).sendKeys(campaignname);
		driver.findElement(By.xpath("//input[@class='crmButton small save']")).click();
		
		
		//verify Campaign info
		
		String actCampaignName = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (actCampaignName.trim().equals(campaignname)) {
			System.out.println(campaignname+" information is verified===PASS");
		}
		else {
			System.out.println(campaignname+" information is not verified===FAIL");
		}
		//Create Product
		driver.findElement(By.linkText("Products")).click();
		driver.findElement(By.xpath("//img[@title='Create Product...']")).click();
		driver.findElement(By.name("productname")).sendKeys(productname);
		driver.findElement(By.xpath("//input[@class='crmbutton small save']")).click();
		
		//verify Product info
				String actProductName = driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();
				
				if (actProductName.trim().equals(productname)) {
					System.out.println(productname+" information is verified===PASS");
				}
				else {
					System.out.println(productname+" information is not verified===FAIL");
				}
		
		//step 2 : navigate to Organization module
		driver.findElement(By.linkText("Organizations")).click();
		
		//step 3 : click on "create Organization" Button
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		//step 4 : enter all the details and create new Organization
		driver.findElement(By.name("accountname")).sendKeys(orgname);
		WebElement wbs1 = driver.findElement(By.xpath("//select[@name='industry']"));
		Select sel=new Select(wbs1);
		sel.selectByVisibleText(indname);
		WebElement wbs2 = driver.findElement(By.xpath("//select[@name='accounttype']"));
		Select sel2=new Select(wbs2);
		sel2.selectByVisibleText(type);
		
		driver.findElement(By.xpath("(//input[@name='button' and @accesskey='S'])[1]")).click();
		
		//step 5 : Verify Industry name expected result
		
		String actIndName = driver.findElement(By.id("dtlview_Industry")).getText();
		if (actIndName.equals(indname)) {
			System.out.println(indname+" information is verified===PASS");
		}
		else {
			System.out.println(indname+" information is not verified===FAIL");
		}
		
		//step 5 : Verify industry type expected result
		
		String actType = driver.findElement(By.id("dtlview_Type")).getText();
		if (actType.equals(type)) {
			System.out.println(type+" information is verified===PASS");
		}
		else {
			System.out.println(type+" is not verified===FAIL");
		}

		Thread.sleep(2000);
		//Create Contact Using Organisation 
		
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		driver.findElement(By.name("lastname")).sendKeys(lastname);
		
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
		
		//Switching to child window (Organisation)
		
		Set<String> childwins = driver.getWindowHandles();
		Iterator<String> it1 = childwins.iterator();
		while (it1.hasNext()) {
			String winId1 = it1.next();
			driver.switchTo().window(winId1);
			String actualcurrUrl = driver.getCurrentUrl();
			if (actualcurrUrl.contains("module=Accounts&action")) {
				break;
			}
		}
		//Selecting the Organisation
		
		WebElement ele = driver.findElement(By.id("search_txt"));
		ele.sendKeys(orgname);
		ele.submit();
		driver.findElement(By.xpath("//a[text()='"+orgname+"']")).click();
		Thread.sleep(2000);
		//Switching back to parent window
		
		Set<String> childwins1 = driver.getWindowHandles();
		
		Iterator<String> it11 = childwins1.iterator();
		while (it11.hasNext()) {
			String winId11 = it11.next();
			driver.switchTo().window(winId11);
			String actualcurrUrl = driver.getCurrentUrl();
			if (actualcurrUrl.contains("module=Contacts&action")) {
				break;
			}
		}
		driver.findElement(By.xpath("//input[@class='crmButton small save']")).click();
		String actOrg1 = driver.findElement(By.id("mouseArea_Organization Name")).getText();
		System.out.println(actOrg1);
		if (actOrg1.trim().equals(orgname)) {
			System.out.println(orgname+" information is verified====PASS");
		}
		else 
		{
			System.out.println(orgname+" information is not verified====FAIL");
		}
		
		String actContactheader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (actContactheader.contains(lastname)) {
			System.out.println(lastname+" header info is verified===PASS");
		}
		else
		{
			System.out.println(lastname+" header info is not verified===FAIL");
		}
		driver.findElement(By.linkText("Opportunities")).click();
		driver.findElement(By.xpath("//img[@title='Create Opportunity...']")).click();
		driver.findElement(By.name("potentialname")).sendKeys(opportunityname);
		driver.findElement(By.xpath("(//img[@title='Select' and @alt='Select'])[2]")).click();
		Set<String> childwinss = driver.getWindowHandles();
		
		Iterator<String> it2 = childwinss.iterator();
		while (it2.hasNext()) {
			String winId2 = it2.next();
			driver.switchTo().window(winId2);
			String actualcurrUrl = driver.getCurrentUrl();
			if (actualcurrUrl.contains("module=Campaigns&action")) {
				break;
			}
		}
		WebElement elem = driver.findElement(By.id("search_txt"));
		elem.sendKeys(campaignname);
		elem.submit();
		driver.findElement(By.xpath("//a[text()='"+campaignname+"']")).click();
		Thread.sleep(2000);
		Set<String> childwins3 = driver.getWindowHandles();
		
		Iterator<String> it3 = childwins3.iterator();
		while (it3.hasNext()) {
			String winId12 = it3.next();
			driver.switchTo().window(winId12);
			String actualcurrUrl = driver.getCurrentUrl();
			if (actualcurrUrl.contains("module=Potential&action")) {
				break;
			}
			driver.findElement(By.xpath("(//img[@title='Select' and @alt='Select'])[1]")).click();
			Set<String> childwi = driver.getWindowHandles();
			
			Iterator<String> it4 = childwi.iterator();
			while (it4.hasNext()) {
				String winId4 = it4.next();
				driver.switchTo().window(winId4);
				String actualcurUrl = driver.getCurrentUrl();
				if (actualcurUrl.contains("module=Accounts&action")) {
					break;
				}
			}
			WebElement ele4 = driver.findElement(By.xpath("//input[@name='search_text']"));
			ele4.sendKeys(orgname);
			ele4.submit();
			driver.findElement(By.xpath("//a[text()='"+orgname+"']")).click();
			Thread.sleep(2000);
			Set<String> childwins5 = driver.getWindowHandles();
			
			Iterator<String> it5 = childwins5.iterator();
			while (it5.hasNext()) {
				String winId5 = it5.next();
				driver.switchTo().window(winId5);
				String actualcurrrUrl = driver.getCurrentUrl();
				if (actualcurrrUrl.contains("module=Potentials&action")) {
					break;
				}
			}
			
			driver.findElement(By.xpath("//input[@class='crmbutton small save']")).click();
			
			Thread.sleep(1000);
		//step 6 : Logout
		driver.quit();
	
		}
	}
}
