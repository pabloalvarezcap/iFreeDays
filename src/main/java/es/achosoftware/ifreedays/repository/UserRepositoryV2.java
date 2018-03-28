package es.achosoftware.ifreedays.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.achosoftware.ifreedays.constants.Querys;
import es.achosoftware.ifreedays.model.User;

@Repository("userRepositoryV2")
public interface UserRepositoryV2 extends JpaRepository<User, Long> {
	User findByEmail(String email);
	User findById(int id);
	@Query(nativeQuery = true, value = Querys.FIND_USERS_BY_PROJECT_ID)
	List<User> findByProjectId(@Param("projectId") Integer Pid);
	@Query(nativeQuery = true, value = Querys.FIND_USERS_BY_SKILL_AND_PROJECT)
	List<User> findBySkillIdAndProjectId(@Param("projectId") Integer projctId, @Param("skillId") int skillId);

}
