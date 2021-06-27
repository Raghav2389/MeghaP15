package com.pom.classes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	private WebDriver driver;
	
	@FindBy (xpath="//input[@id='userid']")
	private WebElement userID;
	
	@FindBy (xpath="//input[@id='password']")
	private WebElement password;
	
	@FindBy (xpath="//button[@type='submit']")
	private WebElement loginButton;
	
	@FindBy (xpath="//a[contains(text(),'Forgot')]")
	private WebElement forgotPassword;
	
	@FindBy (xpath="//img[@alt='Kite Android']")
	private WebElement kiteAndroid;
	
	@FindBy (xpath="//img[@alt='Kite iOS']")
	private WebElement kiteIOS;
	
	@FindBy (xpath="//a[@class=\"footer-logo\"]")
	private WebElement footerLogo;
	
	@FindBy (xpath="//a[contains(text(),'Signup')]")
	private WebElement signUp;
	
	@FindBy (xpath="//input[@id='pin']")
	private WebElement pin;
	
	@FindBy (xpath="//label[text()='PIN']")
	private WebElement pinTextBox;
	
	@FindBy (xpath="//button[@type='submit']")
	private WebElement continueButton;
	
	@FindBy (xpath="//a[contains(text(),'2FA?')]")
	private WebElement forgot2FA;
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void sendUserID(String data)
	{
		userID.sendKeys(data);
	}
	public void sendPassword(String data)
	{
		password.sendKeys(data);
	}
	public void clickLoginButton()
	{
		loginButton.click();
	}

	public void enterPin(String data)
	{
		pin.sendKeys(data);
	}
	public String getPinLabel()
	{
		String lable=pinTextBox.getText();
		return lable;
	}
	public void clickContinueButton()
	{
		continueButton.click();
	}
	public boolean verifyUserIDEnable()
	{
		boolean u=userID.isEnabled();
		return u;
	}
	public boolean verifyPasswordEnable()
	{
		boolean p=password.isEnabled();
		return p;
	}
	public boolean verifyLoginBtnEnabled()
	{
		boolean l=loginButton.isEnabled();
		return l;
	}
	public boolean verifyContinueBtnEnabled()
	{
		boolean c=continueButton.isEnabled();
		return c;
	}
	public boolean verifyPinBox()
	{
		boolean p=pin.isEnabled();
		return p;
	}
	public void clickforgotPasswordLink()
	{
		forgotPassword.click();
		
	}

}
