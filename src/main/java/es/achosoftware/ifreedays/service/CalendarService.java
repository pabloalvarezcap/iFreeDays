package es.achosoftware.ifreedays.service;

import java.util.List;

import es.achosoftware.ifreedays.model.MonthCalendar;
import es.achosoftware.ifreedays.model.Skill;
import es.achosoftware.ifreedays.model.Vacation;

public interface CalendarService {
	List<Vacation> calendarForSkill(int skillId);
	MonthCalendar calendarForSkillAndDate(Skill skill, int month, int year);
	List<Vacation> findAll();	
}
