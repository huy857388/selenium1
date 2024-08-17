package Railway;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Utilities;
import Constant.Constant;

public class BookTicketPage extends GeneralPage{
	private final By dDListDepartDate = By.xpath("//select[@name = 'Date']");
	private final By dDListDepartFrom = By.xpath("//select[@name = 'DepartStation']");
	private final By dDListarriveAt = By.xpath("//select[@name = 'ArriveStation']");
	private final By dDListseatType = By.xpath("//select[@name = 'SeatType']");
	private final By dDListticketAmount = By.xpath("//select[@name = 'TicketAmount']");
	private final By btnBookTicket = By.xpath("//input[@type='submit']");
	private final By lblBookTicketSuccess = By.xpath("//h1");
	private final By txtTicketItem(String item) {return By.xpath("//table[@class='MyTable WideTable']//tr[@class='OddRow']/td[text()='"+item+"']");}	
	
	
	protected WebElement getBtnBookTicket() {return Constant.WEBDRIVER.findElement(btnBookTicket);}
	protected WebElement getLblBookTicketSuccess() {return Constant.WEBDRIVER.findElement(lblBookTicketSuccess);}
	protected WebElement getTxtTicketItemInformation(String item) {return Constant.WEBDRIVER.findElement(txtTicketItem(item));}
	
	public BookTicketPage bookTicket(TicketInformation ticketInformation) {
		ticketInformation.setDepartDate(Utilities.selectRandomValueInDropDownList(dDListDepartDate, Constant.TIME_IN_SECOND));
		Utilities.selectItemInDropDownList(dDListDepartFrom, ticketInformation.getDepartFrom(), Constant.TIME_IN_SECOND);
		Utilities.waitForDataLoad(1000);
		Utilities.selectRandomValueInDropDownList(dDListarriveAt, Constant.TIME_IN_SECOND);
		Utilities.selectItemInDropDownList(dDListseatType, ticketInformation.getSeatType(), Constant.TIME_IN_SECOND);
		Utilities.selectItemInDropDownList(dDListticketAmount, String.valueOf(ticketInformation.getTicketAmount()), Constant.TIME_IN_SECOND);
		Utilities.moveOver(getBtnBookTicket());
		this.getBtnBookTicket().click();
		return this;
	}
	
	public BookTicketPage bookListTicket(ArrayList<TicketInformation> ticketInformationList) {
		for(int i = 0; i < ticketInformationList.size(); i++ ) {
			Utilities.selectItemInDropDownList(dDListDepartDate, ticketInformationList.get(i).getDepartDate(), Constant.TIME_IN_SECOND);
			Utilities.selectItemInDropDownList(dDListDepartFrom, ticketInformationList.get(i).getDepartFrom(), Constant.TIME_IN_SECOND);
			Utilities.waitForDataLoad(1000);
			Utilities.selectItemInDropDownList(dDListseatType, ticketInformationList.get(i).getSeatType(), Constant.TIME_IN_SECOND);
			Utilities.selectItemInDropDownList(dDListticketAmount, String.valueOf(ticketInformationList.get(i).getTicketAmount()), Constant.TIME_IN_SECOND);
			Utilities.selectRandomValueInDropDownList(dDListarriveAt, Constant.TIME_IN_SECOND);
			Utilities.moveOver(getBtnBookTicket());
			this.getBtnBookTicket().click();
			Utilities.waitForElementPresent(txtTicketItem(ticketInformationList.get(i).getDepartDate()), Constant.TIME_IN_SECOND);
			this.getTabMenu(Constant.BOOKTICKET_PAGE).click();
		}
		return this;
	}
	
	public BookTicketPage generateBookTicket(TicketInformation ticketInformation) {
		ticketInformation.setDepartDate(Utilities.selectRandomValueInDropDownList(dDListDepartDate, Constant.TIME_IN_SECOND));
		ticketInformation.setDepartFrom(Utilities.selectRandomValueInDropDownList(dDListDepartFrom, Constant.TIME_IN_SECOND));
		ticketInformation.setArriveAt(Utilities.selectRandomValueInDropDownList(dDListarriveAt, Constant.TIME_IN_SECOND));
		ticketInformation.setSeatType(Utilities.selectRandomValueInDropDownList(dDListseatType, Constant.TIME_IN_SECOND));
		ticketInformation.setTicketAmount(Integer.valueOf(Utilities.selectRandomValueInDropDownList(dDListticketAmount, Constant.TIME_IN_SECOND)));
		Utilities.moveOver(getBtnBookTicket());		
		this.getBtnBookTicket().click();
		return this;
	}
	
	
	
	public boolean isTicketDepartDateBookedCorrect(TicketInformation ticket) {
		if(this.getTxtTicketItemInformation(ticket.getDepartDate()).getText().equals(ticket.getDepartDate())) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isTicketDepartFromBookedCorrect(TicketInformation ticket) {
		if(this.getTxtTicketItemInformation(ticket.getDepartFrom()).getText().equals(ticket.getDepartFrom())) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isTicketArriveAtBookedCorrect(TicketInformation ticket) {
		if(this.getTxtTicketItemInformation(ticket.getArriveAt()).getText().equals(ticket.getArriveAt())) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isTicketSeatTypeBookedCorrect(TicketInformation ticket) {
		if(this.getTxtTicketItemInformation(ticket.getSeatType()).getText().equals(ticket.getSeatType())) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isTicketTicketAmountBookedCorrect(TicketInformation ticket) {
		if(this.getTxtTicketItemInformation(String.valueOf(ticket.getTicketAmount())).getText().equals(String.valueOf(ticket.getTicketAmount()))) {
			return true;
		}else {
			return false;
		}
	}
	
	public String getTxtBookTicketSuccess() {
		return this.getLblBookTicketSuccess().getText();
	}
	
	public boolean isSelectedDepartFromValueCorrect(String value) {
		if(Utilities.isSelectedDropDownListValueDisplayed(dDListDepartFrom, value) == true) {
			return true;
		}
		return false;
	}
	
	public boolean isSelectedArriveAtValueCorrect(String value) {
		if(Utilities.isSelectedDropDownListValueDisplayed(dDListarriveAt, value) == true) {
			return true;
		}
		return false;
	}
}
