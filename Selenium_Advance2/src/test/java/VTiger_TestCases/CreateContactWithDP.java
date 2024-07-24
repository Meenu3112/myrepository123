package VTiger_TestCases;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;

public class CreateContactWithDP {
	
	WebDriver driver;
	
	
	@Test
	public void createTest()  {
		 driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.amazon.in");
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("iphone",Keys.ENTER);
		Object[][] objArr=new Object[18][1];
		List<WebElement> names = driver.findElements(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal']"));
		for (WebElement name : names) {
			String ph_name = name.getText();
				System.out.println(ph_name);
				
		}
	//	String x = driver.findElement(By.xpath(""+prod_name+"']/ancestor::div[@class='a-section a-spacing-small a-spacing-top-small']/descendant::span[.='70,999']")).getText();
	}	
		
	}

