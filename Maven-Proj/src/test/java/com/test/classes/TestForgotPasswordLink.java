package com.test.classes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.pom.classes.LoginPage;

public class TestForgotPasswordLink {
	WebDriver driver;
	LoginPage lp;
	@BeforeClass
	public void beforeClass() throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver", "D:\\\\downlods 20May2021\\\\chromedriver.exe");
		driver = new ChromeDriver();  // Upcasting
		driver.get("https://kite.zerodha.com/");
		driver.manage().window().maximize();
		Thread.sleep(1000);
	}
	@Test
	public void verifyForgotPasswordLink() throws InterruptedException
	{
		lp=new LoginPage(driver);
		lp.clickforgotPasswordLink();
		Thread.sleep(2000);
		String expectedUrl="https://kite.zerodha.com/forgot";
		String url=driver.getCurrentUrl();
		System.out.println(url);
		SoftAssert soft=new SoftAssert();
		soft.assertEquals(url, expectedUrl);
	}
	@AfterClass
	public void afterClass()
	{
		driver.close();
	}
	

}
