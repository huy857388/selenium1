package Railway;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common.Utilities;
import Constant.Constant;

public class FinalTCs extends TestBase{

	@Test
	public void FTTC01() {
		System.out.println("FTTC01 - User can filter 'Manager ticket' table with Depart Date");
		System.out.println("Pre-condition: Create and activate a new account");
		
		// Create and activate a new account
		Account registerAccount = activeAccount();
		Account loginWithNewAccount = new Account(registerAccount.getEmail(), Constant.NEW_EMAIL_PASSWORD);	
		
		// 1. Navigate to QA Railway Website
		// 2. Login with a valid account
		homePage.goToLoginPage()
				.loginSuccess(loginWithNewAccount)
				.goToBookTicketPageWithLogin();
		
		// 3. Book more than 6 tickets with different Depart Dates
		ArrayList<TicketInformation> ticketInformationList = new ArrayList<TicketInformation>();
		ticketInformationList = Utilities.addValueToBlankTickets(ticketInformationList, 6);
		bookTicketPage.bookListTicket(ticketInformationList);
						
		// 4. Click on "My ticket" tab
		// 5. Input one of booked Depart Date in "Depart date" textbox
		// 6. Click "Apply filter" button
		boolean isCorrectTicketFilterDisplayed = homePage.goToMyTicketPage()
				.filterMyTicket(ticketInformationList.get(1).getArriveAt(),Constant.IGNORE,"",Constant.IGNORE)
				.isCorrectTicketFilterDisplay("Depart Date", ticketInformationList.get(1).getDepartDate());

		// Validate "Manage ticket" table shows correct tickets
		Assert.assertTrue(isCorrectTicketFilterDisplayed, "No ticket found with Depart Date: " + ticketInformationList.get(1).getDepartDate() + "" );				
	}
	
	@Test
	public void FTTC02() {
		System.out.println("FTTC02 - User can also activate new account using activation code");
		
		Account registerAccount = new Account(Utilities.generateRandomEmail(10),Constant.NEW_EMAIL_PASSWORD,Constant.PID);
		
		// 1. Navigate to QA Railway Website
		// 2. Register new account with valid values
		homePage.open()
				.goToRegisterPage()
				.registerNewAccount(registerAccount);
		
		// 3. Open mailbox and the open the activation email
		String contentConfirmEmail = homePage.openActivateMailWithNotClosingRailway(1)
											.loginMail(loginMail)
											.getActivationCodeForNewUser(registerAccount.getEmail())
											.getActivationCode();
		// 4. Get the activation code
		String subCodeString = Utilities.getSubString(contentConfirmEmail);
		String activationCode = Utilities.getActivationCode(subCodeString);
		
		// 5. Back to Register page, click on "here" link to enter the activation code
		// 6. Enter the activation code from step 4 and click on "Confirm" button
		Utilities.switchToTab(0);
		String actualMsg1 = homePage.goToRegisterPage()
									.enterActivationCode(activationCode)
									.getConfirmCodeMsgSuccess();
		String expectedMsg1 = "Registration Confirmed! You can now log in to the site.";
		
		// Validate message "Registration Confirmed! You can now log in to the site." displayed
		Assert.assertEquals(actualMsg1, expectedMsg1);
		
		// 7. Login with this account
		String actualMsg2 = homePage.goToLoginPage()
									.loginSuccess(registerAccount)
									.getWelcomeMessage();

		String expectedMsg2 = "Welcome " + registerAccount.getEmail();	
		
		// Validate user is logged into Railway
		Assert.assertTrue(homePage.isTabMenuDisplayed(Constant.LOGOUT_PAGE), "User cannot log into Railway");
		
		// Validate welcome user message is displayed
		Assert.assertEquals(actualMsg2, expectedMsg2, "The welcome message is not displayed as expected");
		
	}
}
