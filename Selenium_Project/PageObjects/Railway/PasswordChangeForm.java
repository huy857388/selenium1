package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Utilities;
import Constant.Constant;

public class PasswordChangeForm extends GeneralPage{
	private final By lblFormTitle = By.xpath("//legend[text()='Password Change Form']");
	private final By txtNewPassword = By.xpath("//input[@id='newPassword']");
	private final By txtConfirmPassword = By.xpath("//input[@id='confirmPassword']");
	private final By txtPasswordResetToken = By.xpath("//input[@id='resetToken']");
	private final By btnResetPassword = By.xpath("//input[@value='Reset Password']");
	private final By lblResetErrorMsg = By.xpath("//p[@class='message error']");
	private final By lblPasswordResetTokenMsg = By.xpath("//label[@for='resetToken' and @class='validation-error']");
	private final By lblConfirmPasswordErrorMsg = By.xpath("//label[@for='confirmPassword' and @class='validation-error']");
	
	protected WebElement getLblFormTitle() {return Constant.WEBDRIVER.findElement(lblFormTitle);}
	protected WebElement getTxtNewPassword() {return Constant.WEBDRIVER.findElement(txtNewPassword);}
	protected WebElement getTxtConfirmPassword() {return Constant.WEBDRIVER.findElement(txtConfirmPassword);}
	protected WebElement getTxtPasswordResetToken() {return Constant.WEBDRIVER.findElement(txtPasswordResetToken);}
	protected WebElement getBtnResetPassword() {return Constant.WEBDRIVER.findElement(btnResetPassword);}
	protected WebElement getLblResetErrorMsg() {return Constant.WEBDRIVER.findElement(lblResetErrorMsg);}
	protected WebElement getLblPasswordResetTokenMsg() {return Constant.WEBDRIVER.findElement(lblPasswordResetTokenMsg);}
	protected WebElement getLblConfirmPasswordErrorMsg() {return Constant.WEBDRIVER.findElement(lblConfirmPasswordErrorMsg);}
	
	public String getTxtLblFormTitle() {
		return this.getLblFormTitle().getText();
	}
	
	public PasswordChangeForm ResetPasswordFailedByRemoveResetToken(Account account) {
		this.getTxtNewPassword().sendKeys(account.getPassword());
		this.getTxtConfirmPassword().sendKeys(account.getPassword());
		this.getTxtPasswordResetToken().clear();
		this.getBtnResetPassword().click();
		return this;
	}
	
	
	public PasswordChangeForm ResetPasswordFailedByMismatchPasswordAndConfirmPassword(Account account, String invalidConfirmNewPassword) {
		this.getTxtNewPassword().sendKeys(account.getPassword());
		this.getTxtConfirmPassword().sendKeys(invalidConfirmNewPassword);
		this.getBtnResetPassword().click();
		return this;
	}
		
	public String getTxtResetErrorMsg() {
		if(Utilities.isElementPresent(lblResetErrorMsg) == true) 
			return this.getLblResetErrorMsg().getText();
		else {
			return "Password changed! Click here to login.";
		}
	}
	
	public String getTxtConfirmPasswordErrorMsg() {
		return this.getLblConfirmPasswordErrorMsg().getText();
	}
	
}
