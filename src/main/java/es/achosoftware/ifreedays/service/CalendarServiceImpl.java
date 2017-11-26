package es.achosoftware.ifreedays.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.achosoftware.ifreedays.model.MonthCalendar;
import es.achosoftware.ifreedays.model.Skill;
import es.achosoftware.ifreedays.model.User;
import es.achosoftware.ifreedays.model.Vacation;
import es.achosoftware.ifreedays.repository.UserRepository;
import es.achosoftware.ifreedays.repository.VacationRepository;

@Service("calendarService")
public class CalendarServiceImpl implements CalendarService {

	@Autowired
	private VacationRepository vacationRepository;
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<Vacation> calendarForSkill(int skillId) {
		List<Vacation> vacations = vacationRepository.findBySkills(skillId);
		vacations.forEach(o -> o.setSkillId(skillId));
		return vacations;
	}

	@Override
	public MonthCalendar calendarForSkillAndDate(Skill skill, int month, int year) {
		List<Vacation> vacations = vacationRepository.findBySkills(skill.getId(), month, year);
		List<User> users = userRepository.findBySkills(skill.getId());
		vacations.forEach(o -> o.setSkillId(skill.getId()));
		return new MonthCalendar(year, month, users.size(), vacations, skill);
	}

	@Override
	public List<Vacation> findAll() {
		return vacationRepository.findAll();
	}



}
