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

import com.pages.SignUpPage;

public class SignupTest extends TestBase {
	
	
	SignUpPage sp ;

	
	
	@BeforeMethod
	public void setup() {
		initialization();
	sp= new SignUpPage();
	}
	
	@BeforeTest
	public void Reportsetup() {
		ExtentReportSetup();
	}
	
	
	@Test(priority = 0)
	public void ValidateSignUpwithInvalidMobile() {
		ExtentTest test = extent.createTest("GoibiboSignUpwithInvalidMobileTestCase");
		String number=prop.getProperty("invalidnumber");
		String actualText = sp.inValidMobileSignUp(number);
		String expectedTest = "Please enter a 10 digit mobile number";
		if(actualText.contentEquals(expectedTest))
			Assert.assertTrue(true);
		else 
			Assert.assertTrue(false);
		test.generateLog(Status.PASS, "ValidateTestCaseSignUpwithInvalidMobilePassed");
		
	}
	
	
	@Test(priority = 1)
	public void validateSignUpwithValidMobileInvalidOtp() {
		ExtentTest test = extent.createTest("GoibiboSignUpwithValidMobileInvalidOtpTestCase");
		String number=prop.getProperty("validnumber1");
		String actualText = sp.validMobileInvalidOtp(number);
		String expectedTest = "Please enter a valid OTP";
		if(actualText.contentEquals(expectedTest))
			Assert.assertTrue(true);
		else 
			Assert.assertTrue(false);
		test.generateLog(Status.PASS, "ValidateTestCaseSignUpwithInvalidOtpPassed");
	}
	
	
	@Test(priority = 2)
	public void ValidateSignUpwithValidMobileValidOtp() {
		ExtentTest test = extent.createTest("GoibiboSignUpwithValidMobileValidOtpTestCase");
		String number=prop.getProperty("validnumber2");
		String fullName=prop.getProperty("fullname");
		String email=prop.getProperty("email");
		String actualText = sp.validMobileSignUp(number,fullName,email);
		String expectedText = "You have successfully logged in";
		if(actualText.contentEquals(expectedText))
			Assert.assertTrue(true);
		else 
			Assert.assertTrue(false);
		test.generateLog(Status.PASS, "ValidateTestCaseSignUpwithValidOtpPassed");
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

