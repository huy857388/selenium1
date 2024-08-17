package ActivateMail;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import Common.Utilities;
import Constant.Constant;
import Railway.PasswordChangeForm;
import Railway.RegisterPage;

public class MailBoxWelcomePage{
	private final By lnkJunkMailBox = By.xpath("//a[@href='./?_task=mail&_mbox=Junk']");
	private final By lnkInboxMail = By.xpath("//a[@href='./?_task=mail&_mbox=INBOX']");
	private final By btnSearchOption = By.xpath("//a[@class='button options']");
	private final By txtMailSearchForm = By.xpath("//input[@id='mailsearchform']");
	private final By btnSearch = By.xpath("//button");
	private final By btnRefreshMessages = By.xpath("//a[@id='rcmbtn111']");
	private final By mailNeedToActive(String email) {return By.xpath("//td[@class='subject']//span[contains(text(),'Please confirm your account "+email+"')]");}
	private final By lnkActivation = By.xpath("//a[contains(text(),'http://railwaysg2.somee.com/Account/Confirm')]");
	private final By lnkPasswordReset = By.xpath("//a[contains(text(),'http://railwaysg2.somee.com/Account/PasswordReset')]");
	private final By sideBarMenu(String option) {return By.xpath("//div[@id='taskmenu']/a[@class='"+option+"']");}
	private final By mailNeedToReset = By.xpath("//td[@class='subject']//span[contains(text(),'Please reset')]");
	private final By txtActivationCode = By.xpath("//div[@class='rcmBody']");
	private final By txtFisrtMail = By.xpath("//tr[1]//td[@class='subject']//span[contains(text(),'Please')]");
	
	protected WebElement getLnkJunkMailBox() {return Constant.WEBDRIVER.findElement(lnkJunkMailBox);}
	protected WebElement getLnkInboxMail() {return Constant.WEBDRIVER.findElement(lnkInboxMail);}
	protected WebElement getMailNeedToActive(String email) {return Constant.WEBDRIVER.findElement(this.mailNeedToActive(email));}
	protected WebElement getLnkActivation() {return Constant.WEBDRIVER.findElement(lnkActivation);}
	protected WebElement getTxtMailSearchForm() {return Constant.WEBDRIVER.findElement(txtMailSearchForm);}
	protected WebElement getBtnSearchOption() {return Constant.WEBDRIVER.findElement(btnSearchOption);}
	protected WebElement getBtnSearch() {return Constant.WEBDRIVER.findElement(btnSearch);}
	protected WebElement getBtnRefreshMessages() {return Constant.WEBDRIVER.findElement(btnRefreshMessages);}
	protected WebElement getLnkPasswordReset() {return Constant.WEBDRIVER.findElement(lnkPasswordReset);}
	protected WebElement getSideBarMenu(String option) {return Constant.WEBDRIVER.findElement(sideBarMenu(option));}
	protected WebElement getMailNeedToReset() {return Constant.WEBDRIVER.findElement(mailNeedToReset);}
	protected WebElement getTxtActivationCode() {return Constant.WEBDRIVER.findElement(txtActivationCode);}
	protected WebElement getTxtFirtMail() {return Constant.WEBDRIVER.findElement(txtFisrtMail);}

	public String getActivationCode() {
		return this.getTxtActivationCode().getText();
	}
	
	public MailBoxWelcomePage getActivationCodeForNewUser(String username) {
		this.getLnkInboxMail().click();
		Utilities.waitForElementPresent(this.txtFisrtMail, Constant.TIME_IN_SECOND);
		this.getTxtMailSearchForm().sendKeys(username);
		this.getTxtMailSearchForm().sendKeys(Keys.ENTER);
		Utilities.waitForElementPresent(this.mailNeedToActive(username), Constant.TIME_IN_SECOND);
		if(this.getMailNeedToActive(username).isDisplayed()) {
			Utilities.doubleClick(this.getMailNeedToActive(username));
			Utilities.waitForElementPresent(this.txtActivationCode, Constant.TIME_IN_SECOND);

		}
		else{
			this.getLnkJunkMailBox().click();	
			Utilities.waitForElementPresent(this.txtFisrtMail, Constant.TIME_IN_SECOND);
			this.getTxtMailSearchForm().sendKeys(username); 
			this.getTxtMailSearchForm().sendKeys(Keys.ENTER);
			Utilities.waitForElementPresent(this.mailNeedToActive(username), Constant.TIME_IN_SECOND);
			if(this.getMailNeedToActive(username).isDisplayed()) {
				Utilities.doubleClick(this.getMailNeedToActive(username));	
				Utilities.waitForElementPresent(this.txtActivationCode, Constant.TIME_IN_SECOND);
			}else {
				System.out.println("No mail is found!");
			}
		}
		return new MailBoxWelcomePage();
	}
	
