package com.mWebApp2.service;

import java.util.List;

import com.mWebApp2.model.User;

public interface UserService {

	void saveUser(User user);
	
	List<User> findAllUsers();
	
	void deleteUserById(Long id);
	
	User findById(Long id);
	
	void updateUser(User user);
}
