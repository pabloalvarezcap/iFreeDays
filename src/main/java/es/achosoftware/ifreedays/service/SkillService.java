package es.achosoftware.ifreedays.service;

import java.util.List;

import es.achosoftware.ifreedays.model.Skill;

public interface SkillService {
	public Skill findSkillByName(String name);
	public void saveSkill(Skill skill);
	public List<Skill> listSkills();
	public Skill findSkillById(Integer id);
}
