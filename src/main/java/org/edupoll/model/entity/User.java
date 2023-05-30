package org.edupoll.model.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="userDetailIdx") //User Entity의 필드
	UserDetail userDetail; // 이 column을 찾는 객체는 UserDetail(id 기준으로 찾음)
	
	
	//setter / getter 추가
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





	public UserDetail getUserDetail() {
		return userDetail;
	}





	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}





	// 객체  insert전 할 작업 
	@PrePersist
	public void doPrePersist() {
		System.out.println("doPrePersist..");
		joinDate = new Date();
	}





	@Override
	public String toString() {
		return "User [id=" + id + ", pass=" + pass + ", nick=" + nick + ", joinDate=" + joinDate + ", userDetail="
				+ userDetail + "]";
	}
	
	
	
}
