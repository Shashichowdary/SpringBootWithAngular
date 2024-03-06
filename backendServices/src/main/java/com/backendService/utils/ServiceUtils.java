package com.backendService.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.backendService.entity.User;
import com.backendService.repository.UserRespository;

@Component
public class ServiceUtils {

	@Autowired
	UserRespository respository;

	public String generateUserName(String firstName, String lastName) {
		String userName = "";
		User user = respository.findByUserName(fetchUserName(firstName, lastName));
		if (user == null) {
			userName = fetchUserName(firstName, lastName);
		} else {
			userName = "ERROR";
		}
		return userName;
	}

	public String fetchUserName(String firstName, String lastName) {
		return firstName + " " + lastName;
	}

	public boolean validateEmailId(String email) {
		User user = respository.findByEmail(email);
		if (user != null) {
			return true;
		} else {
			return false;
		}
	}

	public static String removeWhiteSpaces(String value) {
		StringBuilder resultBuilder = new StringBuilder();
		for (char c : value.toCharArray()) {
			if (!Character.isWhitespace(c)) {
				resultBuilder.append(c);
			}
		}
		return resultBuilder.toString();
	}

	public static String getCookie(HttpServletRequest httpServletRequest) {
		Cookie[] cookies = httpServletRequest.getCookies();

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("username".equals(cookie.getName())) {
					String username = cookie.getValue();
					return username;
				}
			}
		}

		return "";
	}
}
