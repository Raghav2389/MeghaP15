package com.test.classes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.pom.classes.BuyLongtermMO;
import com.pom.classes.HomePage;
import com.pom.classes.LoginPage;

import Utility.ConfigFileReader;
import Utility.ExcelUtil;

public class TestAddWatchList {
	ConfigFileReader configFileReader;
	private WebDriver driver;
	BuyLongtermMO b;
	LoginPage lp;
	HomePage hp;
	@BeforeClass
	public void beforeClass() throws InterruptedException
	{
		configFileReader= new ConfigFileReader();
		System.setProperty("webdriver.chrome.driver",configFileReader.getChromeDriverPath());
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);  
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
	
		
	}
	@DataProvider
	public Object[][] getStockData()
	{
		Object data[][]=ExcelUtil.getTestData();
		return data;
	}
	@Test(dataProvider="getStockData")
	public void verifyAddBtn(String stock) throws InterruptedException
	{
		b=new BuyLongtermMO(driver);
		Thread.sleep(2000);

		b.clickSearchBtn();
		b.enterStock(stock);
		Thread.sleep(1000);
		b.clickAdd();
		Thread.sleep(1000);
		
		
		
	}
	
	@AfterClass
	public void afterClass() throws InterruptedException
	{
		Thread.sleep(2000);
//		SoftAssert soft=new SoftAssert();
//		soft.assertAll();
		hp=new HomePage(driver);
		hp.logoutProcess();
		driver.quit();
	}


}
