package com.mWebApp2.model;

import java.sql.Timestamp;


public class Mood {
	
	private Long id;
	
	private Integer moodRange;
	
	private String description;

	private Timestamp ts;

	private Long uid;
	
	public Mood(){
		this.id = (long) 0;
	}
	
	public Mood(Long id, Integer moodRange, String description, Timestamp ts, Long uid){
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

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}
	
	

}
