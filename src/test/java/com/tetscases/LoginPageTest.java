package com.tetscases;

import java.util.Scanner;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.base.TestBase;
import com.pages.LoginPage;

public class LoginPageTest extends TestBase {
	
	
	
	LoginPage lp;
	
	
	@BeforeMethod
	public void setup() {
		initialization();
	lp= new LoginPage();
	}
	
	@BeforeTest
	public void Reportsetup() {
		ExtentReportSetup();
	}
	
	
	@Test(priority = 3)
	public void ValidateLoginwithInvalidMobile() {
		ExtentTest test = extent.createTest("GoibiboLoginwithInvalidMobileTestCase");
		String number=prop.getProperty("invalidnumber");
		String actualText = lp.inValidMobileLogin(number);
		String expectedTest = "Please enter a 10 digit mobile number";
		if(actualText.contentEquals(expectedTest))
			Assert.assertTrue(true);
		else 
			Assert.assertTrue(false);
		test.generateLog(Status.PASS, "ValidateTestCaseLoginwithInvalidMobilePassed");
		
	}
	
	
	@Test(priority = 4)
	public void validateLoginwithValidMobileInvalidOtp() {
		ExtentTest test = extent.createTest("GoibiboLoginTestCase");
		String number=prop.getProperty("signinvalidnumber1");
		String actualText = lp.validMobileInvalidOtp(number);
		String expectedTest = "Please enter a valid OTP";
		if(actualText.contentEquals(expectedTest))
			Assert.assertTrue(true);
		else 
			Assert.assertTrue(false);
		test.generateLog(Status.PASS, "ValidateTestCaseLoginwithInvalidOtpPassed");
	}
	
	
	@Test(priority = 5)
	public void ValidateLoginwithValidMobileValidOtp() {
		ExtentTest test = extent.createTest("GoibiboLoginTestCase");
		String number=prop.getProperty("signinvalidnumber2");
		String actual = lp.validMobileLogin(number);
		String expected = "Goibibo - Best Travel Website. Book Hotels, Flights, Trains, Bus and Cabs with upto 50% off";
		Assert.assertEquals(actual,expected);
		test.generateLog(Status.PASS, "ValidateTestCaseLoginwithInvalidOtpPassed");
	}
	
	
@AfterTest
public void GenerateReport() {
CloseReportSetup();
}
	
	@AfterMethod
	public void closeSetup() {
		teardown();
	}
	
}

