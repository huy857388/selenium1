package DriverWrapper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import Common.BrowserType;
import Constant.Constant;

public class Driver extends BaseDriver{
	
	private WebDriver driver;
	
	public void getDriver(BrowserType browType) {
		switch ( browType ) {
	    case  CHROME:
			System.setProperty("webdriver.chrome.driver", Constant.DRIVER_PATH + "\\Driver\\chromedriver.exe");
			driver = new ChromeDriver();
	        break;
	    case  OPERA:
	        break;
	    case  FIREFOX:
	        System.setProperty("webdriver.gecko.driver",Constant.DRIVER_PATH + "\\Driver\\geckodriver.exe"); 
	        driver = new FirefoxDriver(); 
	        break;
		}
	}
}
