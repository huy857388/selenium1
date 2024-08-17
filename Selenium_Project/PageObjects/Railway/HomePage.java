package Railway;
import org.openqa.selenium.JavascriptExecutor;

import ActivateMail.MailLoginPage;
import Common.Utilities;
import Constant.Constant;

public class HomePage extends GeneralPage{
	
	public HomePage open() {
		Constant.WEBDRIVER.navigate().to(Constant.RAILWAY_URL);
		return this;
	}
	
	public MailLoginPage openActivateMailWithClosingRailway(int tabIndex) {	
		JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER; 
		js.executeScript("window.open('"+Constant.ACTIVATE_MAIL_URL+"','_blank');");
		Constant.WEBDRIVER.close();
		Utilities.switchToTab(tabIndex);
		return new MailLoginPage();
	}
	
	public MailLoginPage openActivateMailWithNotClosingRailway(int tabIndex) {
		JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER; 
		js.executeScript("window.open('"+Constant.ACTIVATE_MAIL_URL+"','_blank');");
		Utilities.switchToTab(tabIndex);
		return new MailLoginPage();
	}
	
}
