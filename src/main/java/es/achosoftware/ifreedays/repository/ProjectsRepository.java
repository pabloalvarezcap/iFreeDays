package es.achosoftware.ifreedays.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.achosoftware.ifreedays.constants.Querys;
import es.achosoftware.ifreedays.model.Project;
import es.achosoftware.ifreedays.model.Skill;

@Repository("projectsRepository")
public interface ProjectsRepository extends JpaRepository<Project, Integer>{
	

	@Query(nativeQuery = true, value = Querys.FIND_USERS_BY_PROJECT_ID)
	List<Project> findUsersByProjectId(@Param("projectId") int projectId);
	@Query(nativeQuery = true, value = Querys.FIND_SKILLS_BY_PROJECT_AND_USER)
	List<Skill> findSkillsByProjectAndUserId(@Param("projectId") int projectId, @Param("userId") int userId);

}
