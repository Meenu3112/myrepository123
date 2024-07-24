package VTiger_TestCases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.comcast.crm.objectrepositoryutility.LoginPage;

public class p1 {

	public static void main(String[] args) throws Throwable {
		WebDriver driver=new ChromeDriver();
		FileInputStream fis=new FileInputStream("./src/main/resources/CommonData.properties");
		Properties pro=new Properties();
		pro.load(fis);
		
		String URL=pro.getProperty("url");
		LoginPage lp=new LoginPage(driver);
		lp.loginToApp(URL,"admin", "admin");

}
}
