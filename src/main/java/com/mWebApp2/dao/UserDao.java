package com.mWebApp2.dao;

import java.util.List;

import com.mWebApp2.model.User;

public interface UserDao {
	
	void saveUser(User user);
	
	List<User> findAllUsers();
	
	void deleteUserbyId(Long id);
	
	User findById(Long id);
	
	void updateUser(User user);
	
	User findByEmail(String email);
	
}
