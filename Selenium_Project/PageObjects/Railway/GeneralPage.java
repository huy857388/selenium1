package Railway;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Utilities;
import Constant.Constant;

public class GeneralPage {
	private final By tabMenu(String path) {return By.xpath("//div[@id='menu']//a[@href='"+path+"']");}
	private final By lblWelcomeMessage = By.xpath("//div[@class='account']/strong");

	protected WebElement getTabMenu(String path) {return Constant.WEBDRIVER.findElement(this.tabMenu(path));}	
	protected WebElement getLblWelcomeMessage() {return Constant.WEBDRIVER.findElement(lblWelcomeMessage);}
	
	public String getWelcomeMessage() {
		return this.getLblWelcomeMessage().getText();
	}
	
	public LoginPage goToLoginPage() {
		this.getTabMenu(Constant.LOGIN_PAGE).click();
		return new LoginPage();
	}
	
	public LoginPage goToBookTicketPageWithOutLogin() {
		this.getTabMenu(Constant.BOOKTICKET_PAGE).click();
		return new LoginPage();
	}
	
	public BookTicketPage goToBookTicketPageWithLogin() {
		this.getTabMenu(Constant.BOOKTICKET_PAGE).click();
		return new BookTicketPage();
	}
	
	public TimeTablePage goToTimeTablePage() {
		this.getTabMenu(Constant.TIME_TABLE_PAGE).click();
		return new TimeTablePage();
	}
	
	public boolean isTabMenuDisplayed(String menuPath) {
		Utilities.waitUntilElementVisible(getTabMenu(menuPath));
		return this.getTabMenu(menuPath).isDisplayed();
	}
	
	public boolean isPageDisplayed(String pageURL) {
		String currentURL = Utilities.getURL();
		if(currentURL.contains(pageURL)) {
			return true;
		}else {
			return false;
		}
	}
	
	public MyTicketPage goToMyTicketPage() {
		this.getTabMenu(Constant.MYTICKET_PAGE).click();
		return new MyTicketPage();
	}
	
	public ChangePasswordPage goToChangePasswordPage() {
		this.getTabMenu(Constant.CHANGE_PASSWORD_PAGE).click();
		return new ChangePasswordPage();
	}
	
	public RegisterPage goToRegisterPage() {
		this.getTabMenu(Constant.REGISTER_PAGE).click();
		return new RegisterPage();
	}

}
