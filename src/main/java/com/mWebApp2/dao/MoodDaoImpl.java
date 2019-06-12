package com.mWebApp2.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mWebApp2.model.MoodData;

@Repository
public class MoodDaoImpl implements MoodDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public MoodData saveMood(MoodData moodData) {
		sessionFactory.getCurrentSession().persist(moodData);
		return moodData;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MoodData> findAllMoods() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(MoodData.class);
		return (List<MoodData>) criteria.list();
	}

	@Override
	public int deleteMoodbyId(Long id) {
		Query query = sessionFactory.getCurrentSession().createSQLQuery("delete from Mood where id = :id");
        query.setLong("id", id);
        return query.executeUpdate();
	}

	@Override
	public MoodData findById(Long id) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(MoodData.class);
        criteria.add(Restrictions.eq("id",id));
        return (MoodData) criteria.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MoodData> findByUid(Long uid) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(MoodData.class);
        criteria.add(Restrictions.eq("user.id",uid));
        return (List<MoodData>) criteria.list();
	}

	@Override
	public MoodData updateMood(MoodData moodData) {
		sessionFactory.getCurrentSession().update(moodData);
		return moodData;
	}

}
