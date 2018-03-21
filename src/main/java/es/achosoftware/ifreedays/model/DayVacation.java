package es.achosoftware.ifreedays.model;

public class DayVacation {

	private Boolean isVacation;
	private int day;

	public DayVacation(Boolean isVacation, int day) {
		super();
		this.isVacation = isVacation;
		this.day = day; 
	}

	public Boolean getIsVacation() {
		return isVacation;
	}

	public int getDay() {
		return day;
	}

}
