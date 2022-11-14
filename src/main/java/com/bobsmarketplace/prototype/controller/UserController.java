package com.bobsmarketplace.prototype.controller;

import com.bobsmarketplace.prototype.entity.User;
import com.bobsmarketplace.prototype.entity.dto.login.UserLogin;
import com.bobsmarketplace.prototype.entity.dto.login.UserLoginResponse;
import com.bobsmarketplace.prototype.entity.dto.register.UserRegister;
import com.bobsmarketplace.prototype.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody UserRegister userRegister) {
		try {
			userService.register(userRegister);
			return ResponseEntity.ok("Registration Successful!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;	
		}
		
	}
	
	// @PostMapping("/process_register")
	// public String processRegister(User user) {
	// 	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	// 	String encodedPassword = passwordEncoder.encode(user.getPassword());
	// 	user.setPassword(encodedPassword);
		
	// 	userRepo.save(user);
		
	// 	return "register_success";
	// }

	@PostMapping("/login")
	public <T extends UserLoginResponse> UserLoginResponse login(@RequestBody UserLogin userLogin) {
		return userService.login(userLogin);
	}
}
