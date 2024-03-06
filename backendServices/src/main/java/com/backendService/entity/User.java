package com.backendService.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.backendService.utils.JsonDateSerializerDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "registered_users")
public class User {
	@Id
	@GeneratedValue(generator="userID")
	@GenericGenerator(name = "userID", strategy = "com.backendService.utils.CustomIdGenerator")
	private String userID;
	
	@Column(name="password")
	private String password;
	
	@Column(name="first_Name")
	private String firstName;
	
	@Column(name="last_Name")
	private String lastName;
	
	@Column(name="user_Name")
	private String userName;
	
	@Column(name="Gender")
	private String gender;
	
    @Column(name = "date_Of_Birth")
    @JsonSerialize(using = JsonDateSerializerDeserializer.Serializer.class)
    @JsonDeserialize(using = JsonDateSerializerDeserializer.Deserializer.class)
	private Date dateOfBirth;
	
	@Column(name="mobileNumber")
	private String mobileNumber;
	
	@Column(name="Email")
	private String email;
	
	@Column(name="securityQuestion1")
	private String securityQuestion1;
	
	@Column(name="securityQuestion2")
	private String securityQuestion2;
	
	@Column(name="securityAnswer1")
	private String securityAnswer1;
	
	@Column(name="securityAnswer2")
	private String securityAnswer2;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public User(String userID, String password, String firstName, String lastName, String userName, String gender,
			Date dateOfBirth, String mobileNumber, String email, String securityQuestion1, String securityQuestion2,
			String securityAnswer1, String securityAnswer2) {
		super();
		this.userID = userID;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.securityQuestion1 = securityQuestion1;
		this.securityQuestion2 = securityQuestion2;
		this.securityAnswer1 = securityAnswer1;
		this.securityAnswer2 = securityAnswer2;
	}
	
	


	public User(String password, String firstName, String lastName, String userName, String gender, Date dateOfBirth,
			String mobileNumber, String email, String securityQuestion1, String securityQuestion2,
			String securityAnswer1, String securityAnswer2) {
		super();
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.securityQuestion1 = securityQuestion1;
		this.securityQuestion2 = securityQuestion2;
		this.securityAnswer1 = securityAnswer1;
		this.securityAnswer2 = securityAnswer2;
	}


	public String getUserId() {
		return userID;
	}
	public void setUserId(String userID) {
		this.userID = userID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSecurityQuestion1() {
		return securityQuestion1;
	}
	public void setSecurityQuestion1(String securityQuestion1) {
		this.securityQuestion1 = securityQuestion1;
	}
	public String getSecurityQuestion2() {
		return securityQuestion2;
	}
	public void setSecurityQuestion2(String securityQuestion2) {
		this.securityQuestion2 = securityQuestion2;
	}

	public String getSecurityAnswer1() {
		return securityAnswer1;
	}
	public void setSecurityAnswer1(String securityAnswer1) {
		this.securityAnswer1 = securityAnswer1;
	}

	public String getSecurityAnswer2() {
		return securityAnswer2;
	}
	public void setSecurityAnswer2(String securityAnswer2) {
		this.securityAnswer2 = securityAnswer2;
	}
	

}
