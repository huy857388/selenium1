package Railway;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Utilities;
import Constant.Constant;


public class MyTicketPage extends GeneralPage{
	
	private final By txtMyTicketPageTitle = By.xpath("//h1[text()='Manage ticket']");
	private final By tblBodyRows = By.xpath("//table[@class='MyTable']/tbody/tr[@class != 'TableSmallHeader']");
	private final By txtCellOfColumn(int index, String item){return By.xpath("//table[@class='MyTable']//tr["+(index+1)+"]/td[count(//tr[@class='TableSmallHeader']/th[text()='"+item+"']//preceding-sibling::th)+1]");}
	private final By btnCancel(int index) {return By.xpath("//table[@class='MyTable']//tr["+(index+1)+"]//input[@value='Cancel']");}
	private final By txtDepartDateFilter = By.xpath("//input[@name='FilterDpDate']");
	private final By btnFilter = By.xpath("//input[@type='submit' and @value='Apply filter']");
	private final By dDFilterDepartFrom = By.xpath("//select[@name = 'FilterDpStation']");
	private final By dDFilterArriveAt = By.xpath("//select[@name = 'FilterArStation']");
	private final By dDFilterStatus = By.xpath("//select[@name = 'FilterStatus']");
	
	protected WebElement getTxtCellOfColumn(int index, String item){return Constant.WEBDRIVER.findElement(txtCellOfColumn(index, item));}
	protected List<WebElement> getTableBodyRows(){return Constant.WEBDRIVER.findElements(tblBodyRows);}	
	protected WebElement getMyTicketPageTxtTitle() {return Constant.WEBDRIVER.findElement(txtMyTicketPageTitle);}
	protected WebElement getBtnCancel(int index) {return Constant.WEBDRIVER.findElement(btnCancel(index));}
	protected WebElement getTxtDepartDateFilter() {return Constant.WEBDRIVER.findElement(txtDepartDateFilter);}
	protected WebElement getBtnFilter() {return Constant.WEBDRIVER.findElement(btnFilter);}
	protected WebElement getdDFilterDepartFrom() {return Constant.WEBDRIVER.findElement(dDFilterDepartFrom);}
	protected WebElement getdDFilterArriveAt() {return Constant.WEBDRIVER.findElement(dDFilterArriveAt);}
	protected WebElement getdDFilterStatus() {return Constant.WEBDRIVER.findElement(dDFilterStatus);}

	
	
	public MyTicketPage filterMyTicket(String departStation, String arriveStation, String departDate, String status) {
	//	The kw is used to filter Ticket in the My Ticket Page
	//  departStation: input Depart Station, can be: Ignore
	//  arriveStation: input Arrive Station, can be: Ignore	
	//  departDate: input Depart Date, can be "" if don't filter this field
	//  status: input input Status, can be: Ignore

		Utilities.selectItemInDropDownList(dDFilterDepartFrom, departStation, Constant.TIME_IN_SECOND);
		Utilities.selectItemInDropDownList(dDFilterArriveAt, arriveStation, Constant.TIME_IN_SECOND);
		this.getTxtDepartDateFilter().sendKeys(departDate);	
		Utilities.selectItemInDropDownList(dDFilterStatus, status, Constant.TIME_IN_SECOND);
		this.getBtnFilter().click();
		return this;
	}
	
	public boolean isCorrectTicketFilterDisplay(String departDateColumnName, String departDate) {
		int rowsCount = this.getTableBodyRows().size();
		try {
			for(int index = 1; index <= rowsCount; index++) {
				if(this.getTxtCellOfColumn(index, departDateColumnName).getText().equals(departDate)) {
					return true;
				}
			} 
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	public boolean isMyTicketPageDisplayed() {
		return this.getMyTicketPageTxtTitle().isDisplayed();			
	}
	
	public MyTicketPage deleteTicket(String departStationColumnName, String arriveStationColumnName, String departDateColumnName, TicketInformation ticketInformation) {
		int rowsCount = this.getTableBodyRows().size();
		for(int index = 1; index <= rowsCount; index++) {
			if(this.getTxtCellOfColumn(index, departStationColumnName).getText().equals(ticketInformation.getDepartFrom())
				&& this.getTxtCellOfColumn(index, arriveStationColumnName).getText().equals(ticketInformation.getArriveAt())
				&& this.getTxtCellOfColumn(index, departDateColumnName).getText().equals(ticketInformation.getDepartDate())
			){
				Utilities.waitUntilElementVisible(getBtnCancel(index));
				Utilities.moveOver(getBtnCancel(index));
				this.getBtnCancel(index).click();
				Utilities.switchToAlert(true);
				break;
			}
		}
		return this;
	}
	
	public boolean isDeletedTicketDisappeared(String departStationColumnName, String arriveStationColumnName, String departDateColumnName, TicketInformation ticketInformation) {
		int rowsCount = this.getTableBodyRows().size();
		try {
			for(int index = 1; index <= rowsCount; index++) {
				if(this.getTxtCellOfColumn(index, departStationColumnName).getText().equals(ticketInformation.getDepartFrom())
					&& this.getTxtCellOfColumn(index, arriveStationColumnName).getText().equals(ticketInformation.getArriveAt())
					&& this.getTxtCellOfColumn(index, departDateColumnName).getText().equals(ticketInformation.getDepartDate())
				)
					break;			
			}
		}catch(Exception e){
			return true;
		}
		return false;
	}
	
}
