package es.achosoftware.ifreedays.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.achosoftware.ifreedays.model.Skill;

@Repository("skillRepository")
public interface SkillRepository extends JpaRepository<Skill, Integer> {
	Skill findByName(String name);
	Skill findById(int id);
}
