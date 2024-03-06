package com.backendService.utils;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class RandomStringGenerator {
		
	public static String generateRandomString() {
		int length =3;
		StringBuilder randomString =new StringBuilder();
		Random random =new Random();
		for(int i=0;i<length;i++) {
			char randomChar=(char)('A' + random.nextInt(26));
			randomString.append(randomChar);
		}
		return randomString.toString();
	}

}
