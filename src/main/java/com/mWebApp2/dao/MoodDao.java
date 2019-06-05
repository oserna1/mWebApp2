package com.mWebApp2.dao;

import java.util.List;

import com.mWebApp2.model.Mood;

public interface MoodDao {
	
	void saveMood(Mood mood);
	
	List<Mood> findAllMoods();
	
	void deleteMoodbyId(Long id);
	
	Mood findById(Long id);
	
	List<Mood> findByUid(Long uid);
	
	void updateMood(Mood mood);

}
