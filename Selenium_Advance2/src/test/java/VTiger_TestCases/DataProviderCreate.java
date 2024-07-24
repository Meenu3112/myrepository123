package VTiger_TestCases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;

public class DataProviderCreate {
	
	WebDriver driver;
	ExcelUtility elib=new ExcelUtility();
	String data;
	@DataProvider
	public Object[][] getData() throws Throwable {
		int rowCount = elib.getRowCount("phoneprices");
		System.out.println(rowCount);
		Object[][] objArr=new Object[6][1];
		for (int i = 0; i < 6; i++) {
			objArr[i][0]=elib.getDataFromExcelFile("phoneprices", i, 0);
		}
		return objArr;
	}
	@Test(dataProvider = "getData")
	public void createContactTest(String name)
	{	
		driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.amazon.in");
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("iphone",Keys.ENTER);
		String x = driver.findElement(By.xpath("//span[.='"+name+"']/ancestor::div[@class='a-section a-spacing-small a-spacing-top-small']/descendant::span[@class='a-price-whole']")).getText();
		System.out.println(x);
		driver.quit();
	}
	
	
	

}
