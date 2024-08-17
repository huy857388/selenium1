package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common.Utilities;
import Constant.Constant;

public class RegisterTest extends TestBase{
	Account registerAccount = new Account(Utilities.generateRandomEmail(10),Constant.NEW_EMAIL_PASSWORD,Constant.PID);
	Account loginMail = new Account(Constant.MAIL_USERNAME,	Constant.MAIL_PASSWORD);
	Account loginWithNewAccount = new Account(registerAccount.getEmail(), Constant.NEW_EMAIL_PASSWORD);
	@Test
	public void TC07() {
		System.out.println("TC 07 - User can create new account");
		RegisterPage registerPage = homePage.open()
									.goToRegisterPage()
									.registerNewAccount(registerAccount);
		
		String actualMsg1 = registerPage.getTxtLblRegisterMsgSuccess();
		String actualMsg2 = registerPage.getTxtLblAccountActivateMsgReminder();
		
		String expectedMsg1 = "Thank you for registering your account";
		String expectedMsg2 = "But you're not done yet!";
		
		// Validate new account is created
		Assert.assertEquals(actualMsg2, expectedMsg2, "The new account is not created");
		
		// Validate message "Thank you for registering your account" appears.
		Assert.assertEquals(actualMsg1, expectedMsg1, "The message is not displayed as expected");	
	}
	@Test
	public void TC10() {
		System.out.println("TC10 - User can't create account with \"Confirm password\" is not the same with \"Password\"");
		String actualMsg = homePage.open()
									.goToRegisterPage()
									.registerNewAccountFailByMismatchPassword(registerAccount, Constant.INVALID_CONFIRM_NEW_EMAIL_PASSWORD)
									.getTxtLblRegisterMsgError();
		
		String expectedMsg = "There're errors in the form. Please correct the errors and try again.";
		
		// Validate message "There're errors in the form. Please correct the errors and try again." appears.
		Assert.assertEquals(actualMsg, expectedMsg, "The message is not displayed as expected");
		
	}
	@Test
	public void TC11() {
		System.out.println("TC11 - User can't create account while password and PID fields are empty");
		RegisterPage registerPage = homePage.open()
											.goToRegisterPage()
											.registerNewAccountFailByEmptyPasswordAndPID(registerAccount, "");
		
		String actualMsg1 = registerPage.getTxtLblRegisterMsgError();
		String actualMsg2 = registerPage.getTxtLblPasswordMsgError();
		String actualMsg3 = registerPage.getTxtLblPIDMsgError();
		
		String expectedMsg1 = "There're errors in the form. Please correct the errors and try again.";
		String expectedMsg2 = "Invalid password length.";
		String expectedMsg3 = "Invalid ID length.";
		
		//Bug report
		System.out.println("This test case is failed: The expected message is different to The RailWay displayed");			

		// Validate message "There're errors in the form. Please correct the errors and try again." appears above the form.
		Assert.assertEquals(actualMsg1, expectedMsg1, "The message is not displayed as expected");
		
		// Validate next to password fields, error message "Invalid password length." displays
		Assert.assertEquals(actualMsg2, expectedMsg2, "The message is not displayed as expected");
		
		// Validate next to PID field, error message "Invalid ID length." displays
		Assert.assertEquals(actualMsg3, expectedMsg3, "The message is not displayed as expected");
	}

}
