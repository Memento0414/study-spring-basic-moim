package org.edupoll.model.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="user_details")
public class UserDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	Integer idx;
	
	String address;
	Date birthday;
	String avatarId;
	String description;
	
	
	public UserDetail() {
		super();
	}


	public UserDetail(Integer idx, String address, Date birthday, String avatarId, String description) {
		super();
		this.idx = idx;
		this.address = address;
		this.birthday = birthday;
		this.avatarId = avatarId;
		this.description = description;
	}


	public Integer getIdx() {
		return idx;
	}


	public void setIdx(Integer idx) {
		this.idx = idx;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public Date getBirthday() {
		return birthday;
	}


	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}


	public String getAvatarId() {
		return avatarId;
	}


	public void setAvatarId(String avatarId) {
		this.avatarId = avatarId;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
