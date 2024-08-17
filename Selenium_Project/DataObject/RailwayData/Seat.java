package RailwayData;

import java.util.Random;

public enum Seat {
	HS("Hard seat"), SS("Soft seat"),
	SSC("Soft seat with air conditioner"),
	HB("Hard bed"), SB("Soft bed"),
	SBC("Soft bed with air conditioner");
	
	String field;

	private Seat(String field) {
		this.field = field;
	}

	public String getField() {
		return field;
	}
	
	public static Seat selectRandomSeatType() {
		int index = new Random().nextInt(Seat.values().length);
		return Seat.values()[index];
	}
 
}
