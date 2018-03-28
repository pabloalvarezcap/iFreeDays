package es.achosoftware.ifreedays.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import es.achosoftware.ifreedays.model.Project;
import es.achosoftware.ifreedays.model.Skill;
import es.achosoftware.ifreedays.model.User;
import es.achosoftware.ifreedays.model.UserSkillProject;
import es.achosoftware.ifreedays.repository.ProjectsRepository;
import es.achosoftware.ifreedays.repository.UserRepositoryV2;
import es.achosoftware.ifreedays.repository.UserSkillProjectRepository;

@Controller
public class CreateProjectController {

	@Autowired
	private UserRepositoryV2 userRepository;
	@Autowired
	private ProjectsRepository projectsRepository;
	@Autowired
	private UserSkillProjectRepository usp;
	
	@GetMapping("/admin/createProject")
	public ModelAndView createProject() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/createProject");
		return modelAndView;
	}
	
	@PostMapping("/admin/submitProject")
	@ResponseBody
	public List<String> submitProject(Principal principal, @RequestParam("name") String name) {
		User user = userRepository.findByEmail(principal.getName()); 
		System.err.println("\n\n\n\n" + user.getEmail() + "\n\n\n\n");
		UserSkillProject USP= new UserSkillProject();
		Set<User> users = new HashSet<>();
		Set<Skill> skills = new HashSet<>();
		users.add(user);
		user.getSkills().stream().forEach(a->skills.add(a));
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
		project.setSkills(skills);
		Project projec = projectsRepository.save(project);
		USP.setProject(projec);
		USP.setSkill(skills);
//		userRepository.save(user);
		usp.save(USP);
		System.out.println(userRepository.findByProjectId(projec.getId()));
//		List<Skill> skills = projectsRepository.findSkillsByProjectAndUserId(1, user.getId());
		return userRepository.findByProjectId(projec.getId()).stream().map(u -> u.toString()).collect(Collectors.toList());
	}
}
