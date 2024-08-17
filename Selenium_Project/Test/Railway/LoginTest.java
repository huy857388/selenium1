package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common.Utilities;
import Constant.Constant;

public class LoginTest extends TestBase{
	Account Account = new Account(Constant.USERNAME,Constant.PASSWORD);
	Account AccountFailByInvalidPassword = new Account(Constant.USERNAME,Constant.INVALID_PASSWORD);
	Account AccountFailByBlankUsername = new Account("",Constant.PASSWORD);
	Account registerAccount = new Account(Utilities.generateRandomEmail(10),Constant.NEW_EMAIL_PASSWORD,Constant.PID);

	@Test
	public void TC01() {
		System.out.println("TC01 - User can log into Railway with valid username and password");
		String actualMsg = homePage.open()
									.goToLoginPage()
									.loginSuccess(Account)
									.getWelcomeMessage();
		String expectedMsg = "Welcome " + Constant.USERNAME;
		
		// Validate user is logged into Railway
		Assert.assertTrue(homePage.isTabMenuDisplayed(Constant.LOGOUT_PAGE), "User cannot log into Railway");
		
		// Validate welcome user message is displayed
		Assert.assertEquals(actualMsg, expectedMsg, "The welcome message is not displayed as expected");		
	}
	@Test
	public void TC02() {
		System.out.println("TC02 - User can't login with blank \"Username\" textbox");
		String actualMsg = homePage.open()
									.goToLoginPage()
									.loginError(AccountFailByBlankUsername)
									.getLoginErrorMsg();
		
		String expectedMsg = "There was a problem with your login and/or errors exist in your form.";
		
		// Validate user can not log into Railway
		Assert.assertTrue(homePage.isTabMenuDisplayed(Constant.LOGIN_PAGE), "User can log into Railway");
		
		// Validate the message error "There was a problem with your login and/or errors exist in your form. " appears.
		Assert.assertEquals(actualMsg, expectedMsg, "The message \"There was a problem with your login and/or errors exist in your form. \" is not appears.");		
	}	
	@Test
	public void TC03() {
		System.out.println("TC03 - User cannot log into Railway with invalid password");
		String actualMsg = homePage.open()
									.goToLoginPage()
									.loginError(AccountFailByInvalidPassword)
									.getLoginErrorMsg();
		
		String expectedMsg = "There was a problem with your login and/or errors exist in your form.";
		
		// Validate user can not log into Railway
		Assert.assertTrue(homePage.isTabMenuDisplayed(Constant.LOGIN_PAGE), "User can log into Railway");
		
		// Bug report
		System.out.println("This test case is failed: The RailWay's error message is not similar to expected message");

		// Validate the message error "There was a problem with your login and/or errors exist in your form. " appears.
		Assert.assertEquals(actualMsg, expectedMsg, "The Error message \"There was a problem with your login and/or errors exist in your form.\" is not displayed.");		
	}
	
	@Test
	public void TC04() {
		System.out.println("TC04 - Login page displays when un-logged User clicks on Book"
				+ "ticket\" tab");
		String actualMsg = homePage.open()
									.goToBookTicketPageWithOutLogin()
									.getLoginPageTitle();
		
		String expectedMsg = "Login page";
		
		// Validate the Login page displays instead of Book ticket page
		Assert.assertEquals(actualMsg, expectedMsg, "The Login page is not display instead of Book ticket page");		
	}

	@Test
	public void TC05() {
		System.out.println("TC05 - System shows message when user enters wrong password several times");

		String actualMsg = homePage.open()
									.goToLoginPage()
									.loopLoginWithInvalidAccount(AccountFailByInvalidPassword,4)
									.getLoginErrorMsg();
		
		String expectedMsg = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";
		
		//Bug report
		System.out.println("This test case is failed: The RailWay do not displayed expected message");
		
		// Validate user can not log into Railway
		Assert.assertTrue(homePage.isTabMenuDisplayed(Constant.LOGOUT_PAGE), "User can log into Railway");
		
		// Validate the Login page displays instead of Book ticket page
		Assert.assertEquals(actualMsg, expectedMsg, "The User can't login and message \"You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.\" is not appear.");		
	}
	
	@Test
	public void TC06() {
		System.out.println("TC06 - Additional pages display once user logged in");
		 homePage.open()
		 			.goToLoginPage()
					.loginSuccess(Account);
		
		// Validate The My ticket tabs are displayed
		Assert.assertTrue(homePage.isTabMenuDisplayed(Constant.MYTICKET_PAGE), " The My ticket tabs are not displayed.");
		
		// Validate The Change password tabs are displayed.
		Assert.assertTrue(homePage.isTabMenuDisplayed(Constant.CHANGE_PASSWORD_PAGE), "The Change password tabs are not displayed.");
		
		// Validate The Logout tabs are displayed
		Assert.assertTrue(homePage.isTabMenuDisplayed(Constant.LOGOUT_PAGE), "The Logout tabs are not displayed.");	
	
		// Validate Click "My ticket" tab, user will be directed to My ticket page and Click "Change password" tab, user will be directed to Change password page
		Assert.assertTrue(homePage.goToMyTicketPage().isMyTicketPageDisplayed(), "User cannot be directed to My Ticket Page when click to My Ticket Tab");
		Assert.assertTrue(homePage.goToChangePasswordPage().isChangePasswordPageDisplayed(), "User cannot be directed to Change Password Page when click to Change Password Tab");
	}
	
	@Test
	public void TC08() {
		System.out.println("TC08 - User can't login with an account hasn't been activated");
		System.out.println("Pre-Condition: Create a new account but not activate it");
		Account loginWithNewAccount = new Account(registerAccount.getEmail(), Constant.NEW_EMAIL_PASSWORD);

		String actualMsg = homePage.open()
									.goToRegisterPage()
									.registerNewAccount(registerAccount)
									.goToLoginPage()
									.loginError(loginWithNewAccount)
									.getLoginErrorMsg();
									
		
		String expectedMsg = "Invalid username or password. Please try again.";
		
		// Validate user cannot login
		Assert.assertTrue(homePage.isTabMenuDisplayed(Constant.LOGIN_PAGE), "User is logged into Railway");
		
		// Validate message message "Invalid username or password. Please try again." appears.
		Assert.assertEquals(actualMsg, expectedMsg, "The message \"Invalid username or password.Please try again.\" is not appears.");
	}

}