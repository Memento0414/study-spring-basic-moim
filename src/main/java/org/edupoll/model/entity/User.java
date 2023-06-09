package org.edupoll.model.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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
	String pass;
	
	@NotNull
	@NotBlank
	String nick;
	Date joinDate;
	String authority;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="userDetailIdx") //User Entity의 필드
	UserDetail userDetail; // 이 column을 찾는 객체는 UserDetail(id 기준으로 찾음)
	
	@OneToMany(mappedBy = "manager", fetch = FetchType.LAZY)
	List<Moim> moims;
	
	@OneToMany(mappedBy = "user", fetch=FetchType.LAZY)
	List<Attendance> attendance;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="attendances" , joinColumns = @JoinColumn(name="userId"), inverseJoinColumns = @JoinColumn(name="moimId"))
	List<Moim> attendMoims;
	
	//follow 테이블과 연관 관계 맺음 데이터들
	@OneToMany(mappedBy = "owner")
	List<Follow> followTo;
	
	@OneToMany(mappedBy = "target")
	List<Follow> followFrom;
	
//==================================================================
	
	
	
	public List<Follow> getFollowTo() {
		return followTo;
	}
	
	public void setFollowTo(List<Follow> followTo) {
		this.followTo = followTo;
	}
	
	public List<Follow> getFollowFrom() {
		return followFrom;
	}
	
	public void setFollowFrom(List<Follow> followFrom) {
		this.followFrom = followFrom;
	}
	
	
	
//=====================================================================	
	
	public List<Moim> getAttendMoims() {
		return attendMoims;
	}

	public void setAttendMoims(List<Moim> attendMoims) {
		this.attendMoims = attendMoims;
	}


	public List<Attendance> getAttendance() {
		return attendance;
	}

	public void setAttendance(List<Attendance> attendance) {
		this.attendance = attendance;
	}

	public List<Moim> getMoims() {
		return moims;
	}

	public void setMoims(List<Moim> moims) {
		this.moims = moims;
	}

	// setter / getter 추가
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

	// 객체 insert전 할 작업
	@PrePersist
	public void doPrePersist() {
		System.out.println("doPrePersist..");
		joinDate = new Date();
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	
	
}
