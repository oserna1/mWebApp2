package com.mWebApp2.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mWebApp2.model.Mood;

@Service
public class MoodManagerImpl implements MoodManager{
	
	public static final String REST_SERVICE_URI = "http://localhost:8090/com.mWebAppBackend/track/";

	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public List<Mood> listAllMoodsByUserId(Long id) {
		ResponseEntity<List<Mood>> response = restTemplate.exchange(
				REST_SERVICE_URI + id.toString(),
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<Mood>>(){});
		
		return response.getBody();
	}
	
	@Override
	public Boolean createMood(Mood mood) {
		
		HttpEntity<Mood> requestEntity = new HttpEntity<>(mood);
		
		ResponseEntity<Boolean> response = restTemplate.exchange(
				REST_SERVICE_URI,
				HttpMethod.POST,
				requestEntity,
				Boolean.class);
		
		return response.getBody();
				
		
	}
	
	@Override
	public Mood updateMood(Long id, Mood mood) {
		
		HttpEntity<Mood> requestEntity = new HttpEntity<>(mood);
		
		ResponseEntity<Mood> response = restTemplate.exchange(
				REST_SERVICE_URI + id.toString(),
				HttpMethod.PUT,
				requestEntity,
				Mood.class);
		
		return response.getBody();
	}
	
	@Override
	public Boolean deleteMood(Long id) {
		
		ResponseEntity<Boolean> response = restTemplate.exchange(
				REST_SERVICE_URI + id.toString(),
				HttpMethod.DELETE,
				null,
				Boolean.class);
		
		return response.getBody();
	}
}
