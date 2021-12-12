package com.tetscases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.base.TestBase;
import com.pages.Hotelpage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HotelTest extends TestBase{
	Hotelpage BK=new Hotelpage();
	@BeforeMethod
	public void setUp() {

	initialization();
	 driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/header/ul/li[2]/a")).click();

	BK =new Hotelpage();
	}
	@BeforeTest
	public void Reportsetup() {
		ExtentReportSetup();
	
}
	@Test(priority =1)
	public void valiadteTitle() {
		ExtentTest test = extent.createTest("GoibiboHotelTitle");
		String actualTitle = BK.validateUserinHotelPage();
		String expectedTitle = "Online Hotel Booking | Book Cheap, Budget and Luxury Hotels -Goibibo";
		Assert.assertEquals(actualTitle, expectedTitle);
		test.log(Status.PASS, "GoibiboHotelTitleTestCasePassed");
	}

	
	@Test(priority =2)
	public void searchfieldTest() {
		ExtentTest test = extent.createTest("GoibiboHotelSearchfield");
		boolean a=BK.searchfield();
		Assert.assertTrue(a);
		test.log(Status.PASS, "GoibiboHotelSearchfield");
	}
	
	@Test(priority =3)
	public void valiadteinputTest() throws InterruptedException   {
		ExtentTest test = extent.createTest("GoibiboHotelinputTest");
		String validloc= prop.getProperty("validloc");
		String a= (String) BK.validateInput(validloc);
		String b="Results";
		Assert.assertEquals(a,b);
		test.log(Status.PASS, "GoibiboHotelvalidateinputTest");
	}
	@Test (priority =4)
	public void invaliadteinputTest() {
		ExtentTest test = extent.createTest("GoibiboHotelinvalidinput");
		String url2=driver.getCurrentUrl();
		String invalidloc=prop.getProperty("invalidloc");
		String url1=BK.invalidInput(invalidloc);
		Assert.assertEquals(url1, url2);
		test.log(Status.PASS, "GoibiboHotelinvalidinput");
		
	}
	@Test(priority =5)
	public void noinputTest() {
		ExtentTest test = extent.createTest("GoibiboHotelnoinput");
		String url2=driver.getCurrentUrl();
		String url1=BK.noInput();
		Assert.assertEquals(url1, url2);
		test.log(Status.PASS, "GoibiboHotelnoinput");
		
	}
	@Test(priority =6)
	public void selecthotel() throws InterruptedException  {
		ExtentTest test = extent.createTest("GoibiboHotelSelected");
		String title= BK.selecthotel();
		String title2 ="Book Cheap Flights, Air Tickets, Hotels, Bus & Holiday Package at Goibibo";
		Assert.assertEquals(title, title2);
		test.log(Status.PASS, "GoibibohotelSearchTestCasePassed");
		
	}
	@Test(priority =7)
	public void bookinghotelTest() throws InterruptedException
	{
		ExtentTest test = extent.createTest("GoibiboHotelBooking");
		String first=prop.getProperty("firstname");
		String last=prop.getProperty("lastname");
		String validemail=prop.getProperty("validemail");
		String mobileno=prop.getProperty("mobileno");
		String expected=BK.bookingHotel(first,last,validemail,mobileno);
		String actual="Book Cheap Flights, Air Tickets, Hotels, Bus & Holiday Package at Goibibo";
		Assert.assertEquals(actual, expected);
		test.log(Status.PASS, "GoibiboBookingHotelTestCasePassed");
	}
	@Test(priority=8)
	public void bookinghotelTestwithinvalidemail() throws InterruptedException
	{
		ExtentTest test = extent.createTest("GoibiboHotelbooking");
		String first=prop.getProperty("firstname");
		String last=prop.getProperty("lastname");
		String invalidemail=prop.getProperty("invalidemail");
		String mobileno=prop.getProperty("validmobileno");
		boolean expected=BK.bookingHotelinvalidemailid(first,last,invalidemail,mobileno);
		Assert.assertTrue(expected);
		test.log(Status.PASS, "GoibibobookinghotelTestwithinvalidemaiTestCasePassed");
		
	}
	@Test(priority=9)
	public void bookinghotelTestwithinvalidnumber() throws InterruptedException {
		ExtentTest test = extent.createTest("GoibiboHotelTitle");
		String first=prop.getProperty("firstname");
		String last=prop.getProperty("lastname");
		String validemail=prop.getProperty("validemail");
		String invalidmobno=prop.getProperty("invalidmobileno");
		boolean expected=BK.bookingHotelinvalidmobileno(first,last,validemail,invalidmobno);
		Assert.assertTrue(expected);
		test.log(Status.PASS, "GoibibobookinghotelTestwithinvalidnumberTestCasePassed");
	}
	@Test(priority=10)
	public void bookinghotelTestwithnoinput() throws InterruptedException {
		ExtentTest test = extent.createTest("GoibiboHotelTitle");
		boolean expected=BK.bookingHotelnoinput( " " ," "," "," ");
		Assert.assertTrue(expected);
		test.log(Status.PASS, "GoibibobookinghotelTestwithnoinpuTestCasePassed");
	}
	@AfterTest
	public void GenerateReport()
	{
		CloseReportSetup();
	}
	

	@AfterMethod
	public void closesetup() {
		// TODO Auto-generated method stub
		driver.quit();
	}
}