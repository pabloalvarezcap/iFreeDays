package es.achosoftware.ifreedays.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import es.achosoftware.ifreedays.model.Skill;
import es.achosoftware.ifreedays.model.User;
import es.achosoftware.ifreedays.service.SkillService;
import es.achosoftware.ifreedays.service.UserService;

@Controller
public class SkillController {

	@Autowired
	private SkillService skillService;
	@Autowired
	private UserService userService;
	// @Autowired
	// private Session session;

	@RequestMapping(value = "/skills", method = RequestMethod.GET)
	public ModelAndView listSkills() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User user = userService.findUserByEmail(email);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("skills/list");
		Boolean isAdmin = user.isAdmin();
		modelAndView.addObject("isAdmin", isAdmin);
		modelAndView.addObject("skills", skillService.listSkills());
		return modelAndView;
	}
	
	@RequestMapping(value = "/skills/{id}", method = RequestMethod.GET)
	public ModelAndView listSkillsForProject(@PathVariable("id") Integer id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User user = userService.findUserByEmail(email);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("skills/list");
		Boolean isAdmin = user.isAdmin();
		modelAndView.addObject("isAdmin", isAdmin);
		modelAndView.addObject("skills", skillService.listSkillsOfProjectId(id));
		return modelAndView;
	}

	@RequestMapping(value = "/skills/users/{id}", method = RequestMethod.GET)
	public ModelAndView listUsersWithSkill(@PathVariable("id") Integer id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("skills/listUsers");
		modelAndView.addObject("skill", skillService.findSkillById(id));
		modelAndView.addObject("users", userService.listUsersWithSkill(id));
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("isAdmin", user.isAdmin());
		return modelAndView;
	}
	
	@GetMapping("/skills/table")
	public ModelAndView skillsTable(Principal principal) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("employees/listSkills");
		List<User> users = userService.listUsers();
		List<Skill> skills = skillService.listSkills();
		Boolean isAdmin = userService.findUserByEmail(principal.getName()).isAdmin();
		modelAndView.addObject("skills", skills);
		modelAndView.addObject("users", users);
		modelAndView.addObject("isAdmin", isAdmin);
		return modelAndView;
	}
}
