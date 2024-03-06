package com.backendService.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backendService.entity.LoginUser;
import com.backendService.serviceImpl.LoginServiceImpl;
import com.backendService.utils.ServiceUtils;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/loginService/api")
public class LoginController {
	
	@Autowired
	LoginServiceImpl loginService;
	
	Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@PostMapping("/login")
	public Boolean validateUser(@RequestBody LoginUser loginUser, HttpServletResponse httpServletResponse) {
		logger.info("user request:{}", loginUser);
		Boolean response = loginService.validateUser(loginUser.getUserName(), loginUser.getPassword());
		logger.info("User is validated and response is : {}", response);

		if (response) {
			String userName = ServiceUtils.removeWhiteSpaces(loginUser.getUserName());
			logger.info("userName Cookie is set here : {}", userName);
			Cookie userNameCookie = new Cookie("username", userName);
			userNameCookie.setMaxAge(3600);
			userNameCookie.setPath("/");
			httpServletResponse.addCookie(userNameCookie);
		}

		return response;

	}


}
