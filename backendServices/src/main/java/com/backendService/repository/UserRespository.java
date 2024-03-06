package com.backendService.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.backendService.entity.User;

@Repository
public interface UserRespository extends JpaRepository<User, String> {
	
		public User findByUserName(String userName);
		
		public User findByEmail(String email);
		
		public User findByUserNameAndPassword(String userName, String password);
		@Query( value="SELECT COALESCE(MAX(CAST(NULLIF(SUBSTRING(userID, 37), '') AS INT)) + 1, 1) FROM registered_users",nativeQuery =true)
		int generateSequence();
		
		public List<User> findBySecurityAnswer1OrSecurityAnswer2(String securityAnswer1, String securityAnswer2);
		
}
