package es.achosoftware.ifreedays.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.achosoftware.ifreedays.model.Skill;
import es.achosoftware.ifreedays.repository.SkillRepository;

@Service("skillService")
public class SkillServiceImpl implements SkillService {

	@Autowired
	private SkillRepository skillRepository;

	@Override
	public Skill findSkillByName(String name) {
		return skillRepository.findByName(name);
	}

	@Override
	public void saveSkill(Skill skill) {
		skillRepository.save(skill);
	}

	@Override
	public List<Skill> listSkills() {
		return skillRepository.findAll();
	}

	@Override
	public Skill findSkillById(Integer id) {
		return skillRepository.findById(id);
	}

}
