package es.achosoftware.ifreedays.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import es.achosoftware.ifreedays.model.MonthCalendar;
import es.achosoftware.ifreedays.model.Skill;
import es.achosoftware.ifreedays.model.User;
import es.achosoftware.ifreedays.service.CalendarService;
import es.achosoftware.ifreedays.service.SkillService;
import es.achosoftware.ifreedays.service.UserService;

@Controller
public class CalendarController {

	@Autowired
	private SkillService skillService;
	@Autowired
	private CalendarService calendarService;
	@Autowired
	private UserService userService;
	private static final DateFormat df = new SimpleDateFormat("MMMM yyyy", Locale.US);
	
	@RequestMapping(value =  "/calendars/skills", method = RequestMethod.GET)
	public ModelAndView calendarForDate() {
		Integer year = Calendar.getInstance().get(Calendar.YEAR);
		Integer month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		return calendarForDate(year, month);
	}

	@RequestMapping(value =  "/calendars/skills/{year}", method = RequestMethod.GET)
	public ModelAndView calendarForDate(@PathVariable("year") Integer year) {
		Integer theYear = checkYear(year);
		Integer month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		return calendarForDate(theYear, month);
	}

	@RequestMapping(value =  "/calendars/skills/{year}/{month}", method = RequestMethod.GET)
	public ModelAndView calendarForDate(@PathVariable("year") Integer year, @PathVariable("month") Integer month) {
		Integer theYear = checkYear(year);
		Integer theMonth = checkMonth(month);
		List<Skill> skills = skillService.listSkills();
		List<MonthCalendar> vacations = new ArrayList<>();
		for (Skill s : skills) {
			vacations.add(calendarService.calendarForSkillAndDate(s, theMonth, theYear));
		}
		Calendar cal = getCalendar(theYear, theMonth);
		
		List<Integer> daysOfMonth = getDaysOfMonth(cal);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("calendars/skills");
		modelAndView.addObject("skills", skills);
		modelAndView.addObject("calendars", vacations);
		modelAndView.addObject("dateString", StringUtils.capitalize(df.format(cal.getTime())));
		modelAndView.addObject("daysOfMonth", daysOfMonth);
		modelAndView.addObject("year", theYear);
		modelAndView.addObject("month", theMonth - 1);
		cal.add(Calendar.MONTH, -1);
		modelAndView.addObject("prev", cal.get(Calendar.YEAR) + "/" + (cal.get(Calendar.MONTH) + 1));
		cal.add(Calendar.MONTH, 2);
		modelAndView.addObject("next", cal.get(Calendar.YEAR) + "/" + (cal.get(Calendar.MONTH) + 1));
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("isAdmin", user.isAdmin());
		return modelAndView;
	}

	private Calendar getCalendar(Integer theYear, Integer theMonth) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.MONTH, theMonth - 1);
		cal.set(Calendar.YEAR, theYear);
		return cal;
	}

	private List<Integer> getDaysOfMonth(Calendar cal) {
		List<Integer> daysOfMonth = new ArrayList<>();
		int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		for (int day = firstDay; day <= lastDay; day++) {
			daysOfMonth.add(day);
		}
		return daysOfMonth;
	}

	private Integer checkMonth(Integer month) {
		Integer result = month;
		if (month == null || month.equals(0)) {
			result = Calendar.getInstance().get(Calendar.MONTH) + 1;
		}
		return result;
	}

	private Integer checkYear(Integer year) {
		Integer result = year;
		if (year == null || year.equals(0)) {
			result = Calendar.getInstance().get(Calendar.YEAR);
		}
		return result;
	}
}
