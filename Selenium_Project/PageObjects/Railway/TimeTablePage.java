package Railway;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Utilities;
import Constant.Constant;

public class TimeTablePage extends GeneralPage{
	
	private final By tblBodyRows = By.xpath("//table[@class='MyTable WideTable']/tbody/tr");
	private final By txtCellOfColumn(int index, String item){return By.xpath("//table[@class='MyTable WideTable']//tr["+index+"]/td[count(//tr[@class='TableSmallHeader']/th[text()='"+item+"']//preceding-sibling::th)+1]");}		
	private final By lnkBookTicket(int index){return By.xpath("//table[@class='MyTable WideTable']//tr["+index+"]/td/a[contains(@href,'BookTicket')]");}
	
	protected WebElement getTxtCellOfColumn(int index, String item){return Constant.WEBDRIVER.findElement(txtCellOfColumn(index, item));}
	protected List<WebElement> getTableBodyRows(){return Constant.WEBDRIVER.findElements(tblBodyRows);}	
	protected WebElement getLnkBookTicket(int index) {return Constant.WEBDRIVER.findElement(this.lnkBookTicket(index));}
	
	
	public BookTicketPage clickToBookTicket(String departStationColumnName, String arriveStationColumnName, String departFromValue, String arriveAtValue) {
		int rowsCount = this.getTableBodyRows().size();
		for(int index = 1; index <= rowsCount; index++) {
			if(this.getTxtCellOfColumn(index, departStationColumnName).getText().equals(departFromValue) && this.getTxtCellOfColumn(index, arriveStationColumnName).getText().equals(arriveAtValue)){
				Utilities.moveOver(getLnkBookTicket(index));
				this.getLnkBookTicket(index).click();
				break;
			}
		}
		return new BookTicketPage();
	}
}
