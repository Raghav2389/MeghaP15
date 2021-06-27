package com.pom.classes;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;
	
	@FindBy (xpath="//span[text()='Dashboard']")
	private WebElement Dashboard;
	
	@FindBy (xpath="//span[text()='Orders']")
	private WebElement Orders;
		
	@FindBy (xpath="//span[text()='Holdings']")
	private WebElement Holdings;
	
	@FindBy (xpath="//span[text()='Positions']")
	private WebElement Positions;
	
	@FindBy (xpath="//span[text()='Funds']")
	private WebElement Funds;
	
	@FindBy (xpath="//span[text()='Apps']")
	private WebElement Apps;
	
	@FindBy (xpath="//span[@class='user-id']")
	private WebElement ProfileUserid;
	
	@FindBy (xpath="//a[text()=' Logout']")
	private WebElement LogOut;
		
	@FindBy (xpath="//input[@id='search-input']")
	private WebElement Search;
	
	@FindBy (xpath="(//span[@class='tradingsymbol link-chart'])[1]")
	private WebElement NIFYChartLink;
	
	@FindBy (xpath="(//span[@class='tradingsymbol link-chart'])[2]")
	private WebElement SENSEXChartLink;
	
	@FindBy (xpath="//ul[@class='marketwatch-selector list-flat']")
	private WebElement MWatchSelector;
	
	@FindBy (xpath="//span[text()='equity']")
	private WebElement Equity;
	
	@FindBy (xpath="(//div[text()='Margin available'])[1]")
	private WebElement EQMargineAvail;
	
	@FindBy (xpath="(//span[text()='Margins used'])[1]")
	private WebElement EQMarginUsed;
	
	@FindBy (xpath="(//span[text()='View statement'])[1]")
	private WebElement EQViewStatement;
	
	@FindBy (xpath="//div[contains(text(),'Holdings')]")
	private WebElement HoldingsBlockText;
	
	@FindBy (xpath="//span[contains(text(),'P&L')]")
	private WebElement HB_PL;
	
	@FindBy (xpath="//span[text()='Current value']")
	private WebElement HB_CV;
	
	@FindBy (xpath="//span[text()='Investment']")
	private WebElement HB_IN;
	
	@FindBy (xpath="(//label[@class='su-radio-label'])[1]")
	private WebElement HB_RadioCV;
	
	@FindBy (xpath="(//input[@class='su-radio'])[2]")
	private WebElement HB_RadioIV;
	
	@FindBy (xpath="(//input[@class='su-radio'])[3]")
	private WebElement HB_RadioPL;
	
	@FindBy (xpath="//h2[contains(text(),'Market overview')]")
	private WebElement MO_Text;
	
	@FindBy (xpath="//span[contains(text(),'NIFTY 50')]")
	private WebElement NIFTY50Text;
	
	@FindBy (xpath="//span[contains(text(),'SENSEX')]")
	private WebElement SENSEX;
	
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public String getTextDashboard()
	{
		String d=Dashboard.getText();
		return d;
	}
	public String getTextOrders()
	{
		String o=Orders.getText();
		return o;
	}
	public String getTextHoldings()
	{
		String h=Holdings.getText();
		return h;
	}
	public String getTextPositions()
	{
		String p=Positions.getText();
		return p;
	}
	public String getTextFunds()
	{
		String f=Funds.getText();
		return f;
	}
	public String getTextApps()
	{
		String a=Apps.getText();
		return a;
	}
	public String getEquityText()
	{
		String e=Equity.getText();
		return e;
	}
	public String getEquityMargineAvailText()
	{
		String EM=EQMargineAvail.getText();
		return EM;
	}
	public String getEquityMarginUsedText()
	{
		String EMU=EQMarginUsed.getText();
		return EMU;
	}
	public String getEQViewStatementText()
	{
		String EQ_VS=EQViewStatement.getText();
		return EQ_VS;
	}
	public String getHolidingsBlockText()
	{
		String HBText=HoldingsBlockText.getText();
		
		return HBText;
	}
	public String getHBPLText()
	{
		String PLText=HB_PL.getText();
		return PLText;
	}
	public String getHBCVText()
	{
		String CVText=HB_CV.getText();
		return CVText;
	}
	public String getHBINText()
	{
		String HBINText=HB_IN.getText();
		return HBINText;
	}
	public String getNIFTY50Text()
	{
		String NIFTY50=NIFTY50Text.getText();
		return NIFTY50;
	}
	public String getSENSEXText()
	{
		String SENSEXText=SENSEX.getText();
		return SENSEXText;
	}
	public boolean HBRadioCVEnabled()
	{
		boolean cv=HB_RadioCV.isSelected();
		return cv;
	}
	public boolean HBRadioIVEnabled()
	{
		boolean iv=HB_RadioIV.isSelected();
		return iv;
	}
	public boolean HBRadioPLEnabled()
	{
		boolean pl=HB_RadioPL.isSelected();
		return pl;
	}
	public String getMOText()
	{
		String MOText=MO_Text.getText();
		return MOText;
	}
	public void clickDashboard()
	{
		Dashboard.click();
	}
	public void clickOrders()
	{
		Orders.click();
	}
	public void clickHoldings()
	{
		Holdings.click();
	}
	public void clickPositions()
	{
		Positions.click();
	}
	public void clickFunds()
	{
		Funds.click();
	}
	public void clickApps()
	{
		Apps.click();
	}
	public void clickEQViewStatement()
	{
		EQViewStatement.click();
	}
	public void clickNIFTY50Text()
	{
		NIFTY50Text.click();
	}
	public void clickSENSEXText()
	{
		SENSEX.click();
	}
	public void logoutProcess() throws InterruptedException
	{
		Actions action=new Actions(driver);
		action.moveToElement(ProfileUserid).click().perform();
		Thread.sleep(2000);
		action.moveToElement(LogOut).click().perform();
	}
	
	
	
	
}
