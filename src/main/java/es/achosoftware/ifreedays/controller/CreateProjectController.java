package es.achosoftware.ifreedays.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import es.achosoftware.ifreedays.model.Project;
import es.achosoftware.ifreedays.model.User;
import es.achosoftware.ifreedays.repository.ProjectsRepository;
import es.achosoftware.ifreedays.repository.UserRepositoryV2;

@Controller
public class CreateProjectController {

	@Autowired
	private UserRepositoryV2 userRepository;
	@Autowired
	private ProjectsRepository projectsRepository;
	
	@GetMapping("/admin/createProject")
	public ModelAndView createProject() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/createProject");
		return modelAndView;
	}
	
	@PostMapping("/admin/submitProject")
	@ResponseBody
	public List<User> submitProject(Principal principal, @RequestParam("name") String name) {
		User user = userRepository.findByEmail(principal.getName()); 
		System.err.println("\n\n\n\n" + user.getEmail() + "\n\n\n\n");
		Set<User> users = new HashSet<>();
		users.add(user);
		Project project = new Project(name, user);
		project.setUsers(users);
//		Set<Project> projects = user.getMyProjects();
////		if (projects == null)
//			projects = new HashSet<>();
		
//		projects.add(project);
//		user.setMyProjects(projects);
//		project.setName(name);
//		project.setCreator(user);
//		project.setUsers(users);
//		project.setId(300);

		Project projec = projectsRepository.save(project);
//		userRepository.save(user);
		System.out.println(projec.getId());
//		List<Skill> skills = projectsRepository.findSkillsByProjectAndUserId(1, user.getId());
		return userRepository.findByProjectId(projec.getId());
	}
}
