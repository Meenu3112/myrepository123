package VTiger_TestCases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DeleteOrg {

	public static void main(String[] args) throws Throwable {
		//read common data from properties file
		FileInputStream fis=new FileInputStream("./src/test/resources/CommonData.properties");
		Properties pro=new Properties();
		pro.load(fis);
		
		String BROWSER=pro.getProperty("browser");
		String URL=pro.getProperty("url");
		String USERNAME=pro.getProperty("username");
		String PASSWORD=pro.getProperty("password");
		
		FileInputStream fis1=new FileInputStream("./src/test/resources/Test_Data/Test_Script_Data.xlsx");
		Workbook book = WorkbookFactory.create(fis1);
		Sheet sheetorg = book.getSheet("Org");
		Row roworg = sheetorg.getRow(1);
		String orgname = roworg.getCell(2).toString();
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
		
		driver.findElement(By.linkText("Organizations")).click();
		
		for (int i = 4; i <=14; i++) {
			driver.findElement(By.name("selected_id")).click();
			driver.findElement(By.linkText("del")).click();
			driver.switchTo().alert().accept();
			Thread.sleep(1000);
			i++;
			
		}
		
	}

}
