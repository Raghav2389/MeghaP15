package testBrowserSetup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import Utility.ConfigFileReader;

public class Pojo {

	public static WebDriver openChromeBrowser()
	{
		ConfigFileReader configFileReader= new ConfigFileReader();
		System.setProperty("webdriver.chrome.driver", configFileReader.getChromeDriverPath());
		//Create a instance of ChromeOptions class
		ChromeOptions options = new ChromeOptions();

		//Add chrome switch to disable notification - "**--disable-notifications**"
		options.addArguments("--disable-notifications");

		WebDriver driver=new ChromeDriver(options);
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
