package com.mWebApp2.dao;

import java.util.List;

import com.mWebApp2.model.MoodData;

public interface MoodDao {
	
	MoodData saveMood(MoodData moodData);
	
	List<MoodData> findAllMoods();
	
	int deleteMoodbyId(Long id);
	
	MoodData findById(Long id);
	
	List<MoodData> findByUid(Long uid);
	
	MoodData updateMood(MoodData moodData);

}
