package com.pages;

import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

import com.base.TestBase;

public class LoginPage extends TestBase {

	public LoginPage() {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 20);
		
	}

	@FindBy(id = "get_sign_in")
	WebElement login;				//login button
	@FindBy(name = "phone")
	WebElement phone;				//mobile number
	@FindBy(xpath = "//input[@value='Continue']")
	WebElement Continue;			//continue button
	@FindBy( css= ".loginCont__btn")
	WebElement coontinue;
	@FindBy(xpath="//p[@class='gr-appendTop10 gr-font12 gr-redText']")
	WebElement invalidMobile;		//Invalid mobile number text
	@FindBy(xpath = "//input[@class='verifyOtpCont__otpFiled ']")
	WebElement otp;					//Entering otp blocks
	@FindBy(xpath = "//span[@class='robotoRegular gr-font12 gr-redText']")
	WebElement otpfailedtext;		//Invalid otp text
	@FindBy(xpath="//p[@class='successMsg__subTitle']") 
	WebElement successMessage;		//logged in successfull msg
	
	
	

	// public static WebDriverWait wait;
	

	public String inValidMobileLogin(String number) {					
		login.click();													//clicking login button
		phone.sendKeys(number);											//entering mobile number
		Continue.click();												//clicking continue button
		String invalidmobile=invalidMobile.getText();					//getting invalid mobile text
		return invalidmobile;											
	}
	
	public String validMobileInvalidOtp(String number) {
		driver.navigate().refresh();					
		login.click();													//clicking login button
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
	
	public String validMobileLogin(String number) {
		driver.navigate().refresh();
		login.click();													//clicking login button
		phone.clear();													//clearing input
		phone.sendKeys(number);											//entering mobile number		
		Continue.click();												//clicking continue button
		wait.until(ExpectedConditions.visibilityOf(otp));				//waiting for otp blocks
		System.out.println("Enter otp");
		String otpNumber = sc.next();									//taking user input otp
		otp.sendKeys(otpNumber);
		return driver.getTitle();
		
	}
}
	

