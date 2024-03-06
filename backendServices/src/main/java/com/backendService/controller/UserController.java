package com.backendService.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backendService.entity.User;
import com.backendService.service.UserService;
import com.google.gson.Gson;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/userService/api")
public class UserController {
	
	@Autowired
	UserService userService;
	
	Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@GetMapping("/getRegisteredUsers")
	public List<User> getAllUsers(){
		List<User> response=userService.getAllUsers();
		logger.info("getRegisteredUsers API is call and got Response: {}", new Gson().toJson(response));
		return response;
	}
	
	@PostMapping("/createNewUser")
	public ResponseEntity<User> createUser(@Validated @RequestBody User user) {
		User response = userService.createUser(user);
		logger.info("createNewUser API is called and response is : {}", new Gson().toJson(response));
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/getUserById/{userId}")
	public ResponseEntity<Optional<User>> getUserByUserId(@PathVariable String userId) {
		Optional<User> response = userService.getUserById(userId);
		logger.info("getUserById API is called with userId {} and response is : {}", userId, response.toString());
		return ResponseEntity.ok(response);
	}
	
	/*
	 * @PutMapping("/updateUser/{userId}") public ResponseEntity<User>
	 * updateUser(@PathVariable String userId,@RequestBody User userDetails){ User
	 * response = userService.updateUser(userDetails, userId);
	 * //logger.info("updateUser API is called with userId and response is : {}",
	 * new Gson().toJson(response)); return ResponseEntity.ok(response); }
	 */
	
	@PutMapping("/updateUser/{userName}")
	public ResponseEntity<User> updateUserByUserName(@PathVariable String userName,@RequestBody User userDetails){
		User response = userService.updateUserByUserName(userDetails, userName);
		//logger.info("updateUser API is called with userId and response is : {}", new Gson().toJson(response));
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/deleteUser/{userId}")
	public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable String userId){
		Map<String, Boolean> response = userService.deleteUser(userId);
		logger.info("deleteUser API is called with userId and response is : {}", new Gson().toJson(response));
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/getUserByUserName/{userName}")
	public ResponseEntity<User> getUserByUserName(@PathVariable String userName){
		logger.info("getUserByUserName API is called with UserName {}", userName);
		User response = userService.getUserByUserName(userName);
		logger.info("getUserByUserName API is resonded with response {}", new Gson().toJson(response));
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/getSecurityQuestions/{userName}")
	public ResponseEntity<List<String>> getSecurityQuestions(@PathVariable String userName){
		logger.info("getSecurityQuestions API is called with userName {}", userName);
		List<String> response = userService.getSecurityQuestions(userName);
		logger.info("getSecurityQuestions API is resonded with response {}", new Gson().toJson(response));
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/resetPassword")
	public ResponseEntity<Boolean> resetPassword(@RequestBody User user){
		logger.info("resetPassword API is called with request {}", new Gson().toJson(user));
		Boolean resetPassword = userService.resetPassword(user);
		logger.info("Reset API is resonded with response {}", resetPassword);
		return ResponseEntity.ok(resetPassword);
		
	}
	

}
