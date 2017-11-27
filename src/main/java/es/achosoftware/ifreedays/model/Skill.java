package es.achosoftware.ifreedays.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "skill")
public class Skill {
	
	@Id
    @GeneratedValue()
	@Column(name="skill_id")
	private int id;
	@Length(max = 20, message = "*Your skill cannot be longer than 20 characters")
	@Column(name="name")
	private String name;
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "skills")
	private Set<User> user;

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
	public Set<User> getUser() {
		return user;
	}
	public void setUser(Set<User> user) {
		this.user = user;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Skill [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}
	
}