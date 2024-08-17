package Railway;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Utilities;
import Constant.Constant;

public class RegisterPage extends GeneralPage{
	
	private final By txtEmail = By.xpath("//input[@id='email']");
	private final By txtPassword = By.xpath("//input[@id='password']");
	private final By txtConfirmPassword = By.xpath("//input[@id='confirmPassword']");
	private final By txtPID = By.xpath("//input[@id='pid']");
	private final By btnRegister = By.xpath("//input[@type='submit']");
	private final By lblRegisterMessageSuccess = By.xpath("//h1[text()='Thank you for registering your account']");
	private final By lblAccountActivateMsgReminder = By.xpath("//h4");
	private final By lblRegisterMessageError = By.xpath("//p[@class='message error']");
	private final By lblPasswordMessageError = By.xpath("//label[@for='password' and @class='validation-error']");
	private final By lblPIDMessagesError = By.xpath("//label[@for='pid' and @class='validation-error']");
	private final By lnkClickHere = By.xpath("//a[@href='Confirm.cshtml']");
	private final By txtConfirmationCode = By.xpath("//input[@id='confirmationCode']");
	private final By btnConfirm = By.xpath("//input[@type='submit']");
	private final By lblConfirmCodeSuccess = By.xpath("//p");
	
	
	protected WebElement getTextEmail() {return Constant.WEBDRIVER.findElement(txtEmail);}
	protected WebElement getTextPassword() {return Constant.WEBDRIVER.findElement(txtPassword);}
	protected WebElement getTextConfirmPassword() {return Constant.WEBDRIVER.findElement(txtConfirmPassword);}
	protected WebElement getTextPID() {return Constant.WEBDRIVER.findElement(txtPID);}
	protected WebElement getBtnRegister() {return Constant.WEBDRIVER.findElement(btnRegister);}
	protected WebElement getLblRegisterMessageSuccess() {return Constant.WEBDRIVER.findElement(lblRegisterMessageSuccess);}
	protected WebElement getLblAccountActivateMessageReminder() {return Constant.WEBDRIVER.findElement(lblAccountActivateMsgReminder);}
	protected WebElement getLblRegisterMessageError() {return Constant.WEBDRIVER.findElement(lblRegisterMessageError);}
	protected WebElement getLblPasswordMessageError() {return Constant.WEBDRIVER.findElement(lblPasswordMessageError);}
	protected WebElement getLblPIDMessageError() {return Constant.WEBDRIVER.findElement(lblPIDMessagesError);}
	protected WebElement getLnkClickHere() {return Constant.WEBDRIVER.findElement(lnkClickHere);}
	protected WebElement getTxtConfirmationCode() {return Constant.WEBDRIVER.findElement(txtConfirmationCode);}
	protected WebElement getBtnConfirm() {return Constant.WEBDRIVER.findElement(btnConfirm);}
	protected WebElement getLblConfirmCodeMsgSuccess() {return Constant.WEBDRIVER.findElement(lblConfirmCodeSuccess);}
	
	public String getConfirmCodeMsgSuccess() {
		return this.getLblConfirmCodeMsgSuccess().getText();
	}
	
	
	public RegisterPage enterActivationCode(String code) {
		this.getLnkClickHere().click();
		this.getTxtConfirmationCode().sendKeys(code);
		this.getBtnConfirm().click();
		return this;
	}
	
	public RegisterPage registerNewAccount(Account account) {
		this.enterRegisterInformation(account.getEmail(), account.getPassword(), account.getPassword(), account.getPID());
		return this;
	}
	
	public RegisterPage registerNewAccountFailByMismatchPassword(Account account, String invalidConfirmPassword) {
		this.enterRegisterInformation(account.getEmail(), account.getPassword(), invalidConfirmPassword, account.getPID());
		return this;
	}
	
	public RegisterPage registerNewAccountFailByEmptyPasswordAndPID(Account account, String emptyString) {
		this.enterRegisterInformation(account.getEmail(), emptyString, emptyString, emptyString);
		return this;
	}
	
	public void enterRegisterInformation(String email, String password, String confirmPassword, String pID) {
		this.getTextEmail().sendKeys(email);
		this.getTextPassword().sendKeys(password);
		this.getTextConfirmPassword().sendKeys(confirmPassword);
		this.getTextPID().sendKeys(pID);
		Utilities.moveOver(getBtnRegister());
		this.getBtnRegister().click();
	}
		
	public String getTxtLblPasswordMsgError() {
		return this.getLblPasswordMessageError().getText();
	}
	
	public String getTxtLblPIDMsgError() {
		return this.getLblPIDMessageError().getText();
	}
	
	public String getTxtLblRegisterMsgSuccess() {
		return this.getLblRegisterMessageSuccess().getText();
	}
	
	public String getTxtLblAccountActivateMsgReminder() {
		return this.getLblAccountActivateMessageReminder().getText();
	}
	
	public String getTxtLblRegisterMsgError() {
		return this.getLblRegisterMessageError().getText();
	}
	
}
