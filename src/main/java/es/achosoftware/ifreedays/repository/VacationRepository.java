package es.achosoftware.ifreedays.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.achosoftware.ifreedays.constants.Querys;
import es.achosoftware.ifreedays.model.Vacation;

@Repository("vacationRepository")
public interface VacationRepository extends JpaRepository<Vacation, Integer> {
	
	Vacation findByUserId(int userId);
	Vacation findByDay(Date date);
	@Query(nativeQuery = true, value = Querys.FIND_CALENDAR_BY_SKILL_ORDER_BY_DAY_ASC)
	List<Vacation> findBySkills(@Param("skillId") Integer id);
	@Query(nativeQuery = true, value = Querys.FIND_CALENDAR_BY_SKILL_AND_DATE_ORDER_BY_DAY_ASC)
	List<Vacation> findBySkills(@Param("skillId") Integer id, @Param("month") Integer month, @Param("year") Integer year);

}
