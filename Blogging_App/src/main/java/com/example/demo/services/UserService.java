package com.example.demo.services;

import java.util.List;

import com.example.demo.entities.UserDto;

public interface UserService {


	UserDto createUser(UserDto user);
	
	UserDto updateUser(UserDto user,Integer uid);
	
	UserDto getUserById(Integer userId);
	
	List<UserDto> getAllUsers();
	
	void deleteUser(Integer userId);
	
	
}
