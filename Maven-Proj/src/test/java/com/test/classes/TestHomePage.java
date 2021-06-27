package com.test.classes;

import java.util.ArrayList;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.pom.classes.HomePage;
import com.pom.classes.LoginPage;

import Utility.ConfigFileReader;
import testBrowserSetup.Pojo;

public class TestHomePage {
	ConfigFileReader configFileReader;
	WebDriver driver;
	LoginPage lp;
	HomePage hp;
@BeforeClass
public void beforeClass()
{
	lp=new LoginPage(driver);
	hp=new HomePage(driver);
	configFileReader=new ConfigFileReader();
}
	@BeforeTest
	@Parameters("browser")
	public void beforeTest(String browser) throws InterruptedException
	{
		//configFileReader= new ConfigFileReader();
		if(browser.equalsIgnoreCase("Chrome"))
		{
			driver=Pojo.openChromeBrowser();
		}
		else if(browser.equalsIgnoreCase("Firefox"))
		{
			driver=Pojo.openFirefoxDriver();
		}
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
	@Test (priority=0)
	public void verifyURL() throws InterruptedException
	{
		Thread.sleep(1000);
		//hp=new HomePage(driver);
		String url=driver.getCurrentUrl();
		String expectedURL="https://kite.zerodha.com/dashboard";
		SoftAssert soft=new SoftAssert();
		soft.assertEquals(url, expectedURL);
		System.out.println(url);
	}
	@Test (priority=1)
	public void Tabs() throws InterruptedException
	{
		Thread.sleep(1000);
	//	hp=new HomePage(driver);
		String DashboardText=hp.getTextDashboard();
		String expectedTextD="Dashboard";
		SoftAssert soft=new SoftAssert();
		soft.assertEquals(DashboardText,expectedTextD);
		System.out.println(DashboardText);
		
		String OrdersText=hp.getTextOrders();
		String expectedTextO="Orders";
		soft.assertEquals(OrdersText,expectedTextO);
		System.out.println(OrdersText);
		
		String HoldingsText=hp.getTextHoldings();
		String expectedTextH="Holdings";
		soft.assertEquals(HoldingsText,expectedTextH);
		System.out.println(HoldingsText);
		
		String PositionsText=hp.getTextPositions();
		String expectedTextP="Positions";
		soft.assertEquals(PositionsText,expectedTextP);
		System.out.println(PositionsText);
		
		String FundsText=hp.getTextFunds();
		String expectedTextF="Funds";
		soft.assertEquals(FundsText,expectedTextF);
		System.out.println(FundsText);
		
		String AppsText=hp.getTextApps();
		String expectedTextA="Apps";
		soft.assertEquals(AppsText,expectedTextA);
		System.out.println(AppsText);
				
	}
	@Test (priority=2)
	public void verifyChartLink() throws InterruptedException
	{
		Thread.sleep(1000);
	//	hp=new HomePage(driver);
		SoftAssert soft=new SoftAssert();
		String NIFTY50Txt=hp.getNIFTY50Text();
		String expectedText="NIFTY 50";
		soft.assertEquals(NIFTY50Txt, expectedText);
		
		String SENSEXTxt=hp.getSENSEXText();
		String expectedS="SENSEX";
		soft.assertEquals(expectedS, expectedText);
		
		hp.clickNIFTY50Text();
		String expectedURL="https://kite.zerodha.com/chart/web/tvc/INDICES/NIFTY%2050/256265";
		String url=driver.getCurrentUrl();
		soft.assertEquals(url, expectedURL);
		
		Thread.sleep(1000);
		driver.navigate().back();
		
		Thread.sleep(1000);
		hp.clickSENSEXText();
		String expectedURLS="https://kite.zerodha.com/chart/web/tvc/INDICES/SENSEX/265";
		String urlS=driver.getCurrentUrl();
		soft.assertEquals(urlS, expectedURLS);
		
		driver.navigate().back();
		
				
	}
	
	@Test (priority=3)
	public void verifyEquityBlock() throws InterruptedException
	{
	//	hp=new HomePage(driver);
		SoftAssert soft=new SoftAssert();

		String EquityText=hp.getEquityText();
		String expectedTextE="Equity";
		soft.assertEquals(EquityText,expectedTextE);
		System.out.println(EquityText);
		
		String EMAText=hp.getEquityMargineAvailText();
		String expectedTextEMA="Margin available";
		soft.assertEquals(EMAText,expectedTextEMA);
		System.out.println(EMAText);
		
		String EMUText=hp.getEquityMarginUsedText();
		String expectedTextEMU="Margins used";
		soft.assertEquals(EMUText,expectedTextEMU);
		System.out.println(EMUText);
		
		String EMVSText=hp.getEQViewStatementText();
		String expectedTextEMVS="View statement";
		soft.assertEquals(EMVSText,expectedTextEMVS);
		System.out.println(EMVSText);
		
		hp.clickEQViewStatement();
		Thread.sleep(1000);
		ArrayList<String> addr=new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(addr.get(1));
		
		Thread.sleep(1000);
		String url=driver.getCurrentUrl();
		String expectedUrl="https://console.zerodha.com/funds/statement?segment=equity";
		soft.assertEquals(url, expectedUrl);
		System.out.println(url);
		
		
		driver.switchTo().window(addr.get(0));

		Thread.sleep(1000);
		String url1=driver.getCurrentUrl();
		System.out.println(url1);
	}
	
	@Test (priority=4)
	public void verifyHoldingsBlock() throws InterruptedException  
	{
		Thread.sleep(1000);
//		hp=new HomePage(driver);
		SoftAssert soft=new SoftAssert();
//		String HBTxt=hp.getHolidingsBlockText();
//		System.out.println(HBTxt);
		
		String PLTxt=hp.getHBPLText();
		String expectedHBPLText="P&L";
		soft.assertEquals(PLTxt,expectedHBPLText);
		System.out.println(PLTxt);
		
		String CVText=hp.getHBCVText();
		String expectedCV="Current value";
		soft.assertEquals(CVText, expectedCV);
		System.out.println(CVText);
		
		String INText=hp.getHBINText();
		String expectedIN="Investment";
		soft.assertEquals(INText, expectedIN);
		System.out.println(INText);
		
		boolean cv=hp.HBRadioCVEnabled();
		soft.assertEquals(cv, true);
		
		boolean iv=hp.HBRadioIVEnabled();
		soft.assertEquals(iv, true);
		
		boolean pl=hp.HBRadioPLEnabled();
		soft.assertEquals(pl, true);
	}
	@Test (priority=5)
	public void verifyMarketOverviewBlock()
	{
	//	hp=new HomePage(driver);
		String MOTxt=hp.getMOText();
		String expectedMO="Market overview ";
		SoftAssert soft=new SoftAssert();
		soft.assertEquals(MOTxt, expectedMO);
		System.out.println(MOTxt);
		
	}
	@AfterClass
	public void afterClass() throws InterruptedException
	{
		Thread.sleep(2000);
		SoftAssert soft=new SoftAssert();
		soft.assertAll();
//		hp=new HomePage(driver);
		hp.logoutProcess();
		driver.quit();
	}
	

}
