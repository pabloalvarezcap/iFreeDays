package es.achosoftware.ifreedays.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.achosoftware.ifreedays.constants.Querys;
import es.achosoftware.ifreedays.model.Vacation;

@Repository("vacationRepositoryV2")
public interface VacationRepositoryV2 extends JpaRepository<Vacation, Integer> {
	
	Vacation findByUserId(int userId);
	Vacation findByDay(Date date);
	@Query(nativeQuery = true, value = Querys.FIND_CALENDAR_BY_SKILL_BY_PROJECT_ORDER_BY_DAY_ASC)
	List<Vacation> findBySkillsV2(@Param("skillId") Integer id, @Param("projectId") Integer Pid);
	@Query(nativeQuery = true, value = Querys.FIND_CALENDAR_BY_SKILL_AND_FOR_PROJECT_AND_DATE_ORDER_BY_DAY_ASC)
	List<Vacation> findBySkillsV2(@Param("skillId") Integer id, @Param("projectId") Integer Pid, @Param("month") Integer month, @Param("year") Integer year);
	@Query(nativeQuery = true, value = Querys.FIND_VACATIONS_BY_USER_ID_AND_BY_PROJECT_ID)
	List<Vacation> findVacationsByUserIdV2(@Param("userId") Integer id);

}
