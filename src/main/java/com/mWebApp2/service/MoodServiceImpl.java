package com.mWebApp2.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mWebApp2.dao.MoodDao;
import com.mWebApp2.model.Mood;
import com.mWebApp2.model.MoodData;

@Service
@Transactional
public class MoodServiceImpl implements MoodService{

	@Autowired
	private MoodDao dao;
	
	@Autowired
	private UserService uservice;
	
	public Mood saveMood(Mood mood) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		mood.setTs(timestamp);
		return convertEntity(dao.saveMood(convertViewModel(mood)));
	}

	public List<Mood> findAllMoods() {
		List<MoodData> moodDatas = dao.findAllMoods();
		List<Mood> moods = new ArrayList<Mood>();
		for(MoodData moodData : moodDatas) {
			moods.add(convertEntity(moodData));
		}
		return moods;
	}

	public boolean deleteMoodById(Long id) {
		return dao.deleteMoodbyId(id) > 0;
	}

	public Mood findById(Long id) {
		return convertEntity(dao.findById(id));		
	}

	public Mood updateMood(Mood mood) { 
		return convertEntity(dao.updateMood(convertViewModel(mood)));
	}
	
	public List<Mood> findByUid(Long uid) {
		List<MoodData> moodDatas = dao.findByUid(uid);
		List<Mood> moods = new ArrayList<Mood>();
		for(MoodData moodData : moodDatas) {
			moods.add(convertEntity(moodData));
		}
		return moods;
	}
	
	public Mood convertEntity(MoodData moodData) {
		Mood mood = new Mood();
		mood.setId(moodData.getId());
		mood.setDescription(moodData.getDescription());
		mood.setMoodRange(moodData.getMoodRange());
		mood.setTs(moodData.getTs());
		mood.setUid(moodData.getUser().getId());
		return mood;
	}
	
	public MoodData convertViewModel( Mood mood) {
		MoodData moodData = new MoodData();
		moodData.setId(mood.getId());
		moodData.setDescription(mood.getDescription());
		moodData.setMoodRange(mood.getMoodRange());
		moodData.setTs(mood.getTs());
		moodData.setUser(uservice.findById(mood.getUid()));
		return moodData;
	}


}
