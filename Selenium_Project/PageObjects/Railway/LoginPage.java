package Railway;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Utilities;
import Constant.Constant;

public class LoginPage extends GeneralPage {
	
	private final By txtUserName = By.xpath("//input[@id='username']");
	private final By txtPassWord = By.xpath("//input[@id='password']");
	private final By btnLogin = By.xpath("//input[@value='login']");
	private final By lblLoginErrorMsg = By.xpath("//p[@class='message error LoginForm']");
	private final By txtLoginPageTitle = By.xpath("//h1[text()='Login page']");
	private final By lnkForgotPasswordPage = By.xpath("//a[@href='/Account/ForgotPassword.cshtml']");

	protected WebElement getTextUserName() {return Constant.WEBDRIVER.findElement(txtUserName);}
	protected WebElement getTextPassWord() {return Constant.WEBDRIVER.findElement(txtPassWord);}
	protected WebElement getBtnLogin() {return Constant.WEBDRIVER.findElement(btnLogin);}
	protected WebElement getLblLoginErrorMsg() {return Constant.WEBDRIVER.findElement(lblLoginErrorMsg);}
	protected WebElement getLoginPageTxtTitle() {return Constant.WEBDRIVER.findElement(txtLoginPageTitle);}
	protected WebElement getLnkForgotPasswordPage() {return Constant.WEBDRIVER.findElement(lnkForgotPasswordPage);}
	
	
	public ForgotPasswordPage goToForgotPasswordPage() {
		this.getLnkForgotPasswordPage().click();
		return new ForgotPasswordPage();
	}
	
	public HomePage loginSuccess(Account account) {
		Utilities.waitForElementPresent(this.txtUserName, Constant.TIME_IN_SECOND);
		this.getTextUserName().sendKeys(account.getEmail());
		this.getTextPassWord().sendKeys(account.getPassword());
		Utilities.moveOver(getBtnLogin());
		this.getBtnLogin().click();	
		return new HomePage();
	}
	
	public LoginPage loginError(Account account) {
		Utilities.waitForElementPresent(this.txtUserName, Constant.TIME_IN_SECOND);
		this.getTextUserName().sendKeys(account.getEmail());
		this.getTextPassWord().sendKeys(account.getPassword());
		Utilities.moveOver(getBtnLogin());
		this.getBtnLogin().click();
		return this;
	}
	
	public LoginPage loopLoginWithInvalidAccount(Account account, int times) {
		this.getTextUserName().sendKeys(account.getEmail());
		for(int i = 0; i <= times; i++) {
			this.getTextPassWord().sendKeys(account.getPassword());
			Utilities.moveOver(getBtnLogin());
			this.getBtnLogin().click();
		}
		return this;
	}
	
	public String getLoginErrorMsg() {
		return this.getLblLoginErrorMsg().getText();
	}
	
	public String getLoginPageTitle() {
		return this.getLoginPageTxtTitle().getText();
	}
	
	
}
