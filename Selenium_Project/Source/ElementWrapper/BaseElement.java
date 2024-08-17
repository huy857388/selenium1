package ElementWrapper;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BaseElement {

	private WebDriver driver;
	protected WebElement _element = null;
	protected List<WebElement> _elements = null;
	
	private By _byLocator;
	
	public BaseElement(By locator) {
		this._byLocator = locator;
	}

	public BaseElement(String xpath) {
		this._byLocator = By.xpath(xpath);
	}
	
	public BaseElement(WebElement elem) {
		this._element = elem;
	}
	
	protected By getLocator() {
		return this._byLocator;
	} 
	
	protected WebElement getElement() {
		try {
			return driver.findElement(this.getLocator());
		} catch (Exception e) {
			return null;
		}
	}
	
	protected List<WebElement> getElements(){
		try {
			return driver.findElements(this.getLocator());
		} catch (Exception e) {
			return null;
		}
	}
}
