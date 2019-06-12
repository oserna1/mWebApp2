package com.mWebApp2.dao;

import java.util.List;

import com.mWebApp2.model.User;

public interface UserDao {
	
	User saveUser(User user);
	
	List<User> findAllUsers();
	
	int deleteUserbyId(Long id);
	
	User findById(Long id);
	
	User updateUser(User user);
	
	User findByEmail(String email);
	
}
