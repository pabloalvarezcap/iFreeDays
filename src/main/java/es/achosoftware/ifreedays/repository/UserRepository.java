package es.achosoftware.ifreedays.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.achosoftware.ifreedays.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
	@Query(nativeQuery = true, value = "select * from user u inner join user_skill us on (u.user_id = us.user_id) where us.skill_id = :skillId")
	List<User> findBySkills(@Param("skillId") Integer id);
}
