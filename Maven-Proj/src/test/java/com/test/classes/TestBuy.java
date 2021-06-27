package com.test.classes;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.pom.classes.BuyLongtermMO;
import com.pom.classes.HomePage;
import com.pom.classes.LoginPage;

import Utility.ConfigFileReader;
import testBrowserSetup.Pojo;


public class TestBuy {
	ConfigFileReader configFileReader;
	private WebDriver driver;
	
	BuyLongtermMO b;
	LoginPage lp;
	HomePage hp;
	@BeforeClass
	@Parameters("browser")
	public void beforeClass(String browser) throws InterruptedException
	{
		configFileReader= new ConfigFileReader();
		if(browser.equalsIgnoreCase("Chrome"))
		{
			driver=Pojo.openChromeBrowser();
		}
		else if(browser.equalsIgnoreCase("Firefox"))
		{
			driver=Pojo.openFirefoxBrowser()
		}
		driver.get(configFileReader.getApplicationUrl());
		driver.manage().window().maximize();
		lp=new LoginPage(driver);
		lp.sendUserID(configFileReader.getUserID());
		lp.sendPassword(configFileReader.getPassword());
		lp.clickLoginButton();
		Thread.sleep(1000);
		lp.enterPin(configFileReader.getPin());
		Thread.sleep(1000);
		lp.clickContinueButton();
		Thread.sleep(1000);
		b=new BuyLongtermMO(driver);
		b.enterStock("pvr");
		Thread.sleep(1000);
		b.clickAdd();
		Thread.sleep(1000);
		
		
	}
	@Test (priority=0)
	public void verifyBuyBtn() throws InterruptedException
	{
		b=new BuyLongtermMO(driver);
		SoftAssert soft=new SoftAssert();
		b.clickSearchBtn();
		b.enterStock("pvr");
		b.clickBuyBtn();
		Thread.sleep(3000);
		b.handleBuyWindow();
		boolean m=b.enabledMO();
		soft.assertEquals(m, true);
		b.enterQty("1");
		Thread.sleep(2000);
		
	}
	@Test (priority=1)
	public void verifyOrderPlaced() throws InterruptedException
	{
		hp=new HomePage(driver);
		SoftAssert soft=new SoftAssert();
		hp.clickOrders();
		Thread.sleep(1000);
		String expectedUrl="https://kite.zerodha.com/orders";
		String url=driver.getCurrentUrl();
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
		driver.quit();
	}

}
