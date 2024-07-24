package VTiger_TestCases;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

public class VTiger_Org_Test {
	@Test
	public void createOrgTest(XmlTest test) throws Throwable {
		
		
		String URL=test.getParameter("url");
		String USERNAME = test.getParameter("username");
		String PASSWORD = test.getParameter("password");
		FileInputStream fis1=new FileInputStream("./src/test/resources/Test_Data/Test_Script_Data.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		Sheet sh = wb.getSheet("Org");
		Row row = sh.getRow(0);
		int colCount = row.getPhysicalNumberOfCells();
		int rowCount = sh.getLastRowNum();
		Object[][] data=new Object[rowCount][colCount];
		for (int i = 1; i <= rowCount; i++) 
		{
			for (int j = 0; j < colCount; j++)
			{
				data[i-1][j]=sh.getRow(i).getCell(j).toString();
				
			}
		}
		
	WebDriver driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.get(URL);
	
	driver.findElement(By.name("user_name")).sendKeys();
	driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
	driver.findElement(By.id("submitButton")).click();
}
}