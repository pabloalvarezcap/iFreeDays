package es.achosoftware.ifreedays.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name= "user_skill_project")
public class UserSkillProject {
	
	@Id
//	@Column(name = "id_user")
//	@ManyToMany(fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
//	@JoinTable(name = "user", joinColumns = @JoinColumn(name = "id_user"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	@ManyToOne
	@JoinColumn()
	private User user;
	@Id
//	@Column(name = "id_project")
//	@ManyToMany(fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
//	@JoinTable(name = "projects", joinColumns = @JoinColumn(name = "id_project"), inverseJoinColumns = @JoinColumn(name = "project_id"))
	@ManyToOne
	@JoinColumn()
	private Project project;
	@Id
//	@Column(name = "id_skill")
//	@ManyToMany(fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
//	@JoinTable(name = "skill", joinColumns = @JoinColumn(name = "id_skill"), inverseJoinColumns = @JoinColumn(name = "skill_id"))
	@ManyToOne
	@JoinColumn()
	private Skill skill;
	
	
	
}
