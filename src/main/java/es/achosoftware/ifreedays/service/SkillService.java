package es.achosoftware.ifreedays.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.achosoftware.ifreedays.constants.Querys;
import es.achosoftware.ifreedays.model.Skill;
import es.achosoftware.ifreedays.model.Vacation;

public interface SkillService {
	public Skill findSkillByName(String name);
	public void saveSkill(Skill skill);
	public List<Skill> listSkills();
	public Skill findSkillById(Integer id);
	@Query(nativeQuery = true, value = Querys.FIND_SKILLS_BY_PROJECT_ID)
	List<Skill> listSkillsOfProjectId(@Param("projectId") Integer projectID);
}
