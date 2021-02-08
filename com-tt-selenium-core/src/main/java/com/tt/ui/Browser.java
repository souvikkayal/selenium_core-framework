package com.tt.ui;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Browser {
	String browserType = "chrome";
	WebDriver driver = null;
	WebElement currElement = null;
	Select select = null;

	
	public WebDriver initBrowser() {
		if(browserType.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			
		}
		else if(browserType.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	public Browser getObject(String locatortype, String value)
	{
		try
		{
			if(locatortype.equalsIgnoreCase("ID"))
			{
				currElement = driver.findElement(By.id(value));
			}
			else if(locatortype.equalsIgnoreCase("XP"))
			{
				currElement = driver.findElement(By.xpath(value));
			}
			else if(locatortype.equalsIgnoreCase("LINKTEXT"))
			{
				currElement = driver.findElement(By.linkText(value));
			}
			else if(locatortype.equalsIgnoreCase("CLASS"))
			{
				currElement = driver.findElement(By.className(value));
			}
		}
		catch(NoSuchElementException nsee)
		{
			System.out.println("Object is not found");
			currElement = null;
		}
		return this;
	}
	
	public int getObjCountByXP(String xp) {
		int count =0;
		try {
			 List<WebElement> elements = driver.findElements(By.xpath(xp));
			//List<WebElement> eles = (List<WebElement>) driver.findElement(By.xpath(xp));
			if(elements!=null && elements.size()>0) {
				count = elements.size();
			}
			
		}catch(Exception e) {
			System.out.println("Elements are not found with xp "+xp);
		}
		return count;
	}
	
	public Browser getObj(String id) {
		
			 getObject("ID",id);
			 return this;
		
	}
	
	public Browser getObjByClass(String className) {
		getObject("CLASS",className);
		return this;
	
}
	
	public Browser getObjByXP(String xp) {
			getObject("XP",xp);
			return this;
		
	}
	public Browser getObjByLinkText(String name) {
		getObject("LINKTEXT",name);
		return this;
	
}
	
	public void setText(String data) {
		if(currElement!=null) {
			currElement.clear();
			currElement.sendKeys(data);
		}
	}
		
	
	public String getText() {
		String ret = "";
		if(currElement!=null)
		{
				ret = currElement.getText();
		}
		return ret;
	}
	
	public void click() {
		if(currElement!=null) {
			currElement.click();
		}
		
	}
	
	public void launchBrowser(String url) {
		driver.get(url);
	}
	
	public void waitForObjectIsVisible(String locator,String value) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		try {
		if(locator.equalsIgnoreCase("ID")) {
			currElement = (WebElement) wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(value)));
		}
		else if(locator.equalsIgnoreCase("XP")) {
			currElement = (WebElement) wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(value)));
		}
		else if(locator.equalsIgnoreCase("LINKTEXT")) {
			currElement = (WebElement) wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.linkText(value)));
		}
		else if(locator.equalsIgnoreCase("NAME")) {
			currElement = (WebElement) wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.name(value)));
		}
		}catch(Exception e) {
			System.out.println("Object is not found with locator: "+ locator + " and the locator value:" +value);
		}
	}
	
	public void selectOption(String value) {
		select = new Select(currElement);
		select.selectByVisibleText(value);
	}
	
	public void printAllOptions() {
		select = new Select(currElement);
		List<WebElement> allOptions = select.getOptions();
		System.out.println("--Printing all options of product filtering--");
		for(int i=0;i<allOptions.size();i++) {
			System.out.println(allOptions.get(i).getText());
		}
		System.out.println("--End of all options--");
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	
	public void close() {
		driver.quit();
	}
	
	 public void switchToFrame(String id)
	  {
		  WebElement iframe = driver.findElement(By.id(id));
		  driver.switchTo().frame(iframe);
	  }
	  public void switchToFramexp(String xp)
	  {
		  WebElement iframe = driver.findElement(By.xpath(xp));
		  driver.switchTo().frame(iframe);
	  }
	  public void exitFrame()
	  {
		  driver.switchTo().defaultContent();
	  }
	  public void switchToAlert()
	  {
		  driver.switchTo().alert();
	  }
	  public void acceptAlert()
	  {
		  driver.switchTo().alert().accept();
	  }
	  public void cancelAlert()
	  {
		  driver.switchTo().alert().dismiss();
	  }
	  public String getAlertInfo()
	  {
		  return driver.switchTo().alert().getText();
	  }
	  public void setTextInAlert(String data)
	  {
		  driver.switchTo().alert().sendKeys(data);
	  }

}
