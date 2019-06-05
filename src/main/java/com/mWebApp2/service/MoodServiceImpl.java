package com.mWebApp2.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mWebApp2.dao.MoodDao;
import com.mWebApp2.model.Mood;

@Service
@Transactional
public class MoodServiceImpl implements MoodService{

	@Autowired
	private MoodDao dao;
	
	public void saveMood(Mood mood) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		mood.setTs(timestamp);
		dao.saveMood(mood);
	}

	public List<Mood> findAllMoods() {
		return dao.findAllMoods();
	}

	public void deleteMoodById(Long id) {
		dao.deleteMoodbyId(id);
	}

	public Mood findById(Long id) {
		return dao.findById(id);
	}

	public void updateMood(Mood mood) {
		dao.updateMood(mood);
	}
	
	public List<Mood> findByUid(Long uid) {
		return dao.findByUid(uid);
	}


}
