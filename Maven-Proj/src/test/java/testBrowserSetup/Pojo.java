package testBrowserSetup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import Utility.ConfigFileReader;

public class Pojo {

	public static WebDriver openChromeBrowser()
	{
		ConfigFileReader configFileReader= new ConfigFileReader();
		System.setProperty("webdriver.chrome.driver", configFileReader.getChromeDriverPath());
		WebDriver driver=new ChromeDriver();
		return driver;
	}
	public static WebDriver openFirefoxBrowser()
	{
		ConfigFileReader configFileReader= new ConfigFileReader();
		System.setProperty("webdriver.gecko.driver", configFileReader.getFirefoxDriverPath());
		WebDriver driver=new FirefoxDriver();
		return driver;
	}

}