	public RegisterPage activateNewUserRegistered(String username, int tabIndex) {
		this.getLnkInboxMail().click();
		Utilities.waitForElementPresent(this.txtFisrtMail, Constant.TIME_IN_SECOND);
		this.getTxtMailSearchForm().sendKeys(username);
		this.getTxtMailSearchForm().sendKeys(Keys.ENTER);
		Utilities.waitForElementPresent(this.mailNeedToActive(username), Constant.TIME_IN_SECOND);
		if(this.getMailNeedToActive(username).isDisplayed()) {
			Utilities.doubleClick(this.getMailNeedToActive(username));
			Utilities.waitUntilElementVisible(this.getLnkActivation());
			this.getLnkActivation().click();
			Utilities.switchToTab(tabIndex); 	
		}else {
			this.getLnkJunkMailBox().click();	
			Utilities.waitForElementPresent(this.txtFisrtMail, Constant.TIME_IN_SECOND);
			this.getTxtMailSearchForm().sendKeys(username); 
			this.getTxtMailSearchForm().sendKeys(Keys.ENTER);
			Utilities.waitForElementPresent(this.mailNeedToActive(username), Constant.TIME_IN_SECOND);
			if(this.getMailNeedToActive(username).isDisplayed()) {
				Utilities.doubleClick(this.getMailNeedToActive(username));	
				Utilities.waitUntilElementVisible(this.getLnkActivation());
				this.getLnkActivation().click();
				Utilities.switchToTab(tabIndex);	
			}else { 
				System.out.println("No mail is found!");
			}
		}
		return new RegisterPage();
	}
	
	public PasswordChangeForm resetPassword(String username, String menuOption, int tabIndex) {		

		this.getSideBarMenu(menuOption).click();
		Utilities.waitUntilElementVisible(getLnkInboxMail());
		this.getLnkInboxMail().click();
		Utilities.waitForElementPresent(this.txtFisrtMail, Constant.TIME_IN_SECOND);
		this.getBtnRefreshMessages().click();
		this.getTxtMailSearchForm().clear();
		this.getTxtMailSearchForm().sendKeys(username);
		this.getTxtMailSearchForm().sendKeys(Keys.ENTER);
		Utilities.waitForElementPresent(this.mailNeedToReset, Constant.TIME_IN_SECOND);
		if(this.getMailNeedToReset().isDisplayed()) {
			Utilities.doubleClick(this.getMailNeedToReset());
			Utilities.waitForElementPresent(this.lnkPasswordReset, Constant.TIME_IN_SECOND);
//			Utilities.waitUntilElementVisible(this.getLnkPasswordReset());
			this.getLnkPasswordReset().click();
			Utilities.switchToTab(tabIndex);				
		}else {
			this.getLnkJunkMailBox().click();	
			Utilities.waitForElementPresent(this.txtFisrtMail, Constant.TIME_IN_SECOND);
			this.getTxtMailSearchForm().sendKeys(username); 
			this.getTxtMailSearchForm().sendKeys(Keys.ENTER);
			Utilities.waitForElementPresent(this.mailNeedToReset, Constant.TIME_IN_SECOND);
			if(this.getMailNeedToReset().isDisplayed()) {
				Utilities.doubleClick(this.getMailNeedToReset());	
				Utilities.waitForElementPresent(this.lnkPasswordReset, Constant.TIME_IN_SECOND);
				this.getLnkPasswordReset().click();
				Utilities.switchToTab(tabIndex);
			}else {
				System.out.println("No mail is found!");
			}
			
		}
	
		return new PasswordChangeForm();
	}	
}
