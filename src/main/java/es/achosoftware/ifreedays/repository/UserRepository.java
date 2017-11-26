package es.achosoftware.ifreedays.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.achosoftware.ifreedays.constants.Querys;
import es.achosoftware.ifreedays.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
	User findById(Integer id);
	@Query(nativeQuery = true, value = Querys.SELECT_USER_SKILLS)
	List<User> findBySkills(@Param("skillId") Integer id);
}
