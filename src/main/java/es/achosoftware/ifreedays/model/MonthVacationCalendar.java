package es.achosoftware.ifreedays.model;

import java.text.DateFormatSymbols;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

public class MonthVacationCalendar {

	private int month;
	private int year;
	private List<Vacation> vacations;
	private List<DayVacation> days;

	public MonthVacationCalendar(int year, int month, List<Vacation> vacations) {
		this.year = year;
		this.month = month;
		this.month = month;
		this.days = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);

		int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		this.vacations = calculateDays(vacations, month, year);
		
		for (int day = firstDay; day <= lastDay; day++) {
			if (isVacation(this.vacations, day)) {
				days.add(new DayVacation(true, day));
			} else {
				days.add(new DayVacation(false, day));
			}
		}

	}

	private List<Vacation> calculateDays(List<Vacation> vacations, final int month, final int year) {
		Calendar c = Calendar.getInstance();
		return vacations.stream().filter(o -> {
			c.setTime(o.getDay());
			return c.get(Calendar.MONTH) == month && c.get(Calendar.YEAR) == year;
		}).collect(Collectors.toList());
	}

	private Boolean isVacation(List<Vacation> days, int day) {
		Calendar c = Calendar.getInstance();
		for (Vacation v: days) {
			c.setTime(v.getDay());
			if (c.get(Calendar.DAY_OF_MONTH) == day) {
				return true; 
			}
		}
		return false;
	}

	
	public String getMonthForInt(int num) {
        String month = "";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        if (num >= 0 && num <= 11 ) {
            month = months[num];
        }
        return month;
    }
	
	
	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public List<Vacation> getVacations() {
		return vacations;
	}

	public void setVacations(List<Vacation> vacations) {
		this.vacations = vacations;
	}

	public List<DayVacation> getDays() {
		return days;
	}

	public void setDays(List<DayVacation> days) {
		this.days = days;
	}

	
	
}
