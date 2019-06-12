package com.mWebApp2.service;

import java.util.List;

import com.mWebApp2.model.User;

public interface UserService {

	User saveUser(User user);
	
	List<User> findAllUsers();
	
	boolean deleteUserById(Long id);
	
	User findById(Long id);
	
	User findByEmail(String email);
	
	User updateUser(User user);
}
