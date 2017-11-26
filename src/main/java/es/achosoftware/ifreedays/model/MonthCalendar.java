package es.achosoftware.ifreedays.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

public class MonthCalendar {

	private int skillId;
	private String skillName;
	private int skilledPeople;
	private List<DaySkill> month;
	
	public MonthCalendar(int year, int month, int skilledPeople, List<Vacation> vacations, Skill skill) {
		this.skillId = skill.getId();
		this.skillName = skill.getName();
		this.skilledPeople = skilledPeople;
		this.month = new ArrayList<>();
		this.setSkillName(skillName);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month-1);
		
		
		int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		for (int day = firstDay; day <= lastDay; day++) {			
			List<Vacation> days = calculateDays(vacations, day);
			List<Integer> people = new ArrayList<>();
			days.forEach(o -> people.add(o.getUserid()));
			int state = calculateState(skilledPeople, days);
			this.month.add(new DaySkill(skillId, day, state, people));
		}

	}

	private List<Vacation> calculateDays(List<Vacation> vacations, final int aux) {
		Calendar c = Calendar.getInstance();
		return vacations.stream().filter(o -> {c.setTime(o.getDay()); return c.get(Calendar.DAY_OF_MONTH) == aux;}).collect(Collectors.toList());
	}

	private int calculateState(int skilledPeople, List<Vacation> days) {
		int state = 0;
		int busy = days.size();
		if (skilledPeople == 0) {
			state = -1;
		} else if (busy > 0 && busy < skilledPeople) {
			state = 1;
		} else if (busy == skilledPeople){
			state = 2;
		}
		return state;
	}

	public int getSkillId() {
		return skillId;
	}
	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}
	public int getSkilledPeople() {
		return skilledPeople;
	}
	public void setSkilledPeople(int skilledPeople) {
		this.skilledPeople = skilledPeople;
	}
	public List<DaySkill> getMonth() {
		return month;
	}
	public void setMonth(List<DaySkill> month) {
		this.month = month;
	}
	public String getSkillName() {
		return skillName;
	}
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
}
