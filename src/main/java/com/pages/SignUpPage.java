package com.pages;

import java.io.File;

import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

import com.base.TestBase;

public class SignUpPage extends TestBase {

	public SignUpPage() {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 20);
		
	}

	@FindBy(id = "get_sign_in")
	WebElement signup;								
	@FindBy(name = "phone")
	WebElement phone;								
	@FindBy(xpath = "//input[@value='Continue']")
	WebElement Continue;							
	@FindBy( css= ".loginCont__btn")
	WebElement coontinue;							
	@FindBy(xpath="//p[@class='gr-appendTop10 gr-font12 gr-redText']")
	WebElement invalidMobile;						
	@FindBy(xpath = "//input[@class='verifyOtpCont__otpFiled ']")
	WebElement otp;									
	@FindBy(xpath = "//span[@class='robotoRegular gr-font12 gr-redText']")
	WebElement otpfailedtext;						
	@FindBy(xpath="//p[@class='successMsg__subTitle']") 
	WebElement successMessage;						
	@FindBy(name = "fullName")
	WebElement fullName;							
	@FindBy(name = "email")
	WebElement email;							
	@FindBy(className = "loginCont__btn")
	WebElement login;	

	

	// public static WebDriverWait wait;
	
	public String inValidMobileSignUp(String number) {					
		signup.click();													//clicking signup button
		phone.sendKeys(number);											//entering mobile number
		Continue.click();												//clicking continue button
		String invalidmobile=invalidMobile.getText();					//getting invalid mobile text
		
		return invalidmobile;											
	}
	
	public String validMobileInvalidOtp(String number) {
		driver.navigate().refresh();					
		signup.click();													//clicking signup button
		phone.sendKeys(number);											//entering mobile number
		coontinue.click();												//clicking continue button	
		wait.until(ExpectedConditions.visibilityOf(otp));				//waiting for otp blocks	
		System.out.println("Enter otp");									
		String otpNumber = sc.next();									//taking user input otp	
		otp.sendKeys(otpNumber);											
		wait.until(ExpectedConditions.visibilityOf(otpfailedtext));			
		String wrongOtp=otpfailedtext.getText();						//getting otp failed message
		return wrongOtp;
		
	}
	
	public String validMobileSignUp(String number, String fullname, String Email) {
		driver.navigate().refresh();
		signup.click();													//clicking signup button
		phone.clear();													//clearing input
		phone.sendKeys(number);											//entering mobile number		
		Continue.click();												//clicking continue button
		wait.until(ExpectedConditions.visibilityOf(otp));				//waiting for otp blocks
		System.out.println("Enter otp");
		String otpNumber = sc.next();									//taking user input otp
		otp.sendKeys(otpNumber);
		//wait.until(ExpectedConditions.visibilityOf(fullName));
		String successmessage=successMessage.getText();					//gettting successfull signup text
		//fullName.sendKeys(fullname);									//sending fullname
		email.sendKeys(Email);											//sending email
		//login.click();													//clicking on login
		return successmessage;
	}
}
	



