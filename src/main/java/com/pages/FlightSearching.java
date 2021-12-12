package com.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.base.TestBase;


public class FlightSearching extends TestBase {

	public void base(String from, String dest)
	{
		Actions act = new Actions(driver);
		WebElement ele = driver.findElement(By.id("gosuggest_inputSrc"));
		ele.clear();
		ele.sendKeys(from);
		act.pause(2000).perform();
		ele.sendKeys(Keys.ARROW_DOWN);
		ele.sendKeys(Keys.ENTER);
	
		WebElement element= driver.findElement(By.id("gosuggest_inputDest"));
		element.clear();
		element.sendKeys(dest);
		act.pause(2000).perform();
		element.sendKeys(Keys.ARROW_DOWN);
		element.sendKeys(Keys.ENTER);
		
		driver.findElement(By.xpath("//input[@id='departureCalendar']")).click();	 				 
		driver.findElement(By.xpath("//div[@aria-label='Wed Dec 08 2021']")).click();
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}
	
	public String validateFlightSearching(String source, String dest)
	{
		String from, destination;
		from = source;
		destination = dest;
		base(from,destination);
		List<WebElement> viewfare = driver.findElements(By.xpath("//button[contains(@class,'BookButton')]"));
		return viewfare.get(0).getText();
	}
	
	public String invalidFlightSearching(String source, String dest)
	{
		String from, destination;
		from = source;
		destination = dest;
		base(from,destination);
		return driver.findElement(By.xpath("//span[@class='status_cont red ico13']")).getText();
	}

	//////////////////////////////////////////////////////////////////////////
	public void first()
	{
		base("Mumbai","Kolkata");
		List<WebElement> viewfare = driver.findElements(By.xpath("//button[contains(@class,'BookButton')]"));
		viewfare.get(0).click();
		List<WebElement> bookbut = driver.findElements(By.xpath("//input[@value='BOOK']"));
		bookbut.get(0).click();
		
	}

	
	public String validateBookButton()
	{
		first();
		List<WebElement> tktdetails = driver.findElements(By.xpath("//span[@class='flex1']"));
		return tktdetails.get(0).getText();
	}
	
	public void second(String fname, String lname, String email, String number)
	{
		first();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\"insurance_section\"]/div/div[2]/div[5]/div/button[2]")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
		WebElement element =driver.findElement(By.xpath("//select[@class='common-elementsstyles__CustSelectTrvl-sc-ilw4bs-9 evINTo']"));
		Select sel = new  Select(element);
		sel.selectByVisibleText("Mr");
		sel.getFirstSelectedOption();
		driver.findElement(By.name("givenname")).sendKeys(fname);
		driver.findElement(By.name("lastname")).sendKeys(lname);
		driver.findElement(By.name("email")).sendKeys(email);
		driver.findElement(By.name("mobile")).sendKeys(number);
		driver.findElement(By.xpath("//button[@class='dweb-commonstyles__FltBtn-sc-13fxsy5-12 hFdhXg fb quicksand wid25 font16']")).click();
	}
	
	public String validatingPersonalDetails(String fname, String lname, String email, String number)
	{
		String fn,ln,em,mn;
		fn = fname;
		ln = lname;
		em = email;
		mn = number;
		second(fn,ln,em,mn);
		List<WebElement> tktdetails = driver.findElements(By.xpath("//span[@class='flex1']"));
		return tktdetails.get(3).getText();		
	}
	
	public String validatingInvalidPersonalDetails(String fname, String lname, String email, String number)
	{
		String fn,ln,em,mn;
		fn = fname;
		ln = lname;
		em = email;
		mn = number;
		second(fn,ln,em,mn);
		return driver.findElement(By.xpath("//span[@class='red width100 padT3']")).getText();		
	}
	
	
	public String PaymentPage(String fname, String lname, String email, String number)
	{
		String fn,ln,em,mn;
		fn = fname;
		ln = lname;
		em = email;
		mn = number;
		second(fn,ln,em,mn);
		driver.findElement(By.xpath("//button[@value='OK']")).click();
		driver.findElement(By.xpath("//button[@class='dweb-commonstyles__FltBtn-sc-13fxsy5-12 hFdhXg fb quicksand wid25 font16']")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		List<WebElement> tktdetails = driver.findElements(By.xpath("//span[@class='flex1']"));
		return tktdetails.get(4).getText();
	}
}
