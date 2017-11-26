package es.achosoftware.ifreedays.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import es.achosoftware.ifreedays.model.MonthCalendar;
import es.achosoftware.ifreedays.model.Skill;
import es.achosoftware.ifreedays.service.CalendarService;
import es.achosoftware.ifreedays.service.SkillService;

@Controller
public class CalendarController {

	@Autowired
	private SkillService skillService;
	@Autowired
	private CalendarService calendarService;
	private static final DateFormat df = new SimpleDateFormat("MMMM yyyy", Locale.US);
	
	@RequestMapping(value =  "/calendars/skills", method = RequestMethod.GET)
	public ModelAndView calendarForDate() {
		Integer year = Calendar.getInstance().get(Calendar.YEAR);
		Integer month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		return calendarForDate(year, month);
	}

	@RequestMapping(value =  "/calendars/skills/{year}", method = RequestMethod.GET)
	public ModelAndView calendarForDate(@PathVariable("year") Integer year) {
		if (year == null || year.equals(0)) {
			year = Calendar.getInstance().get(Calendar.YEAR);
		}
		Integer month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		return calendarForDate(year, month);
	}

	@RequestMapping(value =  "/calendars/skills/{year}/{month}", method = RequestMethod.GET)
	public ModelAndView calendarForDate(@PathVariable("year") Integer year, @PathVariable("month") Integer month) {
		if (year == null || year.equals(0)) {
			year = Calendar.getInstance().get(Calendar.YEAR);
		}
		if (month == null || month.equals(0)) {
			month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		}
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("calendars/skills");
		List<Skill> skills = skillService.listSkills();
		modelAndView.addObject("skills", skills);
		List<MonthCalendar> vacations = new ArrayList<>();
		for (Skill s : skills) {
			vacations.add(calendarService.calendarForSkillAndDate(s, month, year));
		}
		modelAndView.addObject("calendars", vacations);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.YEAR, year);
		modelAndView.addObject("dateString", StringUtils.capitalize(df.format(cal.getTime())));
		
		List<Integer> daysOfMonth = new ArrayList<>();
		int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		for (int day = firstDay; day <= lastDay; day++) {
			daysOfMonth.add(day);
		}
		modelAndView.addObject("daysOfMonth", daysOfMonth);
		modelAndView.addObject("year", year);
		modelAndView.addObject("month", month - 1);
		cal.add(Calendar.MONTH, -1);
		modelAndView.addObject("prev", cal.get(Calendar.YEAR) + "/" + (cal.get(Calendar.MONTH) + 1));
		cal.add(Calendar.MONTH, 2);
		modelAndView.addObject("next", cal.get(Calendar.YEAR) + "/" + (cal.get(Calendar.MONTH) + 1));
		return modelAndView;
	}
}
