package Utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
	private Properties properties;
	private final String propertyFilePath= "configs\\Configuation.properties";
	 
	public ConfigFileReader(){
		 BufferedReader reader;
		 try {
		 reader = new BufferedReader(new FileReader(propertyFilePath));
		 properties = new Properties();
		 try {
			 properties.load(reader);
			 reader.close();
		 } catch (IOException e) {
		 e.printStackTrace();
		 }
		 } catch (FileNotFoundException e) {
		 e.printStackTrace();
		 throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		 } 
		 }
	
	 public String getChromeDriverPath(){
		 String driverCPath = properties.getProperty("chromeDriverPath");
		 if(driverCPath!= null) return driverCPath;
		 else throw new RuntimeException("driverPath not specified."); 
		 }
	 public String getFirefoxDriverPath()
	 {
		 String driverFPath = properties.getProperty("firefoxDriverPath");
		 if(driverFPath!= null) return driverFPath;
		 else throw new RuntimeException("driverPath not specified."); 
		  
	 }
	 
	 public String getApplicationUrl() {
		 String url = properties.getProperty("url");
		 if(url != null) return url;
		 else throw new RuntimeException("url not specified.");
		 }
	 public String getUserID() {
		 String userID=properties.getProperty("userID");
		 if(userID!=null) return userID;
		 else throw new RuntimeException("userID not specified.");
	 }
	 public String getPassword() {
		 String pwd=properties.getProperty("password");
		 if(pwd!=null) return pwd;
		 else throw new RuntimeException("password not specified.");
	 }
	 public String getPin() {
		 String pin=properties.getProperty("pin");
		 if(pin!=null) return pin;
		 else throw new RuntimeException("pin not specified.");
	
	 }
	 public String getWatchlistTestDataPath()
	 {
		 String path=properties.getProperty("WatchListTestDataPath");
		return path;
		 
	 }
	 public String getLoginTestDataPath()
	 {
		 String path=properties.getProperty("LoginTestDataPath");
		 return path;
	 }

}
