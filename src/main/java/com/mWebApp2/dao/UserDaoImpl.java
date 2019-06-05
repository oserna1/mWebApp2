package com.mWebApp2.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mWebApp2.model.User;

@Repository
public class UserDaoImpl implements UserDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void saveUser(User user) {
		sessionFactory.getCurrentSession().persist(user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAllUsers() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
		return (List<User>) criteria.list();
	}

	@Override
	public void deleteUserbyId(Long id) {
		Query query = sessionFactory.getCurrentSession().createSQLQuery("delete from User where id = :id");
        query.setLong("id", id);
        query.executeUpdate();
		
	}

	@Override
	public User findById(Long id) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("id",id));
        return (User) criteria.uniqueResult();
	}

	@Override
	public void updateUser(User user) {
		sessionFactory.getCurrentSession().update(user);
		
	}
	
	@Override
	public User findByEmail(String email) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("email",email));
        return (User) criteria.uniqueResult();
	}
	
}
