package es.achosoftware.ifreedays.model;

import java.util.List;

public class DaySkill {

	private Integer skillId;
	private Integer day;
	private Integer state;
	private List<Integer> people;
	private int skilledBusyPeople;

	public int getN() {
		return skilledBusyPeople;
	}

	public void setN(int n) {
		this.skilledBusyPeople = n;
	}

	public DaySkill(Integer skillId, Integer day, int state, List<Integer> people) {
		this.skillId = skillId;
		this.day = day;
		this.state = state;
		this.people = people;
	}
	
	
	public DaySkill(Integer skillId, Integer day, int state, List<Integer> people, int skilledBusyPeople) {
		this.skillId = skillId;
		this.day = day;
		this.state = state;
		this.people = people;
		this.skilledBusyPeople = skilledBusyPeople;
	}
	

	public Integer getSkillId() {
		return skillId;
	}

	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public List<Integer> getPeople() {
		return people;
	}

	public void setPeople(List<Integer> people) {
		this.people = people;
	}

}
