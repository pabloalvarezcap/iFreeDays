package es.achosoftware.ifreedays.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import es.achosoftware.ifreedays.model.MonthVacationCalendar;
import es.achosoftware.ifreedays.model.User;
import es.achosoftware.ifreedays.model.Vacation;
import es.achosoftware.ifreedays.repository.UserRepository;
import es.achosoftware.ifreedays.repository.VacationRepository;

@Controller
public class UserDetailsController {
	@Autowired
	private VacationRepository vacationRepository;
	@Autowired
	private UserRepository userRepository;
	private final String year = new Integer(LocalDate.now().get(ChronoField.YEAR)).toString();
	
	@GetMapping("/admin/employees/details/{id}")
	public ModelAndView myVacations(@PathVariable("id")String id,@RequestParam(name = "year", required = false, defaultValue = "CHANGE ME") String year) {
		if (year.equals("CHANGE ME")) {
			year = this.year;
		}
		int _year = Integer.parseInt(year);
		ModelAndView modelAndView = new ModelAndView();
		User user = userRepository.findById(Integer.parseInt(id));
        List<Vacation> vacations = vacationRepository.findVacationsByUserId(user.getId());
        List<MonthVacationCalendar> monthVacationCalendarList = new ArrayList<>();
        for (int x = 0; x<=11; ++x) {
        	monthVacationCalendarList.add(new MonthVacationCalendar(_year, x, vacations));
        }
        modelAndView.addObject("user",user);
        modelAndView.addObject("isAdmin", true);
        modelAndView.addObject("name", user.getName()+" "+user.getLastName());
        modelAndView.addObject("monthList", monthVacationCalendarList);
        modelAndView.setViewName("admin/usersVacations");
        modelAndView.addObject("dateString", Integer.toString(_year));

		modelAndView.addObject("prev", "?year=" + Integer.toString(_year-1));
		modelAndView.addObject("next", "?year=" + Integer.toString(_year+1));

        
        
        return modelAndView;

	}
}
