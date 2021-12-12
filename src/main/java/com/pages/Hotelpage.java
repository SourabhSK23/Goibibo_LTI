package com.pages;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.base.TestBase;

public class Hotelpage  extends TestBase{
	public String validateUserinHotelPage() {
		return driver.getTitle();
	
	}
	//Searchfield
	public boolean searchfield() {     
		boolean b=driver.findElement(By.id("downshift-1-input")).isDisplayed();
		return b;
		
	}
	//Login with valid input
	public String validateInput(String validloc) throws InterruptedException {
		
	driver.findElement(By.xpath("//*[@id=\"downshift-1-input\"]")).sendKeys(validloc);
	Thread.sleep(2000);
	driver.findElement(By.xpath("//*[@id=\"downshift-1-input\"]")).sendKeys(Keys.DOWN, Keys.RETURN);
    driver.findElement(By.xpath("//*[@id='root']/div[2]/div/section[1]/div[1]/div/button")).click();
    driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    
    return driver.getTitle();
	}
	// Login with invalid input
	public String invalidInput(String invalidloc) {
	driver.findElement(By.xpath("//*[@id=\"downshift-1-input\"]")).sendKeys(invalidloc);
	driver.findElement(By.xpath("//*[@id='root']/div[2]/div/section[1]/div[1]/div/button")).click();
	return driver.getCurrentUrl();
	
	}
	// Login with noinput
	public String noInput() {
		driver.findElement(By.xpath("//*[@id=\"downshift-1-input\"]")).sendKeys("   ");
		return driver.getCurrentUrl();
		
		}
	// Selecting hotel
	public void select() throws InterruptedException {
		validateInput("Goalpara");
		Thread.sleep(5000);
		String parentwindow = driver.getWindowHandle();		
		driver.findElement(By.xpath("//h4[@itemprop = 'name']")).click();
		Set<String> windowhandles = driver.getWindowHandles();
		Iterator<String> it =windowhandles.iterator();
		it.next();
		String ChildWindow=it.next();
		driver.switchTo().window(ChildWindow);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,550)");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//button[@data-testid='selectRoomBtn']")).click();
		
	}
	public 	String selecthotel() throws InterruptedException {
		select();
		return driver.getTitle();
	}
           // Hotel Booking	
	public void booking(String fname,String lname,String email,String number) throws InterruptedException {
		select();
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Thread.sleep(5000);
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
		driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[1]/div[2]/div[2]/div[1]/div/div[1]/div[2]/input")).sendKeys(fname);
		driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[1]/div[2]/div[2]/div[1]/div/div[1]/div[3]/input")).sendKeys(lname);
		driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[1]/div[2]/div[2]/div[1]/div/div[2]/div[1]/input")).sendKeys(email);
		driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[1]/div[2]/div[2]/div[1]/div/div[3]/div[1]/div/input")).sendKeys(number);
		driver.findElement(By.xpath("//button[@class='dwebCommonstyles_ButtonBase-sc-112ty3f-10 GuestDetailsBlockstyles_CustomButton-sc-1rzm4ar-6 KETBj blGWwt']")).click();
	}
	public String bookingHotel(String first,String last,String validemail,String validnumber) throws InterruptedException {
		String fname,lname, email,number;
		fname=first;
		lname=last;
		email=validemail;
		number=validnumber;
		booking(fname, lname, email, number);
		
		
		return driver.getTitle();
		
		
	}
	
	public boolean bookingHotelinvalidemailid(String first,String last,String invalidemail,String validnumber) throws InterruptedException {
		String fname,lname, email,number;
		fname=first;
		lname=last;
		email=invalidemail;
		number=validnumber;
		booking(fname, lname, email, number);
		boolean actual=driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[1]/div[2]/div[2]/div[1]/div/div[2]/div[2]")).isDisplayed();
		return actual;
		
		
	}
	public boolean bookingHotelinvalidmobileno(String first,String last,String validemail,String invalidmobno) throws InterruptedException {
		String fname,lname, email,number;
		fname=first;
		lname=last;
		email=validemail;
		number=invalidmobno;
		booking(fname, lname, email, number);
		boolean actual=driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[1]/div[2]/div[2]/div[1]/div/div[3]/div[2]")).isDisplayed();
		return actual;
		
		
	}
		
		
	
	public boolean bookingHotelnoinput(String first,String last,String invalidemail,String invalidnumber) throws InterruptedException {
		String fname,lname, email,number;
		fname=first;
		lname=last;
		email=invalidemail;
		number=invalidnumber;
		booking(fname, lname, email, number);
		boolean actual=driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[1]/div[2]/div[2]/div[1]/div/div[3]/div[2]")).isDisplayed();
		return actual;
		
		
	}
		
	}