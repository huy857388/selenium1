package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common.Utilities;
import Constant.Constant;

public class TicketTest extends TestBase{
	@Test
	public void TC14() {
		System.out.println("TC14 - User can book 1 ticket at a time");
		System.out.println("Pre-condition: Create and activate a new account");
		
		Account registerAccount = activeAccount();
		Account loginWithNewAccount = new Account(registerAccount.getEmail(), Constant.NEW_EMAIL_PASSWORD);
		TicketInformation ticketInformation = new TicketInformation();
		ticketInformation = Utilities.addValueToOneTicket(ticketInformation);
		
		String actualMsg = homePage.goToLoginPage()
							.loginSuccess(loginWithNewAccount)
							.goToBookTicketPageWithLogin()
							.bookTicket(ticketInformation)
							.getTxtBookTicketSuccess();
		
		String expectedMsg = "Ticket booked successfully!";
		
		// Validate message "Ticket booked successfully!" displays
		Assert.assertEquals(actualMsg, expectedMsg, "The message is not displayed as expected");		
		
		// Validate ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)
		Assert.assertTrue(bookTicketPage.isTicketDepartDateBookedCorrect(ticketInformation), "The ticket depart date information is not correct as booked");
		Assert.assertTrue(bookTicketPage.isTicketDepartFromBookedCorrect(ticketInformation), "The ticket depart from information is not correct as booked");
//		Assert.assertTrue(bookTicketPage.isTicketArriveAtBookedCorrect(ticketInformation), "The ticket arrive at information is not correct as booked");
		Assert.assertTrue(bookTicketPage.isTicketSeatTypeBookedCorrect(ticketInformation), "The ticket seat type information is not correct as booked");
		Assert.assertTrue(bookTicketPage.isTicketTicketAmountBookedCorrect(ticketInformation), "The ticket amount information is not correct as booked");

		
	}
		
	@Test
	public void TC15() {
		System.out.println("TC15 - User can open \"Book ticket\" page by clicking on \"Book ticket\" link in \"Train timetable\" page");
		System.out.println("Pre-condition: Create and activate a new account");
		
		Account registerAccount = activeAccount();
		Account loginWithNewAccount = new Account(registerAccount.getEmail(), Constant.NEW_EMAIL_PASSWORD);
		
		// Click on "Timetable" tab, click on "book ticket" link of the route from "Huế" to "Sài Gòn"
		homePage.goToLoginPage()
				.loginSuccess(loginWithNewAccount)
				.goToTimeTablePage()
				.clickToBookTicket("Depart Station", "Arrive Station", Constant.DEPART_STATION, Constant.ARRIVE_STATION);

		
		// Validate Book ticket page is loaded
		Assert.assertTrue(homePage.isPageDisplayed(Constant.BOOKTICKET_PAGE), "Book ticket page is not loaded as expected");
		
		// Validate DepartFrom and ArriveAt is corrected as booked
		Assert.assertTrue(bookTicketPage.isSelectedDepartFromValueCorrect(Constant.DEPART_STATION), "Depart From value is not correct as booked information");
		Assert.assertTrue(bookTicketPage.isSelectedArriveAtValueCorrect(Constant.ARRIVE_STATION), "Arrive At value is not correct as booked information");

	}
	
	@Test
	public void TC16() {
		System.out.println("TC16 - User can cancel a ticket");
		System.out.println("Pre-condition: Create and activate a new account");
		
		Account registerAccount = activeAccount();
		Account loginWithNewAccount = new Account(registerAccount.getEmail(), Constant.NEW_EMAIL_PASSWORD);
		TicketInformation ticketInformation = new TicketInformation();
		ticketInformation = Utilities.addValueToOneTicket(ticketInformation);
		
		boolean isTicketDeleted = homePage.goToLoginPage()
				.loginSuccess(loginWithNewAccount)
				.goToBookTicketPageWithLogin()
				.generateBookTicket(ticketInformation)
				.goToMyTicketPage()
				.deleteTicket("Depart Station", "Arrive Station", "Depart Date", ticketInformation)
				.isDeletedTicketDisappeared("Depart Station", "Arrive Station", "Depart Date", ticketInformation);

		//Bug report
		System.out.println("This test case is failed: The RailWay do not allow delete Ticket");
		
		// Validate the canceled ticket is disappeared.
		Assert.assertTrue(isTicketDeleted, "The ticket user deleted is not disappeared");
		
	}

}
