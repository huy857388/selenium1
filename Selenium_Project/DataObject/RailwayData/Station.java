package RailwayData;

import java.util.Random;

public enum Station {
	SAI_GON("Sài Gòn"), PHAN_THIET("Phan Thiết"), NHA_TRANG("Nha Trang"), 
	DA_NANG("Đà Nẵng"), HUE("Huế"), QUANG_NGAI("Quảng Ngãi");
	
	private String field;
	
	private Station(String value) {
		this.field = value;
	}
	
	public String getField() {
		return field;
	}
	
	public static Station selectRandomStationType() {
		int index = new Random().nextInt(Station.values().length);
		return Station.values()[index];
	}
	
	public static Station selectRandomStationTypeExcludeValue(String value) {
		int index = new Random().nextInt(Station.values().length);
		if(Station.values()[index].toString() != value) {
			return Station.values()[index];
		}else
			return selectRandomStationTypeExcludeValue(value);
	}
}
