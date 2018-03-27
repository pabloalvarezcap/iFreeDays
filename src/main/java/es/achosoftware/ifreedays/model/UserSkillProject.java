package es.achosoftware.ifreedays.model;

import java.util.List;


public class UserSkillProject {
	private Project project;
	private List<Skill> skill;
	
	
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public List<Skill> getSkill() {
		return skill;
	}
	public void setSkill(List<Skill> skill) {
		this.skill = skill;
	}
	
	
}
