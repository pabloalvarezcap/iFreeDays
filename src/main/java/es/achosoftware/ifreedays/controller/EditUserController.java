package es.achosoftware.ifreedays.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import es.achosoftware.ifreedays.model.Skill;
import es.achosoftware.ifreedays.model.User;
import es.achosoftware.ifreedays.repository.UserRepository;
import es.achosoftware.ifreedays.service.UserService;


@Controller
public class EditUserController {
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/admin/employees/edit/{id}")
	public ModelAndView editUser(@PathVariable("id") String id) {
		int _id = Integer.parseInt(id);
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		Boolean isAdmin = user.isAdmin();
		User _user = userService.findUserById(_id);
		
		modelAndView.addObject("user", _user);
		modelAndView.addObject("isAdmin", isAdmin);
		modelAndView.setViewName("admin/editUser");
		return modelAndView;
	}
	@PostMapping("/admin/submitEditUser")
	public ModelAndView submitEditUser(@RequestParam("id") String id, @RequestParam("name") String name, @RequestParam("lastName") String lastName, @RequestParam("email") String email, @RequestParam("active") Integer active,@RequestParam("password") String password) {
		ModelAndView modelAndView = new ModelAndView();
		User user = userService.findUserById(Integer.parseInt(id));
		if(!password.equals("")) {
			user.setPassword(bCryptPasswordEncoder.encode(password));
		}
		user.setEmail(email);
		user.setName(name);
		user.setLastName(lastName);
		user.setActive(active);	
		userRepository.save(user);
		modelAndView.addObject("skills", new ArrayList<Skill>());
		modelAndView.addObject("isAdmin", userService.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).isAdmin());
		modelAndView.addObject("msg", "User succesfully edited");
		modelAndView.setViewName("employees/list");
		modelAndView.addObject("employees", userService.listUsers());
		
		
		return modelAndView;
	}

}
