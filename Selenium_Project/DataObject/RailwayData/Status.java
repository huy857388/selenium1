package RailwayData;

public enum Status {
	PAID("Paid"), NEW("New"), EXPIRED("Expired");
	
	private String field;

	private Status(String field) {
		this.field = field;
	}

	public String getField() {
		return field;
	}
}
