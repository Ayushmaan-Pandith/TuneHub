package com.kodnest.tunehub.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodnest.tunehub.entity.User;
import com.kodnest.tunehub.repository.UserRepository;
import com.kodnest.tunehub.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;

	@Override
	public String addUser(User user) {
		userRepository.save(user);
		return "User Added";
	}

	//To check the duplicate entries.
	//register
	@Override
	public boolean emailExists(String email) {
		boolean exists = userRepository.existsByEmail(email);
		return exists;
	}
	
	@Override
	public boolean validateUser(String email, String password) {
		boolean emailExists = userRepository.existsByEmail(email);
		boolean passwordExists = userRepository.existsByPassword(password);
		if(emailExists&&passwordExists) {
			return true;
		}else {
			return false;			
		}
	}

	@Override
	public String getRole(String email) {
		User user = userRepository.findByEmail(email);
		return user.getRole();
	}

	@Override
	public User getUser(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void updateUser(User user) {
		userRepository.save(user);
		
	}
}
