package practice.orgtest;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;



public class VerifyHomePageTest {
	
	@Test
	public void homePageverifytest(Method mtd)
	{	
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:8888/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		System.out.println(mtd.getName()+"  Starts");
		String expectedTitle="Home";
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		String actTitle = driver.findElement(By.xpath("//a[contains(text(),'Home')]")).getText();
		Assert.assertEquals(actTitle, expectedTitle);
//		if(actTitle.trim().equals(expectedTitle)) {
//			driver.out.println(expectedTitle+" is verified====PASS");
//		}
//		else {
//			driver.out.println(expectedTitle+" is not verified====FAIL");
//		}
	}
	
	@Test
	public void logoVerifytest()
	{
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:8888/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		//driver.out.println(mtd.getName()+"  Starts");
		boolean logo = driver.findElement(By.xpath("//img[@title='vtiger-crm-logo.gif']")).isDisplayed();
		Assert.assertEquals(logo, true);
//		if(logo==true)
//		{
//			driver.out.println("Logo is verified====PASS");
//		}
//		else
//		{
//			driver.out.println("Logo is not verified====FAIL");
//		}
	}

}
