package org.edupoll.model.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="users")
public class User {
	@Id
	@Pattern(regexp ="[a-z][a-z0-9]+")
	@NotNull
	@NotBlank
	@Size(min=4, max=12)
	String id;
	
	@NotNull
	@NotBlank
	@Size(min=4, max=12)
	String pass;
	
	@NotNull
	@NotBlank
	String nick;
	Date joinDate;
	Integer userDetailIdx;
	
	
	public User() {
		super();
	}


	public User(String id, String pass,  String nick, Date joinDate, Integer userDetailIdx) {
		super();
		this.id = id;
		this.pass = pass;
		this.nick = nick;
		this.joinDate = joinDate;
		this.userDetailIdx = userDetailIdx;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPass() {
		return pass;
	}


	public void setPass(String pass) {
		this.pass = pass;
	}


	public String getNick() {
		return nick;
	}


	public void setNick(String nick) {
		this.nick = nick;
	}


	public Date getJoinDate() {
		return joinDate;
	}


	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}


	public Integer getUserDetailIdx() {
		return userDetailIdx;
	}


	public void setUserDetailIdx(Integer userDetailIdx) {
		this.userDetailIdx = userDetailIdx;
	}


	@PrePersist
	public void doPrePersist() {
		System.out.println("doPrePersist..");
		joinDate = new Date();
	}
	
	
}
