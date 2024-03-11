package com.kodnest.tunehub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kodnest.tunehub.entity.User;
import com.kodnest.tunehub.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/register")
	public String addUser(@ModelAttribute User user)
	{	

		//email taken from the registration form.
		String email = user.getEmail();
		//checking if email as entered in registration form is present in the database or not.
		boolean status = userService.emailExists(email);

		//if the email is not present then we will add the user or else we will return  "User already exists".
		if(!status)	userService.addUser(user);			
		else  return  "/login";

		return "/login";
	}

	//In this method we are checking if the user has entered valid email and password
	@PostMapping("/validate")
	public String validate(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session) {
		if(userService.validateUser(email,password)) {

			//And here we are checking if the user ia an admin or a customer.
			String role = userService.getRole(email);
			
			
			session.setAttribute("email", email);
			
			if(role.equals("admin")) {
				return "/adminhome";
			}else {
				return "/userhome";
			}
		}else {
			return "login";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
	}

}
