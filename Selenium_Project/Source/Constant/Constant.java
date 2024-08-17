package Constant;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;

import Common.Utilities;

public class Constant {
	public static WebDriver WEBDRIVER;
	public static final int TIME_IN_SECOND = 30;
	public static final String RAILWAY_URL = "http://railwaysg2.somee.com/Page/HomePage.cshtml";
	public static final String USERNAME = "huy.minh.pham@logigear.com";
	public static final String PASSWORD = "12345678";
	public static final String INVALID_PASSWORD = Utilities.generateRandomEmail(10);
	public static final String LOGIN_PAGE = "/Account/Login.cshtml";
	public static final String LOGOUT_PAGE = "/Account/Logout";
	public static final String BOOKTICKET_PAGE = "/Page/BookTicketPage.cshtml";
	public static final String MYTICKET_PAGE = "/Page/ManageTicket.cshtml";
	public static final String CHANGE_PASSWORD_PAGE = "/Account/ChangePassword.cshtml";
	public static final String PASSWORD_CHANGE_FORM = "/Account/PasswordReset";
	public static final String REGISTER_PAGE = "/Account/Register.cshtml";
	public static final String TIME_TABLE_PAGE = "TrainTimeListPage.cshtml";
	public static final String DRIVER_PATH = System.getProperty("user.dir");
	public static final long WAITING_TIME = 30;
	public static final String IGNORE = "Ignore";
	
	// Constants for create new account
	public static final String EMAIL = Utilities.generateRandomEmail(10);
	public static final String NEW_EMAIL_PASSWORD = "blake123";
	public static final String INVALID_CONFIRM_NEW_EMAIL_PASSWORD = "111111111";
	public static final String PID = "159753852";
	
	// Constants for change password
	public static final String PASSWORD_CHANGING = "blake123456";
		
	// Constants for reset password
	public static final String NEW_RESET_PASSWORD = "123456789";
	public static final String INVALID_CONFIRM_NEW_RESET_PASSWORD = "12345670";
	
	// Constants for activation
	public static final String ACTIVATE_MAIL_URL = "http://mail.trainingliving.com/webmail";
	public static final String MAIL_USERNAME = "safe.railway@trainingliving.com";
	public static final String MAIL_PASSWORD = "Logigear123!!";
	
	// Constant for book ticket
	public static final String DEPART_DATE = Utilities.getDateInFuture(30);
	public static final String DEPART_FROM = "Sài Gòn";
	public static final String ARRIVE_AT = "Nha Trang";
	public static final String SEAT_TYPE = "Soft bed with air conditioner";
	public static final int TICKET_AMOUNT = 1;
	
	
	// Constant for click to book ticket link in TimeTable Page
	public static final String DEPART_STATION = "Huế";
	public static final String ARRIVE_STATION = "Sài Gòn";
	 
	// Side Bar mail Menu
	public static final String MAIL = "mail selected";
	
	public static final String BOOK_TICKET_SUCCESS = "Ticket booked successfully!";
	
	// Creates the logger.
	public static final Logger createLogger(String className) {
		return Logger.getLogger(className);
	}

}
