package com.mWebApp2.manager;

import java.util.List;

import com.mWebApp2.model.User;

public interface UserManager {
	
	List<User> listAllUsers();
	
	User getUserbyId(Long id);
	
	Boolean createUser(User user);
	
	User updateUser(Long id, User user);
	
	Boolean deleteUser(Long id);

}
