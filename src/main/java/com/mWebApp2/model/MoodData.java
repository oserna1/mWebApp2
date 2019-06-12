package com.mWebApp2.model;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name="mood")
public class MoodData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "moodRange")
	private Integer moodRange;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "ts")
	private Timestamp ts;
	
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name="uid")
	private User user;
	
	public MoodData() {
		this.id = (long)0;
	}
	
	public MoodData(Long id, Integer moodRange, String description, Timestamp ts, User user) {
		this.id = id;
		this.moodRange = moodRange;
		this.description = description;
		this.ts = ts; 
		this.user = user;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getMoodRange() {
		return moodRange;
	}

	public void setMoodRange(Integer moodRange) {
		this.moodRange = moodRange;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getTs() {
		return ts;
	}

	public void setTs(Timestamp ts) {
		this.ts = ts;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


}
