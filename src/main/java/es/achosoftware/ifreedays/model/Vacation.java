package es.achosoftware.ifreedays.model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vacation")
public class Vacation implements Comparable<Vacation> {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	// @Id
	@Column(name = "user_id")
	private int userId;
	@Column(name = "day")
	private Date day;
	@Column(name = "skill_id")
	private int skillId;

	public Vacation() {
	}

	public Vacation(int userId, Date day, int skillId) {
		super();
		this.userId = userId;
		this.day = day;
		this.skillId = skillId;
	}

	public int getUserid() {
		return userId;
	}

	public void setUserid(int userId) {
		this.userId = userId;
	}

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	public int getSkillId() {
		return skillId;
	}

	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}

	public int getd() {
		return id;
	}

	public void setd(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Vacation [userId=" + userId + ", day=" + day + ", skillId=" + skillId + "]";
	}

	public String pami() {
		LocalDate a = getDay().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		return a.format(formatter);

	}

	@Override
	public int compareTo(Vacation arg0) {
		return this.getDay().compareTo(arg0.getDay());
	}
}