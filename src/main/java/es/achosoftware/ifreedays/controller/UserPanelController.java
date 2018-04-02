package es.achosoftware.ifreedays.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import es.achosoftware.ifreedays.model.MonthVacationCalendar;
import es.achosoftware.ifreedays.model.Skill;
import es.achosoftware.ifreedays.model.User;
import es.achosoftware.ifreedays.model.Vacation;
import es.achosoftware.ifreedays.repository.UserRepository;
import es.achosoftware.ifreedays.repository.VacationRepository;

@Controller
public class UserPanelController {
	
	@Autowired
	private VacationRepository vacationRepository;
	@Autowired
	private UserRepository userRepository;
	private final String year = new Integer(LocalDate.now().get(ChronoField.YEAR)).toString();
	
	@RequestMapping("/user/home")
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userRepository.findByEmail(auth.getName());
		modelAndView.addObject("isAdmin", user.isAdmin());
		modelAndView.addObject("userName",
				"Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
		modelAndView.setViewName("user/panel");
		modelAndView.addObject("userMessage", "Eres un fenómeno y lo sabes. 😁");
		return modelAndView;
	}
	
	@GetMapping("/user/requestVacation")
	public ModelAndView requestVacation() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userRepository.findByEmail(auth.getName());
		modelAndView.addObject("userName",
				"Choose your vacations " + user.getName() + " " + user.getLastName() +"!");
		modelAndView.addObject("isAdmin", user.isAdmin());
		modelAndView.setViewName("user/requestForm");
		return modelAndView;
	}
	
	@GetMapping("/user/viewVacations")
	@ResponseBody
	public List<Vacation> viewVacations() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userRepository.findByEmail(auth.getName());
		List<Vacation> vacations = vacationRepository.findVacationsByUserId(user.getId());
		return vacations;
	}
	
	@GetMapping("/user/myVacations")
	public ModelAndView myVacations(@RequestParam(name = "year", required = false, defaultValue = "CHANGE ME") String year) {
		if (year.equals("CHANGE ME")) {
			year = this.year;
		}
		int _year = Integer.parseInt(year);
		ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName());
        List<Vacation> vacations = vacationRepository.findVacationsByUserId(user.getId());
        List<MonthVacationCalendar> monthVacationCalendarList = new ArrayList<>();
        for (int x = 0; x<=11; ++x) {
        	monthVacationCalendarList.add(new MonthVacationCalendar(_year, x, vacations));
        }
        
        modelAndView.addObject("isAdmin", user.isAdmin());
        modelAndView.addObject("monthList", monthVacationCalendarList);
        modelAndView.setViewName("user/myVacations");
        modelAndView.addObject("dateString", Integer.toString(_year));

		modelAndView.addObject("prev", "?year=" + Integer.toString(_year-1));
		modelAndView.addObject("next", "?year=" + Integer.toString(_year+1));

        
        
        return modelAndView;

	}
	
	@PostMapping("/user/requestVacation")
	public ModelAndView requestVacationPost(@RequestParam(name="startDate") String startDate, @RequestParam(name="endDate") String endDate) {
		LocalDate _startDate = LocalDate.parse(startDate);
		LocalDate _endDate = LocalDate.parse(endDate);
		if(ChronoUnit.DAYS.between(_startDate, _endDate) >= 60) {
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("forward:/user/error");
			return modelAndView;
		}
		
		
		
		List<Vacation> _vacations= createVacation(_startDate, _endDate);
				
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userRepository.findByEmail(auth.getName());
		
		List<Vacation> vacations = vacationRepository.findVacationsByUserId(user.getId());
		
		for(Vacation v : _vacations) {
			if(vacations.size() == 0) {
				System.out.println("SE GUARDAN TODAS LAS VACACIONES");
				vacationRepository.save(_vacations);
				break;
			}
			int x = 0;
			for(Vacation v2 : vacations) {
				if(v.getDay().equals(v2.getDay())) {
					++x;
				}
			}
			if (x == 0) {
				vacationRepository.save(v);
			}
		}

		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("isAdmin", user.isAdmin());
		modelAndView.setViewName("user/requestForm");
		modelAndView.addObject("message", "Operation complete!");
		return modelAndView;
	}
	
	@GetMapping("/user/error")
	public ModelAndView errorTooManyVacations() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userRepository.findByEmail(auth.getName());
		modelAndView.addObject("isAdmin", user.isAdmin());
		modelAndView.setViewName("user/error");
		return modelAndView;
	}
	
	public List<Vacation> createVacation(LocalDate start, LocalDate end) {
		Long days = ChronoUnit.DAYS.between(start, end) + 1;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userRepository.findByEmail(auth.getName());
		Set<Skill> skills = user.getSkills();
		List<Vacation> vacations = new ArrayList<>();
		
		for(int x=0; x<days; x++) {
			for (Skill skill : skills) {
				Vacation v = new Vacation(user.getId(), java.sql.Date.valueOf(start.plus(x, ChronoUnit.DAYS)), skill.getId());
				vacations.add(v);
			}
		}
		
		return vacations;
	}

}
