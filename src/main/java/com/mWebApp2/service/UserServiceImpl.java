package com.mWebApp2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mWebApp2.dao.UserDao;
import com.mWebApp2.model.User;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao dao;

	public void saveUser(User user) {
		dao.saveUser(user);
		
	}

	public List<User> findAllUsers() {
		return dao.findAllUsers();
	}

	public void deleteUserById(Long id) {
		dao.deleteUserbyId(id);		
	}

	public User findById(Long id) {
		return dao.findById(id);
	}

	public void updateUser(User user) {
		dao.updateUser(user);	
	}
	
	public User findByEmail(String email) {
		return dao.findByEmail(email);
	}

}
