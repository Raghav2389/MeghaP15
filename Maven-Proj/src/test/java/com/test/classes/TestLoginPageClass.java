package com.test.classes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pom.classes.LoginPage;

public class TestLoginPageClass {
	private WebDriver driver;
	LoginPage lp;
	@BeforeClass
	public void beforeClass()
	{
		System.setProperty("webdriver.chrome.driver", "D:\\downlods 20May2021\\chromedriver.exe");
		driver = new ChromeDriver();  // Upcasting
		driver.get("https://kite.zerodha.com/");
		driver.manage().window().maximize();
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
		Thread.sleep(2000);
		LoginPage lp=new LoginPage(driver);
		lp.sendUserID("");
		lp.sendPassword("");
		lp.clickLoginButton();
		Thread.sleep(3000);
		String p=lp.getPinLabel();
		Assert.assertEquals("PIN",p);
	}
	@AfterClass
	public void afterClass()
	{
		driver.close();
		
	}
	
}