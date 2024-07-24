package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.webdriverutility.WebDriverUtility;

 /**
  * @author Meenu
  * c
  * Contains Login Page elements & Business libraries like login()
  */
  
  
public class LoginPage extends WebDriverUtility	
/*Rule -1 Create a separate Java Class */

/*Rule -2 Object initialization*/
{
	WebDriver driver;
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	/*Rule -3 Oject Identification*/
	
	@FindBy(name="user_name")
	private WebElement usernameEdit;
	
	@FindBy(name="user_password")
	private WebElement passwordEdit;
	
	@FindBy(id="submitButton")
	private WebElement loginBtn;
	
	//Rule -4 Object Encapsulation
	public WebElement getUsernameEdit() {
		return usernameEdit;
	}

	public WebElement getPasswordEdit() {
		return passwordEdit;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}
	/*Rule -5 Object UtiliZation*/
	/**
	 * login to application based on username,password,url arguments
	 * @param url
	 * @param username
	 * @param password
	 */
	public void loginToApp(String url,String username,String password)
	{	
		waitForPageToLoad(driver);
		driver.manage().window().maximize();
		driver.get(url);
		getUsernameEdit().sendKeys(username);
		getPasswordEdit().sendKeys(password);
		getLoginBtn().click();
	}
}
