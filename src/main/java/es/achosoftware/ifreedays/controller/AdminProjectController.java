package es.achosoftware.ifreedays.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import es.achosoftware.ifreedays.model.User;
import es.achosoftware.ifreedays.repository.ProjectsRepository;
import es.achosoftware.ifreedays.service.UserService;

@Controller
public class AdminProjectController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProjectsRepository projectRepository;
	
	@RequestMapping(value = "/admin/home/{id}", method = RequestMethod.GET)
	public ModelAndView homeProject(@PathVariable("id") String id) {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		
		
		
		
		
	return modelAndView;
	}

}
