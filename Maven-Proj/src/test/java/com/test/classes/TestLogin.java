package com.test.classes;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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

public class TestLogin {
	ConfigFileReader configFileReader;
	private WebDriver driver;
	LoginPage lp;
	HomePage hp;
	int testID;
	static ExtentTest test;
	static ExtentHtmlReporter reporter;
	
	@BeforeTest
	@Parameters("browser")
	public void launchBrowser(String browser) throws Exception {
		reporter = new ExtentHtmlReporter("test-output"+File.separator+"ExtendReport"+File.separator+"extendReport.html");
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
		configFileReader=new ConfigFileReader();
		lp = new LoginPage(driver);
		hp = new HomePage(driver);
	}
	@BeforeMethod
	public void loginToUserAccount() {
		driver.get("https://kite.zerodha.com/");
		
	}
	@Test (priority=0)
	public void verifyUserIDTextbox() throws InterruptedException
	{
		Thread.sleep(1000);
		LoginPage lp=new LoginPage(driver);
		boolean u=lp.verifyUserIDEnable();
		Assert.assertEquals(true, u);
		System.out.println("UserIDTextBox is enabled – Assert passed");
		
	}
	
	@Test (priority=1)
	public void verifyPasswordTextbox() throws InterruptedException
	{
		Thread.sleep(2000);
		LoginPage lp=new LoginPage(driver);
		boolean p=lp.verifyPasswordEnable();
		Assert.assertEquals(true, p);
		System.out.println("PasswordTextBox is enabled – Assert passed");
		
	}
	@Test (priority=2)
	public void verifyLoginButton() throws InterruptedException
	{
		Thread.sleep(2000);
		LoginPage lp=new LoginPage(driver);
		boolean l=lp.verifyLoginBtnEnabled();
		Assert.assertEquals(true, l);
		System.out.println("LoginButton is enabled – Assert passed");
	}
	@Test (priority=3)
	public void verifyCredentials() throws InterruptedException
	{
		testID = 100;
		Thread.sleep(2000);
		LoginPage lp=new LoginPage(driver);
		lp.sendUserID(configFileReader.getUserID());
		lp.sendPassword(configFileReader.getPassword());
		lp.clickLoginButton();
		Thread.sleep(3000);
		String p=lp.getPinLabel();
		Assert.assertEquals("PIN",p);
	}
	@AfterMethod
	public void logoutAccount(ITestResult result) throws InterruptedException, IOException {
		if(ITestResult.FAILURE == result.getStatus())
		{
			ScreenShotUtility.takeScreenshot(driver, testID);
		}
		Thread.sleep(10000);
		System.out.println("After Method");
		
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
