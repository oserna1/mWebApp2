package com.mWebApp2.service;

import java.util.List;

import com.mWebApp2.model.Mood;

public interface MoodService {
	
	Mood saveMood(Mood mood);
	
	List<Mood> findAllMoods();
	
	boolean deleteMoodById(Long id);
	
	Mood findById(Long id);
	
	List<Mood> findByUid(Long uid);
	
	Mood updateMood(Mood mood);
}
