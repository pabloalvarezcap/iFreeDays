package es.achosoftware.ifreedays.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import es.achosoftware.ifreedays.model.Project;
import es.achosoftware.ifreedays.model.Skill;
import es.achosoftware.ifreedays.model.User;
import es.achosoftware.ifreedays.model.UserSkillProject;
import es.achosoftware.ifreedays.repository.ProjectsRepository;
import es.achosoftware.ifreedays.repository.UserRepository;

@Repository
public class UserSkillProjectService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProjectsRepository projectRepository;

	public void setupUSP(int userId, int projectId) {

		List<Skill> queryForList = jdbcTemplate.queryForList(
				"select s.* from skill s inner join user_skill_project usp on usp.id_user = ? and usp.id_project = ? where s.skill_id = usp.id_skill",
				Skill.class, new Object[] { userId, projectId });

		User user = userRepository.findById(userId);
		Project project = projectRepository.findOne(projectId);
		UserSkillProject usp = new UserSkillProject();
		List<Skill> skills = new ArrayList<>();

		for (Skill s : queryForList) {
			if (user.getSkills().contains(s)) {
				skills.add(s);
			}
		}
		usp.setSkill(skills);
		usp.setProject(project);
		List<UserSkillProject> userUSPList = user.getUserSkillproject();
		if (userUSPList == null) {
			userUSPList = new ArrayList<>();
		}
		
		userUSPList.add(usp);
		user.setUserSkillproject(userUSPList);
	}
}
