package com.pom.classes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BuyLongtermMO {
	private WebDriver driver;
	
	@FindBy (xpath="//input[@id='search-input']")
	private WebElement Search;
	
	@FindBy (xpath="//span[@class=\"icon icon-search\"]")
	private WebElement SearchBtn;
	
	@FindBy (xpath="//li[@class='search-result-item selected']")
	private WebElement SearchItem;
	
	@FindBy (xpath="//li[@class='search-result-item selected isadded']")
	private WebElement AddedItem;
	
	@FindBy (xpath="//button[@class='button-green']")
	private WebElement addStock;
	
	@FindBy (xpath="//button[@class='button-blue']")
	private WebElement buyBtn;
	
	@FindBy (xpath="//button[@class='button-orange sell']")
	private WebElement sellBtn;
	
	@FindBy (xpath="//div[@class='vddl-draggable instrument up index0']")
	private WebElement draggable1;
	
	@FindBy (xpath="//form[@class='order-window layer-2 place buy']")
	private WebElement buyWindow;
	
	@FindBy (xpath="(//form[@class='order-window layer-2 place buy']//input)[8]")
	private WebElement qty;
	@FindBy (xpath="//input[@label='Market']")
	private WebElement MO;
	
	@FindBy (xpath="//button/span[text()='Buy']")
	private WebElement buy;
	
	public BuyLongtermMO(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	public void clickSearchBtn()
	{
		SearchBtn.click();
	}
	public void enterStock(String stk)
	{
		Search.click();
		Search.sendKeys(stk);
	}
	public void clickAdd() throws InterruptedException
	{
		Actions action=new Actions(driver);
		action.moveToElement(SearchItem).perform();
		Thread.sleep(1000);
		action.moveToElement(addStock).click().perform();
	}
	public void clickBuyBtn() throws InterruptedException
	{
		Actions action=new Actions(driver);
		action.moveToElement(AddedItem).perform();
		Thread.sleep(1000);
		action.click(buyBtn).perform();
		
	}
	public void clickSellBtn()
	{
		sellBtn.click();
	}
	public void handleBuyWindow()
	{
		Actions action=new Actions(driver);
		action.moveToElement(buyWindow).click();
		
	}
	public boolean enabledMO()
	{
		boolean m=MO.isSelected();
		return m;
	}
	public void enterQty(String q)
	{
		qty.sendKeys(q);
	}
	public void clickBuy()
	{
		buy.click();
	}
}
