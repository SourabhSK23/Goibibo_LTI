package com.tetscases;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.pages.FlightSearching;

public class FlightSearchingTest extends FlightSearching{

	FlightSearching fs = new FlightSearching();
	
	@BeforeMethod
	public void setup()
	{
		initialization();
	}
	
	@BeforeTest
	public void Reportsetup() {
		ExtentReportSetup();
	}
	
	//validating searching by giving valid FROM and valid DESTINATION
	@Test(priority = 6)
	public void validatingFlightSearchTest()
	{
		ExtentTest test = extent.createTest("GoibiboFlightSearchTestCase");
		String validfrom = prop.getProperty("validfrom");
		String validdestination = prop.getProperty("validdestination");
		String expected = "VIEW FARES";
		String actual = fs.validateFlightSearching(validfrom,validdestination);
		Assert.assertEquals(actual, expected);
		test.log(Status.PASS, "GoibiboFlightSearchTestCasePassed");
	}
	
	//validating searching by giving invalid FROM and invalid DESTINATION
	@Test(priority = 7)
	public void validatingInvalidSearchTest()
	{
		ExtentTest test = extent.createTest("GoibiboInvalidFlightSearchTestCase");
		String invalidfrom = prop.getProperty("invalidfrom");
		String invaliddestination = prop.getProperty("invaliddestination");
		String expected = "Please enter a valid Source";
		String actual = fs.invalidFlightSearching(invalidfrom,invaliddestination);
		Assert.assertEquals(actual, expected);
		test.log(Status.PASS, "GoibiboInvalidFlightSearchTestCasePassed");
	}
	
	//validating searching by giving only valid FROM
	@Test(priority = 8)
	public void validatingFromSearchTest()
	{
		ExtentTest test = extent.createTest("GoibibovalidFlightSearch_FROMTestCase");
		String validfrom = prop.getProperty("validfrom");
		String expected = "Please enter a valid Destination";
		String actual = fs.invalidFlightSearching(validfrom," ");
		Assert.assertEquals(actual, expected);
		test.log(Status.PASS, "GoibibovalidFlightSearch_FROMTestCasePassed");
	}
	
	//validating searching by giving only valid DESTINATION
	@Test(priority = 9)
	public void validatingDestinationTest()
	{
		ExtentTest test = extent.createTest("GoibibovalidFlightSearch_DESTINATIONTestCase");
		String validdestination = prop.getProperty("validdestination");
		String expected = "Please enter a valid Source";
		String actual = fs.invalidFlightSearching(" ",validdestination);
		Assert.assertEquals(actual, expected);
		test.log(Status.PASS, "GoibibovalidFlightSearch_DESTINATIONTestCasePassed");
	}
	
	//validating searching by giving same credentials to FROM and DESTINATION
	@Test(priority = 10)
	public void validingSameSearchTest()
	{
		ExtentTest test = extent.createTest("GoibibovalidFlightSearch_FROM_DESTINATIONTestCase");
		String validfrom = prop.getProperty("validfrom");
		String validdestination = prop.getProperty("validfrom");
		String expected = "Source and Destination cannot be same";
		String actual = fs.invalidFlightSearching(validfrom,validdestination);
		Assert.assertEquals(actual, expected);
		test.log(Status.PASS, "GoibibovalidFlightSearch_FROM_DESTINATIONTestCasePassed");
	}
	
	
	
	//validating searching by giving nothing
	@Test(priority = 11)
	public void validatingSearchTest()
	{
		ExtentTest test = extent.createTest("GoibibovalidFlightSearch_NOINPUTSTestCase");
		String expected = "Please enter a valid Source";
		String actual = fs.invalidFlightSearching(" "," ");
		Assert.assertEquals(actual, expected);
		test.log(Status.PASS, "GoibibovalidFlightSearch_NOINPUTSTestCasePassed");
	}
	
	// validating BOOK button is working or not
	@Test(priority = 12)
	public void validatingBookButtonTest()
	{
		ExtentTest test = extent.createTest("GoibiboBookButtonTestCase");
		String expected = "TICKET DETAILS";
		String actual = fs.validateBookButton();
		Assert.assertEquals(actual, expected);
		test.log(Status.PASS, "GoibiboBookButtonTestCasePassed");
	}
	
	
	//validating Guest personal details page by giving valid email 
	@Test(priority = 13)
	public void validatingPersonalDeatilsTest()
	{
		ExtentTest test = extent.createTest("GoibiboGuestDetailsTestCase");
		String firstname = prop.getProperty("firstname");
		String lastname = prop.getProperty("lastname");
		String validemail = prop.getProperty("validemail");
		String validmobileno = prop.getProperty("validmobileno");
		String expected = "ADDONS";
		String actual = fs.validatingPersonalDetails(firstname,lastname,validemail,validmobileno);
		Assert.assertEquals(actual, expected);
		test.log(Status.PASS, "GoibiboBookButtonTestCasePassed");
		
	}
	
	
	//validating Guest personal details page by giving invalid email
	@Test(priority = 14)
	public void validatingInvalidDetailsTest()
	{
		ExtentTest test = extent.createTest("GoibiboInvalidGuestDetailsTestCase");
		String firstname = prop.getProperty("firstname");
		String lastname = prop.getProperty("lastname");
		String invalidemail = prop.getProperty("invalidemail");
		String validmobileno = prop.getProperty("validmobileno");
		String expected = "Please enter a valid Email ID";
		String actual = fs.validatingInvalidPersonalDetails(firstname,lastname,invalidemail,validmobileno);
		Assert.assertEquals(actual, expected);
		test.log(Status.PASS, "GoibiboInvalidGuestDetailsTestCasePassed");
		
	}
	
	
	//validating Payment page is Displaying
	@Test(priority = 15)
	public void validatingPaymentPageTest()
	{
		ExtentTest test = extent.createTest("GoibiboPaymentPageTestCase");
		String firstname = prop.getProperty("firstname");
		String lastname = prop.getProperty("lastname");
		String validemail = prop.getProperty("validemail");
		String validmobileno = prop.getProperty("validmobileno");
		String expected = "PAYMENT DETAILS";
		String actual = fs.PaymentPage(firstname,lastname,validemail,validmobileno);
		Assert.assertEquals(actual, expected);
		test.log(Status.PASS, "GoibiboPaymentPageTestCasePassed");
	}
	
	
	@AfterTest
	public void GenerateReport() {
		CloseReportSetup();
	}
	
	
	@AfterMethod
	public void setupend()
	{
		teardown();
	}
}
