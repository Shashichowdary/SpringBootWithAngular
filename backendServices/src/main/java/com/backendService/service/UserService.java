package com.backendService.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.backendService.entity.User;

public interface UserService {

	public List<User> getAllUsers();
	public User createUser(User user);
	public Optional<User> getUserById(String id);
	public User updateUser(User user, String userId);
	public User updateUserByUserName(User user, String userName);
	public Map<String ,Boolean> deleteUser(String userId);
	public User getUserByUserName(String userName);
	public List<String> getSecurityQuestions(String userName);
	public Boolean resetPassword(User user);
	
}
