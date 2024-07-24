package VTiger_TestCases;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CreateNinjaProjectAndVerifytest  {
	Random r=new Random();
	
	@DataProvider(name="Orgdata")
	public Object[][] data() throws Throwable {
		
		FileInputStream fis=new FileInputStream("./src/test/resources/Test_Data/Test_Script_Data.xlsx");
		Workbook book = WorkbookFactory.create(fis);
		Sheet sheet = book.getSheet("CreateOrgNinja");
		Row row = sheet.getRow(0);
		int colnum = row.getPhysicalNumberOfCells();
		int rownum=sheet.getPhysicalNumberOfRows();
		Object[][] data=new Object[rownum-1][colnum];
		for(int i=1;i<rownum;i++) {
			for(int j=0;j<colnum;j++) {
				data[i-1][j]=sheet.getRow(i).getCell(j).toString();
			}
		}
		return data;
	}
	@Test(dataProvider = "Orgdata")
	public void createOrgTest (String projname,String projmanager,String status) {
		r.nextInt(1000);
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://106.51.90.215:8084/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.findElement(By.name("username")).sendKeys("rmgyantra");
		driver.findElement(By.name("password")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.findElement(By.linkText("Projects")).click();
		driver.findElement(By.xpath("//span[text()='Create Project']")).click();
		driver.findElement(By.name("projectName")).sendKeys(projname+r);
		//driver.findElement(By.name("teamSize")).sendKeys("100");
		driver.findElement(By.name("createdBy")).sendKeys(projmanager);
		WebElement ele = driver.findElement(By.xpath("(//select[@name='status'])[1]"));
		
		Select sel=new Select(ele);
		sel.selectByVisibleText(status);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		driver.findElement(By.xpath("//span[text()='Create Project']")).click();
		driver.close();
		
	}

}
