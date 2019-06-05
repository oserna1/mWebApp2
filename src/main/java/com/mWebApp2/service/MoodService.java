package com.mWebApp2.service;

import java.util.List;

import com.mWebApp2.model.Mood;

public interface MoodService {
	
	void saveMood(Mood mood);
	
	List<Mood> findAllMoods();
	
	void deleteMoodById(Long id);
	
	Mood findById(Long id);
	
	List<Mood> findByUid(Long uid);
	
	void updateMood(Mood mood);
}
