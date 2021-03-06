package es.achosoftware.ifreedays.model;

import java.util.Set;
import java.util.stream.Collectors;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;

//import es.achosoftware.ifreedays.service.UserSkillProjectService;

/**
 * @author Francisco
 *
 */
@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int id;
	@Column(name = "email")
	@Email(message = "*Please provide a valid Email")
	@NotEmpty(message = "*Please provide an email")
	private String email;
	@Column(name = "password")
	@Length(min = 5, message = "*Your password must have at least 5 characters")
	@NotEmpty(message = "*Please provide your password")
	@Transient
	private String password;
	@Column(name = "name")
	@NotEmpty(message = "*Please provide your name")
	private String name;
	@Column(name = "last_name")
	@NotEmpty(message = "*Please provide your last name")
	private String lastName;
	@Column(name = "active")
	private int active;
	@ManyToMany(fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
	@ManyToMany(fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(name = "user_skill", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "skill_id"))
	private Set<Skill> skills;
	@OneToMany
	@JoinTable(name="USER_USP", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "USP_id"))
	@JoinColumn(name="UserUsp_id")
	private Set<UserSkillProject> userSkillproject;
//	@ManyToMany(fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
//	@JoinTable(name = "user_projects", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "project_id"))
//	private Set<Project> myProjects;
//	@OneToMany(mappedBy = "creator", cascade = CascadeType.ALL)
//	private Set<Project> myCreatedProjects;
	

	
	
	public User() {
		
	}
	
	public User(int id, String email, String password, String name, String lastName, int active, Set<Role> roles,
			Set<Skill> skills) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
		this.active = active;
		this.roles = roles;
		this.skills = skills;
//		this.myProjects = myProjects;
//		this.myCreatedProjects = myCreatedProjects;
	}
//	
//	@PostConstruct
//	public void setUpUSP() {
//		UserSkillProjectService usps = new UserSkillProjectService();
//		this.myProjects.stream().forEach(p -> usps.setupUSP(this.id, p.getId()));
//	}


//	public Set<Project> getMyProjects() {
//		return myProjects;
//	}
//
//	public void setMyProjects(Set<Project> myProjects) {
//		this.myProjects = myProjects;
//	}

//	public Set<Project> getMyCreatedProjects() {
//		return myCreatedProjects;
//	}
//
//	public void setMyCreatedProjects(Set<Project> myCreatedProjects) {
//		this.myCreatedProjects = myCreatedProjects;
//	}

//	public List<UserSkillProject> getUserSkillproject() {
//		return userSkillproject;
//	}
//
//	public void setUserSkillproject(List<UserSkillProject> userSkillproject) {
//		this.userSkillproject = userSkillproject;
//	}

	public Boolean isAdmin() {
		Boolean b = this.getRoles().stream().filter(role -> role.getName().equals("ADMIN")).collect(Collectors.toList()).size() > 0;
		System.err.println(b);
		return b;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<Skill> getSkills() {
		return skills;
	}

	public void setSkills(Set<Skill> skills) {
		this.skills = skills;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", name=" + name + ", lastName="
				+ lastName + ", active=" + active + "]";
	}

}
