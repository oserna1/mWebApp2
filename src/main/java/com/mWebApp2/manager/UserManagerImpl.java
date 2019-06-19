package com.mWebApp2.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mWebApp2.model.User;

@Service
public class UserManagerImpl implements UserManager{
	
	public static final String REST_SERVICE_URI = "http://localhost:8090/com.mWebAppBackend/user/";
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<User> listAllUsers() {
		
		ResponseEntity<List<User>> response = restTemplate.exchange(
				REST_SERVICE_URI,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<User>>(){});
		
		return response.getBody();
	}

	@Override
	public User getUserbyId(Long id) {
		ResponseEntity<User> response = restTemplate.exchange(
				REST_SERVICE_URI + id.toString(),
				HttpMethod.GET,
				null,
				User.class);
		
		return response.getBody();
	}

	@Override
	public Boolean createUser(User user) {
		
		HttpEntity<User> requestEntity = new HttpEntity<>(user);
		
		ResponseEntity<Boolean> response = restTemplate.exchange(
				REST_SERVICE_URI,
				HttpMethod.POST,
				requestEntity,
				Boolean.class);
		
		return response.getBody();
	}

	@Override
	public User updateUser(Long id, User user) {
		
		HttpEntity<User> requestEntity = new HttpEntity<>(user);
		
		ResponseEntity<User> response = restTemplate.exchange(
				REST_SERVICE_URI + id.toString(),
				HttpMethod.PUT,
				requestEntity,
				User.class);
		
		return response.getBody();
	}

	@Override
	public Boolean deleteUser(Long id) {
		
		ResponseEntity<Boolean> response = restTemplate.exchange(
				REST_SERVICE_URI + id.toString(),
				HttpMethod.PUT,
				null,
				Boolean.class);
		
		return response.getBody();
	}
	

}
