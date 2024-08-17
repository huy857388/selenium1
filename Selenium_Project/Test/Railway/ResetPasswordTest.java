package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common.Utilities;
import Constant.Constant;

public class ResetPasswordTest extends TestBase{
		
	@Test
	public void TC12() {
		System.out.println("TC12 - Errors display when password reset token is blank");
		System.out.println("Pre-condition: Create and activate a new account");
		// Create and activate a new account
		Account registerAccount = activeAccount();
		Account resetPasword = new Account(registerAccount.getEmail(), Constant.NEW_RESET_PASSWORD);		
		//Click on "Forgot Password page" link
		//Enter the email address of the created account in Pre-condition
		//Click on "Send Instructions" button
		homePage.goToLoginPage()
				.goToForgotPasswordPage()
				.sendMailInstruction(registerAccount.getEmail());
		
		Utilities.switchToTab(0);
		
		String actualTitle = mailBoxWelcomePage.resetPassword(registerAccount.getEmail(), Constant.MAIL, 1)
												.getTxtLblFormTitle();
		String expectedTitle = "Password Change Form";
		
		// Validate password change form is displayed when click on reset password link
		Assert.assertEquals(actualTitle, expectedTitle, "Password Change Form is not displayed as expected");
		
		// Enter new passwords and remove reset token
		passwordChangeForm.ResetPasswordFailedByRemoveResetToken(resetPasword);
		
		String actualMsg1 = passwordChangeForm.getTxtResetErrorMsg();
		String expectedMsg1 = "The password reset token is incorrect or may be expired. Visit the forgot password page to generate a new one.";
		
		//Bug report
		System.out.println("This test case is failed: The RailWay accept change Password without 'Password Reset Token'");
		
		// Validate error message "The password reset token is incorrect or may be expired. Visit the forgot password page to generate a new one." displays above the form.	
		Assert.assertEquals(actualMsg1, expectedMsg1, "The messages is not displayed as expected");
			
	}
	
	@Test
	public void TC13() {
		System.out.println("TC13 - Errors display if password and confirm password don't match when resetting password");
		System.out.println("Pre-condition: Create and activate a new account");
		
		Account registerAccount = activeAccount();
		Account resetPasword = new Account(registerAccount.getEmail(), Constant.NEW_RESET_PASSWORD);		

		homePage.goToLoginPage()
				.goToForgotPasswordPage()
				.sendMailInstruction(registerAccount.getEmail());
		
		 Utilities.switchToTab(0);
		
		String actualTitle = mailBoxWelcomePage.resetPassword(registerAccount.getEmail(), Constant.MAIL, 1)
												.getTxtLblFormTitle();
		String expectedTitle = "Password Change Form";
		
		// Validate password change form is displayed when click on reset password link
		Assert.assertEquals(actualTitle, expectedTitle, "Password Change Form is not displayed as expected");
		
		passwordChangeForm.ResetPasswordFailedByMismatchPasswordAndConfirmPassword(resetPasword, Constant.INVALID_CONFIRM_NEW_EMAIL_PASSWORD);

		String actualMsg1 = passwordChangeForm.getTxtResetErrorMsg();
		String actualMsg2 = passwordChangeForm.getTxtConfirmPasswordErrorMsg();
		
		String expectedMsg1 = "Could not reset password. Please correct the errors and try again.";
		String expectedMsg2 = "The password confirmation did not match the new password.";
		
		// Validate error message "Could not reset password. Please correct the errors and try again." displays above the form.
		Assert.assertEquals(actualMsg1, expectedMsg1, "The message error above is not displayed as expected");
		
		// Validate error message "The password confirmation did not match the new password." displays next to the confirm password field.
		Assert.assertEquals(actualMsg2, expectedMsg2, "The message confirm error is not displayed as expected");

	}

}
