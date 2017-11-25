package es.achosoftware.ifreedays.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "skill")
public class Skill {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="skill_id")
	private int id;
	@Length(max = 20, message = "*Your skill cannot be longer than 20 characters")
	@Column(name="name")
	private String name;
	
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
}