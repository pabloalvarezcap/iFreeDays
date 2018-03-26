package es.achosoftware.ifreedays.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;


@Entity
@Table(name="projects")
public class Project {
	@Id
	@GeneratedValue()
	@Column(name = "project_id")
	private int id;
	@Length(max = 100, message = "*Project's name cannot be longer than 100 characters")
	@Column(name = "name")
	private String name;
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "myProjects")
	private Set<User> users;
	@ManyToOne
	@JoinColumn()
	private User creator;

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
