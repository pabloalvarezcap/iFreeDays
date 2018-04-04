package es.achosoftware.ifreedays.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.achosoftware.ifreedays.constants.Querys;
import es.achosoftware.ifreedays.model.Skill;

@Repository("skillRepository")
public interface SkillRepository extends JpaRepository<Skill, Integer> {
	Skill findByName(String name);
	Skill findById(int id);
	@Query(nativeQuery = true, value = Querys.FIND_SKILLS_BY_PROJECT_ID)
	public List<Skill> listSkillsOfProjectId(@Param("projectId") Integer projectID);
}
