package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Constant.Constant;

public class ChangePasswordTest extends TestBase{

	@Test
	public void TC09() {
		System.out.println("TC09 - User can change password");
		System.out.println("Pre-condition: Create and activate a new account");
				
		// Create and activate a new account
		Account registerAccount = activeAccount();
		Account loginWithNewAccount = new Account(registerAccount.getEmail(), Constant.NEW_EMAIL_PASSWORD);
		
		//Change password
		String actualMsg = homePage.goToLoginPage() 
									.loginSuccess(loginWithNewAccount)
									.goToChangePasswordPage()
									.changePassword(loginWithNewAccount, Constant.PASSWORD_CHANGING)
									.getChangePasswordSuccessMsg();
		
		String expectedMsg = "Your password has been updated";
		
		//Bug report
		System.out.println("This test case is failed: The expected message is different to The RailWay displayed");			
		
		// Validation message "Your password has been updated" appears.
		Assert.assertEquals(actualMsg, expectedMsg, "The password has not been changed");			
	}

}
