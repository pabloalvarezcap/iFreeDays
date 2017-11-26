package es.achosoftware.ifreedays.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "vacation")
public class Vacation {
	@Id
	@Column(name="user_id")
	private int userId;
	@Column(name="day")
	private Date day;
	@Transient
	private int skillId;
	
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
	@Override
	public String toString() {
		return "Vacation [userId=" + userId + ", day=" + day + ", skillId=" + skillId + "]";
	}
	
}