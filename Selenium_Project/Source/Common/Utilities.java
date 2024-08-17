package Common;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import Constant.Constant;
import RailwayData.Seat;
import RailwayData.Station;
import Railway.TicketInformation;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utilities {
	
	public static String getProjectPath() {	
		return System.getProperty("user.dir");
	}

	public static String getURL() {
		return Constant.WEBDRIVER.getCurrentUrl();
	}
	
	public static String  getDateInFuture(int days)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, days);
		Date futureDateTime = calendar.getTime();
		SimpleDateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
		String futureDate = dateFormat.format(futureDateTime).toString();
		return futureDate;
	}
	
	public static String getSubString(String string) {
		String subString = string.substring( 0, string.indexOf("."));
		return subString;
	}
	
	public static String getActivationCode(String string) {
		String separator =": ";
		int sepPos = string.indexOf(separator);
		if (sepPos == -1) {
	         System.out.println("");
	   }
	return string.substring(sepPos + separator.length());
	}
	
	public static String generateRandomEmail(int length) {
		String alphabet = "abcdefghijklmnopqrstuvwyzABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		
		for(int i = 0; i < length; i++) {
		   int index = random.nextInt(alphabet.length());
		   char randomChar = alphabet.charAt(index);
		   sb.append(randomChar);
		}

		
		String randomString = sb.toString();
		return randomString + generateRandomDomain(10);
	}
	
	public static String generateRandomDomain(int length) {
		String alphabet = "abcdefghijklmnopqrstuvwyz1234567890";
		StringBuilder sb = new StringBuilder();
		Random random = new Random();		
		for(int i = 0; i < length; i++) {
			   int index = random.nextInt(alphabet.length());
			   char randomChar = alphabet.charAt(index);
			   sb.append(randomChar);
			}
		String randomString = sb.toString();
		
		String[] domains = {"de","com","in","en","us","guru","vn","lnk","ml"};
		int idx = new Random().nextInt(domains.length);
        String domain = (domains[idx]).toString();
        
		return "@"+randomString+"."+domain;
	}
		
	public static void waitForElementPresent(By by, int timeInSecond) {
		WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, timeInSecond);
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
	}
	
	public static void doubleClick(WebElement webElement) {
		Actions actions = new Actions(Constant.WEBDRIVER);
		WebElement element = webElement;
		actions.doubleClick(element).perform();
	}
	
	public static void switchToTab(int index) {
		ArrayList<String> tabs = new ArrayList<String> (Constant.WEBDRIVER.getWindowHandles());
		Constant.WEBDRIVER.switchTo().window(tabs.get(index));
	}
	
	public static void switchToAlert(boolean isAccept) {
		Alert alert = Constant.WEBDRIVER.switchTo().alert();
		if(isAccept == true) {
			alert.accept();
		}else {
			alert.dismiss();
		}
	}
	
	
	public static void waitForDropDownListPopulated(By by, int timeInSecond) {
		WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, timeInSecond);
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.presenceOfElementLocated(by)));
	}
	
	public static void selectItemInDropDownList(By by, String txtSelected, int timeInSecond) {
		try {
			waitForDropDownListPopulated(by, timeInSecond);
			Select dropDownList = new Select(Constant.WEBDRIVER.findElement(by));
			dropDownList.selectByVisibleText(txtSelected);
		}catch (StaleElementReferenceException e)
		{
			selectItemInDropDownList(by, txtSelected, timeInSecond);
		}
		
	}
	
	public static boolean isElementPresent(By by) {
		try {
			Constant.WEBDRIVER.findElement(by);
			return true;
		}catch(NoSuchElementException e) {
			return false;
		}
	}
	
	public static boolean isSelectedDropDownListValueDisplayed(By by, String value) {
		Select dropDownList = new Select(Constant.WEBDRIVER.findElement(by));
		String selectedValue = dropDownList.getFirstSelectedOption().getText();
		if(selectedValue.equals(value)) {
			return true;
		}else {
			return false;
		}
	}
	
	public static String selectRandomValueInDropDownList(By by, int timeInSecond) {		
		try
		{
			Select dropDownList = new Select(Constant.WEBDRIVER.findElement(by));
			List<WebElement> webElementList = dropDownList.getOptions();
			int size = webElementList.size();
			Random num = new Random();
			int i = num.nextInt(size);
			dropDownList.selectByIndex(i);
			waitForDropDownListPopulated(by, timeInSecond);
			return dropDownList.getFirstSelectedOption().getText();
		}
		catch (StaleElementReferenceException e)
		{
			return selectRandomValueInDropDownList(by, timeInSecond);
		}
	}
	
	public static void waitUntilElementVisible(WebElement element) {
		WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Constant.WAITING_TIME);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public static void moveOver(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;       		
        js.executeScript("arguments[0].scrollIntoView();", element);
	}
	
	public static ArrayList<TicketInformation> addValueToBlankTickets(ArrayList<TicketInformation> ticketInformationList, int times) {
		for(int i = 1; i <= times; i++ ) {
			String randomDepartFrom = Station.selectRandomStationType().getField();
			String randomArriveAt = Station.selectRandomStationTypeExcludeValue(randomDepartFrom).getField();
			TicketInformation ticket = new TicketInformation(Utilities.getDateInFuture(i+3), randomDepartFrom,
					randomArriveAt,
					Seat.selectRandomSeatType().getField(),
					1);
			ticketInformationList.add(ticket);
		}
		return ticketInformationList;
	}
	
	public static TicketInformation addValueToOneTicket(TicketInformation ticketInformationList) {
		String randomDepartFrom = Station.selectRandomStationType().getField();
		String randomArriveAt = Station.selectRandomStationTypeExcludeValue(randomDepartFrom).getField();
		TicketInformation ticket = new TicketInformation(Utilities.getDateInFuture(4), randomDepartFrom,
				randomArriveAt,
				Seat.selectRandomSeatType().getField(),
				1);
		return ticket;
	}

	public static void waitForDataLoad(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}



}

	
