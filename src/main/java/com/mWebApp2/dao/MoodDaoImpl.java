package com.mWebApp2.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mWebApp2.model.Mood;

@Repository
public class MoodDaoImpl implements MoodDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveMood(Mood mood) {
		sessionFactory.getCurrentSession().persist(mood);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Mood> findAllMoods() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Mood.class);
		return (List<Mood>) criteria.list();
	}

	@Override
	public void deleteMoodbyId(Long id) {
		Query query = sessionFactory.getCurrentSession().createSQLQuery("delete from Mood where id = :id");
        query.setLong("id", id);
        query.executeUpdate();
	}

	@Override
	public Mood findById(Long id) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Mood.class);
        criteria.add(Restrictions.eq("id",id));
        return (Mood) criteria.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Mood> findByUid(Long uid) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Mood.class);
        criteria.add(Restrictions.eq("uid",uid));
        return (List<Mood>) criteria.list();
	}

	@Override
	public void updateMood(Mood mood) {
		sessionFactory.getCurrentSession().update(mood);
	}

}
