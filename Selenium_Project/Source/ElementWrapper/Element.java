package ElementWrapper;

import org.openqa.selenium.By;

public class Element extends BaseElement{

	
	public Element(By locator) {
		super(locator);
	}
	
	public Element(String xpath) {
		super(xpath);
	}
}
