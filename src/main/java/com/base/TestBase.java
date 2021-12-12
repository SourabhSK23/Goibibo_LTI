package com.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	
	public static  WebDriver driver;
	public static WebDriverWait wait;
	public Scanner sc = new Scanner(System.in);
	
	
	
	
	public static Properties prop;
	public static ExtentReports extent;
	public static ExtentSparkReporter reporter;
	
	public TestBase() {
		prop =new Properties();
		try {
		FileInputStream fis=new FileInputStream("./src/main/java/com/config/config.properties");
			prop.load(fis);
	
		}
		catch(FileNotFoundException e) {
			e.printStackTrace(System.err);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public static void initialization() {
		String browser=prop.getProperty("browser");
		String url=prop.getProperty("url");
		
		if(browser.equals("chrome")) {
			ChromeOptions options = new ChromeOptions();
			//options.addArguments("--headless");
		//	options.addArguments("disable-gpu");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);	
		}
		
		else if(browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		
		else if(browser.equals("ie"))
		{
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		}
	
		driver.get(url);  
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	
	public void ExtentReportSetup() {
		String reportpath = System.getProperty("user.dir") + "/extentreport/index1.html";
		reporter = new ExtentSparkReporter(reportpath);
		extent = new ExtentReports();
		extent.attachReporter(reporter);
	}
	
	
	public void CloseReportSetup() {
		extent.flush();
	}
	
		public static void teardown() {
		driver.quit();
	}
}
