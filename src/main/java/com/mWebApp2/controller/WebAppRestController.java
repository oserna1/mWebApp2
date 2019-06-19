package com.mWebApp2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mWebApp2.manager.MoodManager;
import com.mWebApp2.manager.UserManager;
import com.mWebApp2.model.Mood;
import com.mWebApp2.model.User;


@RestController
public class WebAppRestController {
	
	@Autowired
	private MoodManager moodManager;
	
	@Autowired
	private UserManager userManager;
	
	
	//-------------------Retrieve All Users--------------------------------------------------------
    
    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> users = userManager.listAllUsers();
        if(users.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    
  //-------------------Retrieve Single User--------------------------------------------------------
    
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable("id") long id) {

        User user = userManager.getUserbyId(id);
        if (user == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    

  //-------------------Create a User--------------------------------------------------------
    
    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public ResponseEntity<Boolean> createUser(@RequestBody User user) {
 
        if(userManager.createUser(user)) {
        	return new ResponseEntity<>(true, HttpStatus.CREATED);
        }else {
        	return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    

  //------------------- Update a User --------------------------------------------------------
    
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
  
        User currentUser = userManager.updateUser(id, user);
         
        if (currentUser==null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }
    
    
  //------------------- Delete a User --------------------------------------------------------
    
   
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteUser(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting User with id " + id);
 
        if(userManager.deleteUser(id)) {
        	return new ResponseEntity<>(HttpStatus.OK);
        }else {
        	return new ResponseEntity<>(false,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

  //-------------------Retrieve Moods by UserId--------------------------------------------------------
    
    @RequestMapping(value = "/track/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Mood>> getMoods(@PathVariable("id") Long uid) {

        List<Mood> moods = moodManager.listAllMoodsByUserId(uid);
        if (moods == null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(moods, HttpStatus.OK);
    }

    
  //-------------------Create a Mood--------------------------------------------------------
    
    @RequestMapping(value = "/track/", method = RequestMethod.POST)
    public ResponseEntity<Boolean> createMood(@RequestBody Mood mood) {
        return new ResponseEntity<>( moodManager.createMood(mood)!=null, HttpStatus.OK);
    }
    
    
  //------------------- Update a Mood --------------------------------------------------------
    
    @RequestMapping(value = "/track/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Mood> updateMood(@PathVariable("id") long id, @RequestBody Mood mood) {
        System.out.println("Updating Mood " + id);
         
        Mood currentMood = moodManager.updateMood(id, mood);
         
        if (currentMood==null) {
            System.out.println("Mood with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }  
        return new ResponseEntity<>(currentMood, HttpStatus.OK);
    }
    
  //------------------- Delete a Mood --------------------------------------------------------
    
    @RequestMapping(value = "/track/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteMood(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Mood with id " + id);
 
        if(moodManager.deleteMood(id)) {
        	return new ResponseEntity<>(true, HttpStatus.OK);
        }
        else {
        	return new ResponseEntity<>(false, HttpStatus.CONFLICT);
        }
    }

}
