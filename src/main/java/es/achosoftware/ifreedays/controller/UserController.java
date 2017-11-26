package es.achosoftware.ifreedays.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import es.achosoftware.ifreedays.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value =  "/admin/employees", method = RequestMethod.GET)
	public ModelAndView listEmployees() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("employees/list");
		modelAndView.addObject("employees", userService.listUsers());
		return modelAndView;
	}
	
	@RequestMapping(value =  "/admin/employees/delete{id}", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam("id") Integer id) {
		userService.delete(id);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("employees/list");
		modelAndView.addObject("employees", userService.listUsers());
		modelAndView.addObject("msg", "OK");
		return modelAndView;
	}
}
