package org.edupoll.model.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name="moims")
public class Moim {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	String id;
	String managerId;
	String title;
	String cate;
	String description;
	Integer maxPerson;
	Integer currentPerson;
	Date targetDate;
	Integer duration;
	
	public Moim() {
		super();
	}

	public String getId() {
		return id;
	}




	public void setId(String id) {
		this.id = id;
	}




	public String getManagerId() {
		return managerId;
	}




	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}




	public String getTitle() {
		return title;
	}




	public void setTitle(String title) {
		this.title = title;
	}




	public String getCate() {
		return cate;
	}




	public void setCate(String cate) {
		this.cate = cate;
	}




	public String getDescription() {
		return description;
	}




	public void setDescription(String description) {
		this.description = description;
	}




	public Integer getMaxPerson() {
		return maxPerson;
	}




	public void setMaxPerson(Integer maxPerson) {
		this.maxPerson = maxPerson;
	}




	public Integer getCurrentPerson() {
		return currentPerson;
	}




	public void setCurrentPerson(Integer currentPerson) {
		this.currentPerson = currentPerson;
	}




	public Date getTargetDate() {
		return targetDate;
	}




	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}




	public Integer getDuration() {
		return duration;
	}




	public void setDuration(Integer duration) {
		this.duration = duration;
	}


	@PrePersist
	public void doPrePersist() {
		System.out.println("doPrePersist..");
		targetDate = new Date();
	}
	
}
