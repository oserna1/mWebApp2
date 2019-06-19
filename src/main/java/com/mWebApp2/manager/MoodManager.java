package com.mWebApp2.manager;

import java.util.List;

import com.mWebApp2.model.Mood;


public interface MoodManager {
	
	List<Mood> listAllMoodsByUserId(Long id);
	
	Boolean createMood(Mood mood);
	
	Mood updateMood(Long id, Mood mood);
	
	Boolean deleteMood(Long id);

}
