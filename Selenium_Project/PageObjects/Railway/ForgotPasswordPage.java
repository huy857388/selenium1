package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Constant.Constant;

public class ForgotPasswordPage extends GeneralPage{

	private final By txtEmailAddress = By.xpath("//input[@id='email']");
	private final By btnSendInstructions = By.xpath("//input[@value='Send Instructions']");
	private final By lblSendMailInstructionSuccessMsg = By.xpath("//p[@class='message success']");
	
	protected WebElement getTxtEmailAddress() {return Constant.WEBDRIVER.findElement(txtEmailAddress);}
	protected WebElement getBtnSendInstructions() {return Constant.WEBDRIVER.findElement(btnSendInstructions);}
	protected WebElement getLblSendMailInstructionSuccessMsg() {return Constant.WEBDRIVER.findElement(lblSendMailInstructionSuccessMsg);}
	
	
	public ForgotPasswordPage sendMailInstruction(String email) {
		this.getTxtEmailAddress().sendKeys(email);
		this.getBtnSendInstructions().click();
		Constant.WEBDRIVER.close();
		return this;
	}
	
}
