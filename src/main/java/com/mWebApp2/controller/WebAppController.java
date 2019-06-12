package com.mWebApp2.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.stereotype.Controller;

import com.mWebApp2.model.User;
import com.mWebApp2.service.UserService;

@Controller
public class WebAppController {
	
	@Autowired
    private UserService userService;
	
	@RequestMapping(value = "/login/" , method=RequestMethod.GET)
	public String completeLogin(@ModelAttribute (name="user") User user) {
		return "Login";
	}
	
	@RequestMapping(value="/validateCreate")
	public String validateCreateUser(@Valid @ModelAttribute (name="user") User user, BindingResult result) {
		
		if(result.hasErrors()) {
			System.out.println("Form has errors");
			return "Login";
		}else if(userService.findByEmail(user.getEmail()) != null){
			return "redirect:/admin/";
		}
		
		return "redirect:/track/";
	}
	
	
	@RequestMapping(value = "/track/" , method=RequestMethod.GET)
	public String trackMoods() {
		return "Track";
	}
	
	@RequestMapping(value = "/admin/" , method=RequestMethod.GET)
	public String userAdmin() {
		return "Admin";
	}

}
