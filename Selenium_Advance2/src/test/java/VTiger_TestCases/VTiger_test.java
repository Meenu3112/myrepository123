package VTiger_TestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class VTiger_test {
	@Test
	public void runTimeParameterTest()
	{
		String URL=System.getProperty("url");
		WebDriver driver=new ChromeDriver();
		driver.get(URL);
	}

}
