package org.edupoll.model.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name="moims")
public class Moim {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	String id;
	
	String title;
	String cate;
	String description;
	Integer maxPerson;
	Integer currentPerson;
	Date targetDate;
	Integer duration;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="managerId")
	User manager;
	
	
	public String getId() {
		return id;
	}







	public void setId(String id) {
		this.id = id;
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







	public User getManager() {
		return manager;
	}







	public void setManager(User manager) {
		this.manager = manager;
	}







	@PrePersist
	public void doPrePersist() {
		System.out.println("doPrePersist..");
		targetDate = new Date();
	}
	
}
