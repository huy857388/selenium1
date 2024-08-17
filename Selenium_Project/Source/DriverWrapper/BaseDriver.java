package DriverWrapper;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Constant.Constant;

class BaseDriver implements WebDriver{

	public void get(String url) {
		Constant.WEBDRIVER.get(url);
	}
	
	public String getCurrentUrl() {
		return Constant.WEBDRIVER.getCurrentUrl();
	}
	
	public String getTitle() {
		return Constant.WEBDRIVER.getTitle();
	}
	
	@Deprecated
	public List<WebElement> findElements(By by) {
		throw new UnsupportedOperationException("This method is not supported");
	}

	@Deprecated
	public WebElement findElement(By by) {
		throw new UnsupportedOperationException("This method is not supported");
	}

	public String getPageSource() {
		return Constant.WEBDRIVER.getPageSource();
	}

	public void close() {
		Constant.WEBDRIVER.close();
	}

	public void quit() {
		Constant.WEBDRIVER.quit();
	}

	@Deprecated
	public Set<String> getWindowHandles() {
		throw new UnsupportedOperationException("This method is not supported");
	}

	@Deprecated
	public String getWindowHandle() {
		throw new UnsupportedOperationException("This method is not supported");
	}

	public TargetLocator switchTo() {
		return Constant.WEBDRIVER.switchTo();
	}

	public Navigation navigate() {
		return Constant.WEBDRIVER.navigate();
	}

	public Options manage() {
		return Constant.WEBDRIVER.manage();
	}
}
