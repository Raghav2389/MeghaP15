package com.test.classes;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.pom.classes.HomePage;
import com.pom.classes.LoginPage;

import Utility.ConfigFileReader;
import Utility.ScreenShotUtility;
import testBrowserSetup.Pojo;


public class TestHome extends Pojo{
	ConfigFileReader configFileReader;
	WebDriver driver;
	LoginPage lp;
	HomePage hp;
	int testID;
	static ExtentTest test;
	static ExtentHtmlReporter reporter;
	
	
	@BeforeTest
	@Parameters("browser")
	public void launchBrowser(String browser) throws Exception {
		reporter = new ExtentHtmlReporter("test-output"+File.separator+"ExtendReport"+File.separator+"Extent.html");
		ExtentReports extend = new ExtentReports();
		extend.attachReporter(reporter);
		System.out.println("Before Test");
		
		if(browser.equalsIgnoreCase("Chrome"))
		{
			driver = Pojo.openChromeBrowser();
		}
		
		else if(browser.equalsIgnoreCase("Firefox"))
		{
			driver = Pojo.openFirefoxBrowser();
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	
	@BeforeClass
	public void createObject() {
		System.out.println("Before Class");
		lp = new LoginPage(driver);
		hp = new HomePage(driver);
		configFileReader=new ConfigFileReader();
	}
	
	@BeforeMethod
	public void loginToUserAccount() throws InterruptedException {
		driver.get(configFileReader.getApplicationUrl());
		driver.manage().window().maximize();
	//	lp=new LoginPage(driver);
		lp.sendUserID(configFileReader.getUserID());
		lp.sendPassword(configFileReader.getPassword());
		lp.clickLoginButton();
		Thread.sleep(1000);
		lp.enterPin(configFileReader.getPin());
		Thread.sleep(1000);
		lp.clickContinueButton();
		Thread.sleep(1000);
	}
	
	@Test
	public void verifydashboard() {
		testID = 100;
		System.out.println("Test");
		String link=driver.getCurrentUrl();
		if(link.equals("https://kite.zerodha.com/dashboard"))
		{
			System.out.println("Dashboard link is "+link);
		}
		else
		{
			Assert.fail();
		}
	}
	
	@AfterMethod
	public void logoutAccount(ITestResult result) throws InterruptedException, IOException {
		if(ITestResult.FAILURE == result.getStatus())
		{
			ScreenShotUtility.takeScreenshot(driver, testID);
		}
		Thread.sleep(10000);
		hp=new HomePage(driver);
		hp.logoutProcess();
	}
	
	@AfterClass
	public void clearObjects() {
		System.out.println("After Class");
		hp = null;
		lp = null;
	}
	
	@AfterTest
	public void closedBrowser() {
		System.out.println("After Test");
		driver.quit();
		driver = null;
		System.gc();
	}

}
