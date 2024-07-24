package com.comcast.crm.webdriverutility;

import java.awt.Robot;
import java.awt.AWTException;
import java.awt.Desktop.Action;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {

	public void waitForPageToLoad(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	public void waitForElementPresent(WebDriver driver,WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	public void switchToNewTabOnURL(WebDriver driver,String partialUrl)
	{
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();
		while (it.hasNext())
		{
			String winId = (String) it.next();
			driver.switchTo().window(winId);
			String actUrl=driver.getCurrentUrl();
			if (actUrl.contains(partialUrl)) 
			{
				break;
			}
		}
	}
	public void switchToTabOnTitle(WebDriver driver,String partialTitle)
	{
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();
		while (it.hasNext()) 
		{
			String winId = (String) it.next();
			driver.switchTo().window(winId);
			String actTitle = driver.getTitle();
			if (actTitle.contains(partialTitle)) 
			{
				break;
			}
		}
	}
	public void switchToFrame(WebDriver driver,int index)
	{
		driver.switchTo().frame(index);
	}
	public void switchToFrame(WebDriver driver,String text)
	{
		driver.switchTo().frame(text);
	}
	public void switchToFrame(WebDriver driver,WebElement element)
	{
		driver.switchTo().frame(element);
	}
	public void switchToFrame(WebDriver driver)
	{
		driver.switchTo().parentFrame();
	}
	public void switchToAlertAndAccept(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}
	public void switchToAlertAndCancel(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
	}
	public void select(WebElement element,String text)
	{
		Select sel=new Select(element);
		sel.selectByVisibleText(text);
	}
	public void select(WebElement element,int index)
	{
		Select sel=new Select(element);
		sel.selectByIndex(index);
	}
	public void mouseMoveToElement(WebDriver driver,WebElement element)
	{
		Actions act=new Actions(driver);
		act.moveToElement(element).perform();
	}
	public void doubleClick(WebDriver driver,WebElement element)
	{
		Actions act=new Actions(driver);
		act.doubleClick(element).perform();
	}
	public void clickAndHold(WebDriver driver,WebElement element)
	{
		Actions act=new Actions(driver);
		act.clickAndHold(element).perform();;
	}
	public void release(WebDriver driver,WebElement element)
	{
		Actions act=new Actions(driver);
		act.release(element);
	}
	public void dragAndDrop(WebDriver driver,WebElement ele1,WebElement ele2)
	{
		Actions act=new Actions(driver);
		act.dragAndDrop(ele1, ele2);
	}
	public void rightClick(WebDriver driver)
	{
		Actions act=new Actions(driver);
		act.contextClick();
	}
	public void rightClick(WebDriver driver,WebElement element)
	{
		Actions act=new Actions(driver);
		act.contextClick(element);
	}
	public void usingRobotClass(WebDriver driver,char c) throws Throwable
	{
		Robot rob=new Robot();
		rob.keyPress(KeyEvent.VK_A);
	}
	public void scrollToElementUsingJavaScriptExecutor(WebDriver driver,WebElement element)
	{
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(false)", element);
	}
	public void scrollDownByUsingJavascriptExecutor(WebDriver driver)
	{
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("scrollBy(0,500)");
	}
	public void scrollUPByUsingJavascriptExecutor(WebDriver driver)
	{
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("scrollBy(0,-500)");
	}
	public String getTitleUsingJavascriptExecutor(WebDriver driver)
	{
		JavascriptExecutor js=(JavascriptExecutor) driver;
		String title = (String) js.executeScript("return document.title;");
		return title;
	}
	public void takeScreenshotOfPage(WebDriver driver) throws Throwable
	{	
		JavaUtility jlib=new JavaUtility();
		int rno = jlib.getRandomNumber();
		TakesScreenshot ts=(TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest=new File("./Screenshots/S_"+rno+".png");
		FileHandler.copy(src, dest);
	}
	public void takeScreenshotOfWebelement(WebElement element) throws Throwable
	{	
		JavaUtility jlib=new JavaUtility();
		File src=element.getScreenshotAs(OutputType.FILE);
		File dest=new File("./Screenshots/WE_"+jlib.getRandomNumber()+".png");
		FileHandler.copy(src, dest);
	}
	
	
}
