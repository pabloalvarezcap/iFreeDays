package es.achosoftware.ifreedays.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import es.achosoftware.ifreedays.service.SkillService;
import es.achosoftware.ifreedays.service.UserService;

@Controller
public class SkillController {

	@Autowired
	private SkillService skillService;
	@Autowired
	private UserService userService;

	@RequestMapping(value =  "/skills", method = RequestMethod.GET)
	public ModelAndView listSkills() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("skills/list");
		modelAndView.addObject("skills", skillService.listSkills());
		return modelAndView;
	}

	@RequestMapping(value =  "/skills/users/{id}", method = RequestMethod.GET)
	public ModelAndView listUsersWithSkill(@PathVariable("id") Integer id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("skills/listUsers");
		modelAndView.addObject("skill", skillService.findSkillById(id));
		modelAndView.addObject("users", userService.listUsersWithSkill(id));
		return modelAndView;
	}
}
