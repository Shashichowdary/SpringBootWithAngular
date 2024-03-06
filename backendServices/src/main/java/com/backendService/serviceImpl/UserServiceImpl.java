package com.backendService.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backendService.entity.User;
import com.backendService.exception.ResourceNotFoundException;
import com.backendService.repository.UserRespository;
import com.backendService.service.UserService;
import com.backendService.utils.ServiceUtils;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRespository userRespository;
	
	@Autowired
	ServiceUtils serviceUtils;
	
	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public List<User> getAllUsers() {
		return userRespository.findAll();
	}

	@Override
	public User createUser(User user) {
		String userName = serviceUtils.generateUserName(user.getFirstName(), user.getLastName());
		if(userName.startsWith("ERROR") || serviceUtils.validateEmailId(user.getEmail())) {
			logger.error("Exception Occurred at while creating new User");
			throw new ResourceNotFoundException("Duplicate UserName or EmailId!!!");
		}else {
			user.setUserName(userName);
		}
		return userRespository.save(user);
	}

	@Override
	public Optional<User> getUserById(String id) {
		return userRespository.findById(id);
	}

	@Override
	public User updateUser(User userDetails, String userId) {
		User user = userRespository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not exist with userId : "+userId));
		try {
		user.setFirstName(userDetails.getFirstName());
		user.setLastName(userDetails.getLastName());
		user.setUserName(user.getFirstName()+" "+userDetails.getLastName());
		user.setDateOfBirth(userDetails.getDateOfBirth());
		user.setEmail(userDetails.getEmail());
		user.setMobileNumber(userDetails.getMobileNumber());
		}catch(Exception e) {
			logger.error("Exception Occurred while Updating the user", e);
		}
		return userRespository.save(user);
	}

	@Override
	public Map<String, Boolean> deleteUser(String userId) {
		Map<String, Boolean> response = new HashMap<>();
		try {
		User user = userRespository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not exist with userId :"+userId));
		userRespository.delete(user);
		response.put("deleted", Boolean.TRUE);
		}catch(Exception e) {
			logger.error("Exception Occurred while deleting the user", e);
		}
		return response;
	}

	@Override
	public User getUserByUserName(String userName) {
		User user = userRespository.findByUserName(userName);
		if(user==null) {
			throw new ResourceNotFoundException("User with the Given UserName doesn't Exist");
		}	
		return user;
	}

	@Override
	public List<String> getSecurityQuestions(String userName) {
		List<String> securityQuestions = new ArrayList<>();
		User user = userRespository.findByUserName(userName);
		if(user==null) {
			throw new ResourceNotFoundException("User with the Given UserName doesn't Exist");
		}else {
			securityQuestions.add(user.getSecurityQuestion1());
			securityQuestions.add(user.getSecurityQuestion2());
		}
		return securityQuestions;
	}

	@Override
	public Boolean resetPassword(User user) {
		boolean validAnswer = false;
		if ((user.getSecurityAnswer1() != null && !user.getSecurityAnswer1().isEmpty())
				|| (user.getSecurityAnswer2() != null && !user.getSecurityAnswer2().isEmpty())) {
			List<User> bySecurityAnswer1OrSecurityAnswer2 = userRespository
					.findBySecurityAnswer1OrSecurityAnswer2(user.getSecurityAnswer1(), user.getSecurityAnswer2());

			if (!bySecurityAnswer1OrSecurityAnswer2.isEmpty()) {
				User updatePassword = bySecurityAnswer1OrSecurityAnswer2.get(0);
				validAnswer = true;
				updatePassword.setPassword(user.getPassword());
				userRespository.save(updatePassword);
			}

		}
		return validAnswer;

	}

	@Override
	public User updateUserByUserName(User user, String userName) {
		User userByUserName = userRespository.findByUserName(userName);
		try {
		if(userByUserName==null) {
			throw new ResourceNotFoundException("User not exist with User Name : "+userName);
		}else {
			userByUserName.setFirstName(user.getFirstName());
			userByUserName.setLastName(user.getLastName());
			userByUserName.setUserName(user.getFirstName()+" "+user.getLastName());
			userByUserName.setDateOfBirth(user.getDateOfBirth());
			userByUserName.setEmail(user.getEmail());
			userByUserName.setMobileNumber(user.getMobileNumber());
		}
		}catch(Exception e) {
			logger.error("Exception Occurred while Updating the user by user name", e);
		}
		return userRespository.save(userByUserName);
	}
}
