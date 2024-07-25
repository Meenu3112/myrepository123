package VTiger_TestCases;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Productclass {
	
	// this page is dedicated to productpage
	
	@FindBy(xpath = "//input[@linktest='products']")
	private WebElement productlink;

}
