package com.mWebApp2.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="mood")
public class Mood {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "moodRange")
	private int moodRange;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "ts")
	private Timestamp ts;
	
	@Column(name = "uid")
	private Long uid;
	
	public Mood() {
		id = (long) 0;
	}
	
	public Mood(long id, int moodRange, String description, Timestamp ts, Long uid) {
		this.id = id;
		this.moodRange = moodRange;
		this.description = description;
		this.ts = ts;
		this.uid = uid;
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

	public void setMoodRange(int moodRange) {
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

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}
	
	

}
