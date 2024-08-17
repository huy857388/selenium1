package Railway;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import ActivateMail.MailBoxWelcomePage;
import Common.BrowserType;
import Common.Utilities;
import Constant.Constant;
import DriverWrapper.Driver;

public class TestBase {

	public HomePage homePage = new HomePage();
	protected MailBoxWelcomePage mailBoxWelcomePage = new MailBoxWelcomePage();
	protected PasswordChangeForm passwordChangeForm = new PasswordChangeForm();
	protected BookTicketPage bookTicketPage = new BookTicketPage();
	Account loginMail = new Account(Constant.MAIL_USERNAME,	Constant.MAIL_PASSWORD);

	@BeforeMethod()
	public void setUp() {
		Driver webDriver = new Driver();
		webDriver.getDriver(BrowserType.CHROME);
		Constant.WEBDRIVER.manage().window().maximize();
	}
	
	@AfterMethod()
	public void tearDown() {
		Constant.WEBDRIVER.quit();
	}

	public Account activeAccount()  
	{  
		Account registerAccount = new Account(Utilities.generateRandomEmail(10),Constant.NEW_EMAIL_PASSWORD,Constant.PID);
		homePage.open()
		.goToRegisterPage()
		.registerNewAccount(registerAccount);

		homePage.openActivateMailWithClosingRailway(0)
		.loginMail(loginMail)
		.activateNewUserRegistered(registerAccount.getEmail(),1);
		
		return registerAccount;

	}  

}
