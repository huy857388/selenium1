package Railway;

public class TicketInformation {
	private String departDate;
	private String departFrom;
	private String arriveAt;
	private String seatType;
	private int ticketAmount;
	
	
	public TicketInformation(String departDate, String departFrom, String arriveAt, String seatType, int ticketAmount){
		this.departDate = departDate;
		this.departFrom = departFrom;
		this.arriveAt = arriveAt;
		this.seatType = seatType;
		this.ticketAmount = ticketAmount;
	}
	
	public TicketInformation(){

	}
	
	public void setDepartDate(String departDate) {
		this.departDate = departDate;
	}
	
	public void setDepartFrom(String departFrom) {
		this.departFrom = departFrom;
	}
	
	public void setArriveAt(String arriveAt) {
		this.arriveAt = arriveAt;
	}
	
	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}
	
	public void setTicketAmount(int ticketAmount){
		this.ticketAmount = ticketAmount;
	}
	
	public String getDepartDate() {
		return this.departDate;
	}
	
	public String getDepartFrom() {
		return this.departFrom;
	}
	
	public String getArriveAt() {
		return this.arriveAt;
	}
	
	public String getSeatType() {
		return this.seatType;
	}
	
	public int getTicketAmount() {
		return this.ticketAmount;
	}
	
}
