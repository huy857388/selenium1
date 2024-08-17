package ActivateMail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Utilities;
import Constant.Constant;
import Railway.Account;

public class MailLoginPage{
	private final By txtUsername = By.xpath("//input[@id='rcmloginuser']");
	private final By txtPassword = By.xpath("//input[@id='rcmloginpwd']");
	private final By btnLogin = By.xpath("//button[@id='rcmloginsubmit']");
	
	protected WebElement getTxtUsername() {return Constant.WEBDRIVER.findElement(txtUsername);}
	protected WebElement getTxtPassword() {return Constant.WEBDRIVER.findElement(txtPassword);}
	protected WebElement getBtnLogin() {return Constant.WEBDRIVER.findElement(btnLogin);}
	
	public MailBoxWelcomePage loginMail(Account account) {
		Utilities.waitUntilElementVisible(getTxtUsername());
		this.getTxtUsername().sendKeys(account.getEmail());
		Utilities.waitUntilElementVisible(getTxtPassword());		
		this.getTxtPassword().sendKeys(account.getPassword());
		this.getBtnLogin().click();
		
		return new MailBoxWelcomePage();
	}
}
