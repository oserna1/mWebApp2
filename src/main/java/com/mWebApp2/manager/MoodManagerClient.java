package com.mWebApp2.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.mWebApp2.model.Mood;

public class MoodManagerClient {
	
	public static final String REST_SERVICE_URI = "http://localhost:8080/mWebApp2/track/";

	@Autowired
	static RestTemplate restTemplate;
	
	@SuppressWarnings("unused")
	private static List<Mood> listAllMoodsByUserId(Long id) {
		ResponseEntity<List<Mood>> response = restTemplate.exchange(
				REST_SERVICE_URI + id.toString(),
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<Mood>>(){});
		
		return response.getBody();
	}
	
	@SuppressWarnings("unused")
	private static Boolean createMood(Mood mood) {
		
		HttpEntity<Mood> requestEntity = new HttpEntity<>(mood);
		
		ResponseEntity<Boolean> response = restTemplate.exchange(
				REST_SERVICE_URI,
				HttpMethod.POST,
				requestEntity,
				new ParameterizedTypeReference<Boolean>(){});
		
		return response.getBody();
				
		
	}
}
