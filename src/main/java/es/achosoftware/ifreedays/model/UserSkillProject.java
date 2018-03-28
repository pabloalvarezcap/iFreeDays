package es.achosoftware.ifreedays.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity	
@Table(name = "USP")
public class UserSkillProject {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USP_id")
	private int id;
	@ManyToOne
	@JoinTable(name="project_USP", joinColumns = @JoinColumn(name = "USP_id"), inverseJoinColumns = @JoinColumn(name = "project_id"))
	@JoinColumn(name="project_id")
	private Project project;
	@ManyToMany(fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(name = "USP_skill", joinColumns = @JoinColumn(name = "USP_id"), inverseJoinColumns = @JoinColumn(name = "skill_id"))
	private Set<Skill> skill;
	
	
	public UserSkillProject() {
		super();
	}
	public UserSkillProject(Project project, Set<Skill> skill) {
		super();
		this.project = project;
		this.skill = skill;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public Set<Skill> getSkill() {
		return skill;
	}
	public void setSkill(Set<Skill> skill) {
		this.skill = skill;
	}
	
	
}
