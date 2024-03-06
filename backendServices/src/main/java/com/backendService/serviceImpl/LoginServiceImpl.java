package com.backendService.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backendService.entity.User;
import com.backendService.repository.UserRespository;
import com.backendService.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired 
	UserRespository respository;

	@Override
	public Boolean validateUser(String userName, String password) {
		User user = respository.findByUserNameAndPassword(userName, password);
		if(user==null) {
			return false;
		}else {
			return true;
		}
	}

}
