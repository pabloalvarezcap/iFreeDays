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

import org.hibernate.validator.constraints.Length;


@Entity
@Table(name="projects")
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "project_id")
	private int id;
	@Length(max = 100, message = "*Project's name cannot be longer than 100 characters")
	@Column(name = "name")
	private String name;
//	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "myProjects")
	@ManyToMany(fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(name = "user_projects", joinColumns = @JoinColumn(name = "project_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<User> users;
	@ManyToOne
	@JoinTable(name="project_creator", joinColumns = @JoinColumn(name = "project_id"), inverseJoinColumns = @JoinColumn(name = "creator_id"))
	@JoinColumn(name="creator_user_id")
	private User creator;
	@ManyToMany(fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(name = "project_skills", joinColumns = @JoinColumn(name = "project_id"), inverseJoinColumns = @JoinColumn(name = "skill_id"))
	private Set<Skill> skills;
	
	public Project() {}
	
	

	public Project(String name, User creator) {
		this.name = name;
		this.creator = creator;
//		this.users = users;
	}


	
	

	public Set<Skill> getSkills() {
		return skills;
	}



	public void setSkills(Set<Skill> skills) {
		this.skills = skills;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}
	
	
}
