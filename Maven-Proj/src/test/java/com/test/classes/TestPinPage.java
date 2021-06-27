package com.test.classes;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.pom.classes.HomePage;
import com.pom.classes.LoginPage;
import testBrowserSetup.Pojo;

import Utility.ConfigFileReader;

public class TestPinPage extends Pojo {
	ConfigFileReader configFileReader;
	WebDriver driver;
	LoginPage lp;
	HomePage hp;
	@BeforeTest
	@Parameters("browser")
	public void beforeTest(String browser)
	{
		configFileReader= new ConfigFileReader();
		if(browser.equalsIgnoreCase("Chrome"))
		{
			driver = openChromeBrowser();
		}
		
		else if(browser.equalsIgnoreCase("Firefox"))
		{
			driver = openFirefoxDriver();
		}

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

	}
	
	@BeforeClass
	@Parameters("browser")
	public void beforeClass(String browser)
	{
		configFileReader= new ConfigFileReader();
		

		driver.get(configFileReader.getApplicationUrl());
		driver.manage().window().maximize();
		lp=new LoginPage(driver);
		lp.sendUserID(configFileReader.getUserID());
		lp.sendPassword(configFileReader.getPassword());
		lp.clickLoginButton();
	}
	@Test (priority=0)
	public void verifyPinTextBox() throws InterruptedException
	{
		Thread.sleep(1000);
		lp=new LoginPage(driver);
		boolean b=lp.verifyPinBox();
		Assert.assertEquals(true, b);
		System.out.println("PinTextBox is enabled – Assert passed");
		lp.enterPin(configFileReader.getPin());
	}
	@Test (priority=1)
	public void verifyContinueButton() throws InterruptedException 
	{
		Thread.sleep(2000);
		lp=new LoginPage(driver);
		boolean cb=lp.verifyContinueBtnEnabled();
		SoftAssert soft=new SoftAssert();
		soft.assertEquals(true, cb);
		System.out.println("Continue Button is enabled – Assert passed");
		lp.clickContinueButton();
		Thread.sleep(2000);
		String url=driver.getCurrentUrl();
		String expectedUrl="https://kite.zerodha.com/dashboard";
		soft.assertEquals(url, expectedUrl);
		System.out.println(url);
	}
	@AfterClass
	public void afterClass() throws InterruptedException
	{
		Thread.sleep(2000);
		SoftAssert soft=new SoftAssert();
		soft.assertAll();
		hp=new HomePage(driver);
		hp.logoutProcess();
		driver.close();
	}
	@AfterTest
	public void closedBrowser() {
		System.out.println("After Test");
		driver.quit();
		
	}

}
