package com.kodnest.tunehub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavController {

	@GetMapping("/login")
	public String login() {
	    return "login";  // login.html is the name of your login page
	}
	
	@GetMapping("/registration")
	public String registration() {
	    return "registration";  // registration.html is the name of your registration page
	}
	
	@GetMapping("/newsong")
	public String newsong() {
		return "newsong";
	}
}
