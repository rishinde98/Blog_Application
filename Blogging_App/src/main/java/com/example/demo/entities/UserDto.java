package com.example.demo.entities;

import org.hibernate.validator.constraints.Email;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserDto {


	private int uid;
	
	@NotNull
	@Size(min=4,message="username must be min of 4 characters !")
	private String name;
	
	@jakarta.validation.constraints.Email(message="Email address is not valid !")
	private String email;
	
	@NotEmpty
	@Size(min=3,max=12, message="password must be 3-12 characters")
//	@Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=\\S+$/).{8,}$") //?= positive lookahead assertion 
	private String pwd;
	
	
	private String about;

	public UserDto() {
		// TODO Auto-generated constructor stub
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}
	
}
