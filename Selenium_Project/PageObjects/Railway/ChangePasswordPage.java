package Railway;
import org.openqa.selenium.WebElement;

import Common.Utilities;

import org.openqa.selenium.By;
import Constant.Constant;

public class ChangePasswordPage extends GeneralPage{
	private final By txtChangePasswordTitle = By.xpath("//h1[text()='Change password']");
	private final By txtCurrentPassword = By.xpath("//input[@id='currentPassword']");
	private final By txtNewPassword = By.xpath("//input[@id='newPassword']");
	private final By txtConfirmNewPassword = By.xpath("//input[@id='confirmPassword']");
	private final By btnChangePassword = By.xpath("//input[@value='Change Password']");
	private final By lblChangePasswordSuccessMsg = By.xpath("//p[@class='message success']");
	
	protected WebElement getChangePasswordTxtTitle(){return Constant.WEBDRIVER.findElement(txtChangePasswordTitle);}
	protected WebElement getTxtCurrentPassword() {return Constant.WEBDRIVER.findElement(txtCurrentPassword);}
	protected WebElement getTxtNewPassword() {return Constant.WEBDRIVER.findElement(txtNewPassword);}
	protected WebElement getTxtConfirmNewPassword() {return Constant.WEBDRIVER.findElement(txtConfirmNewPassword);}
	protected WebElement getBtnChangePassword() {return Constant.WEBDRIVER.findElement(btnChangePassword);}
	protected WebElement getLblChangePasswordSuccessMsg() {return Constant.WEBDRIVER.findElement(lblChangePasswordSuccessMsg);}
	
	public boolean isChangePasswordPageDisplayed() {
		return this.getChangePasswordTxtTitle().isDisplayed();
			
	}
	
	public ChangePasswordPage changePassword(Account account, String newPassword) {
		this.getTxtCurrentPassword().sendKeys(account.getPassword());
		this.getTxtNewPassword().sendKeys(newPassword);
		this.getTxtConfirmNewPassword().sendKeys(newPassword);
		Utilities.moveOver(getBtnChangePassword());
		this.getBtnChangePassword().click();
		return this;
	}
	
	public String getChangePasswordSuccessMsg() {
		return this.getLblChangePasswordSuccessMsg().getText();
	}
}
