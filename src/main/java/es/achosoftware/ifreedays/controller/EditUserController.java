package es.achosoftware.ifreedays.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import es.achosoftware.ifreedays.model.User;
import es.achosoftware.ifreedays.service.UserService;
@Controller
public class EditUserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/admin/employees/edit/{id}")
	public ModelAndView editUser(@PathVariable("id") String id) {
		int _id = Integer.parseInt(id);
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		Boolean isAdmin = user.isAdmin();
		User _user = userService.findUserById(_id);
		System.out.println("\n\n\n\n\n TROLOROLROLROLROLROLRORLO");
		modelAndView.addObject("user", _user);
		modelAndView.addObject("isAdmin", isAdmin);
		modelAndView.setViewName("admin/editUser");
		return modelAndView;
	}

}
