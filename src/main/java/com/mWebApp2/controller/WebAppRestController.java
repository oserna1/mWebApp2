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

import com.mWebApp2.model.Mood;
import com.mWebApp2.model.User;
import com.mWebApp2.service.MoodService;
import com.mWebApp2.service.UserService;


@RestController
public class WebAppRestController {
	
	@Autowired
    private UserService userService;
	
	@Autowired
	private MoodService moodService;
	
	//-------------------Retrieve All Users--------------------------------------------------------
    
    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> users = userService.findAllUsers();
        if(users.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    
  //-------------------Retrieve Single User--------------------------------------------------------
    
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable("id") long id) {
        System.out.println("Fetching User with id " + id);
        User user = userService.findById(id);
        if (user == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    

  //-------------------Create a User--------------------------------------------------------
    
    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public ResponseEntity<Boolean> createUser(@RequestBody User user) {
        System.out.println("Creating User " + user.getUsername());
 
        if (userService.findByEmail(user.getEmail())!= null) {
            System.out.println("A User with email " + user.getEmail() + " already exist");
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        userService.saveUser(user);
        return new ResponseEntity<>(true, HttpStatus.CREATED);
    }
    
    

  //------------------- Update a User --------------------------------------------------------
    
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        System.out.println("Updating User " + id);
        User currentUser = userService.findById(id);
         
        if (currentUser==null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
         
        userService.updateUser(user);
        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }
    
    
  //------------------- Delete a User --------------------------------------------------------
    
   
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteUser(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting User with id " + id);
 
        User user = userService.findById(id);
        if (user == null) {
            System.out.println("Unable to delete. User with id " + id + " not found");
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(userService.deleteUserById(id), HttpStatus.OK);
    }
    

  //-------------------Retrieve Moods by UserId--------------------------------------------------------
    
    @RequestMapping(value = "/track/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Mood>> getMoods(@PathVariable("id") Long uid) {
        System.out.println("Fetching Moods with uid " + uid);
        List<Mood> moods = moodService.findByUid(uid);
        if (moods == null) {
            System.out.println("Moods with user id: " + uid + " not found");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(moods, HttpStatus.OK);
    }

    
  //-------------------Create a Mood--------------------------------------------------------
    
    @RequestMapping(value = "/track/", method = RequestMethod.POST)
    public ResponseEntity<Boolean> createMood(@RequestBody Mood mood) {
        return new ResponseEntity<>( moodService.saveMood(mood)!=null, HttpStatus.OK);
    }
    
    
  //------------------- Update a Mood --------------------------------------------------------
    
    @RequestMapping(value = "/track/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Mood> updateMood(@PathVariable("id") long id, @RequestBody Mood mood) {
        System.out.println("Updating Mood " + id);
         
        Mood currentMood = moodService.findById(id);
         
        if (currentMood==null) {
            System.out.println("Mood with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }  
        return new ResponseEntity<>(moodService.updateMood(mood), HttpStatus.OK);
    }
    
  //------------------- Delete a Mood --------------------------------------------------------
    
    @RequestMapping(value = "/track/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteMood(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Mood with id " + id);
 
        Mood mood = moodService.findById(id);
        if (mood == null) {
            System.out.println("Unable to delete. Mood with id " + id + " not found");
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }  
        return new ResponseEntity<>(moodService.deleteMoodById(id), HttpStatus.OK);
    }

}
