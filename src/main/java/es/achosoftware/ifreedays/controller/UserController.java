package es.achosoftware.ifreedays.controller;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import es.achosoftware.ifreedays.model.Skill;
import es.achosoftware.ifreedays.model.User;
import es.achosoftware.ifreedays.repository.UserRepository;
import es.achosoftware.ifreedays.service.SkillService;
import es.achosoftware.ifreedays.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private SkillService skillService;

	@RequestMapping(value = "/admin/employees", method = RequestMethod.GET)
	public ModelAndView listEmployees(@RequestParam(name="msg", required=false, defaultValue="") String msg) {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("isAdmin", user.isAdmin());
		modelAndView.addObject("skills", new ArrayList<Skill>());
		if (!msg.equals(""))
			modelAndView.addObject("msg", msg);
		else
			modelAndView.addObject("msg", null);
		modelAndView.setViewName("employees/list");
		modelAndView.addObject("employees", userService.listUsers());
		return modelAndView;
	}

	@RequestMapping(value = "/admin/employees/delete/{id}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable("id") Integer id) {
		userService.delete(id);
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("isAdmin", user.isAdmin());
//		modelAndView.setViewName("employees/list");
		modelAndView.setViewName("redirect:/admin/employees/");
		modelAndView.addObject("skills", new ArrayList<Skill>());
		modelAndView.addObject("employees", userService.listUsers());
		modelAndView.addObject("msg", "The user «" + user.getName() + ", " + user.getLastName() + "» has been deleted.");
		return modelAndView;
	}
	
	@RequestMapping(value = "/admin/employees/delete/skill/", method = RequestMethod.GET)
	public ModelAndView deleteSkill(@RequestParam("skillId") Integer skillId, @RequestParam("userId") Integer userId) {
		User user = userService.findUserById(userId);
		Set<Skill> skills = user.getSkills().parallelStream().filter(s -> s.getId() != skillId).collect(Collectors.toSet());
		user.setSkills(skills);
		userRepository.save(user);
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User _user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("isAdmin", _user.isAdmin());
		modelAndView.setViewName("redirect:/admin/employees/skills/" + user.getId());
		modelAndView.addObject("skills", skills);
		modelAndView.addObject("employees", userService.listUsers());
		modelAndView.addObject("msg", "The skill «" + skillService.findSkillById(skillId).getName()  + "» has been deleted.");
		return modelAndView;
	}

	@GetMapping("/admin/employees/skills/{id}")
	public ModelAndView viewSkills(@PathVariable("id") Integer id, @RequestParam(name="msg", required=false, defaultValue="") String msg) {
//		List<String> skills = userService.findUserById(id).getSkills().stream().map(e -> e.getName())
//				.collect(Collectors.toList());
		TreeSet<Skill> skills = new TreeSet<Skill>(userService.findUserById(id).getSkills());
		User user = userService.findUserById(id);
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User _user = userRepository.findByEmail(auth.getName());
		modelAndView.addObject("isAdmin", _user.isAdmin());
		modelAndView.addObject("employees", userService.listUsers());
		modelAndView.setViewName("employees/list");
		if (skills.size() == 0) {
			skills = null;
		}
		if (!msg.equals(""))
			modelAndView.addObject("msg", msg);
		else
			modelAndView.addObject("msg", null);
		modelAndView.addObject("skills", skills);
		modelAndView.addObject("user", user);
		return modelAndView;
	}
	
	
}
