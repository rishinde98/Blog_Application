package com.example.demo.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.User;
import com.example.demo.entities.UserDto;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repositories.UserRepo;
import com.example.demo.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepo userRepo;
	
	//create
	@Override
	public UserDto createUser(UserDto userDto) {
		//we created userToUserDto and method to covert userDto to user and vv
		User user = this.dtoToUser(userDto);
		User savedUser=this.userRepo.save(user);
		return null;
	}

	//update
	@Override
	public UserDto updateUser(UserDto userDto, Integer uid) {
		User user = this.userRepo.findById(uid).orElseThrow(() -> new ResourceNotFoundException("User","id",uid));
		
		user.setName(userDto.getName());
		user.setAbout(userDto.getAbout());
		user.setEmail(userDto.getEmail());
		user.setPwd(userDto.getPwd());
		
		User updatedUser =  this.userRepo.save(user);
		UserDto userDto1 = this.userToDto(updatedUser);
		
		return userDto1;
	}

	//getUser
	@Override
	public UserDto getUserById(Integer uid) {
		User user = this.userRepo.findById(uid)
							.orElseThrow(()-> new ResourceNotFoundException("User","id",uid));
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = this.userRepo.findAll();
		
		List<UserDto> userDtos = users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer uid) {
		
		User user = this.userRepo.findById(uid)
							.orElseThrow(()-> new ResourceNotFoundException("User","id",uid));
		this.userRepo.delete(user);	
		
	}

	//this method will change dto to entity
	private User dtoToUser(UserDto userDto)
	{
		User user = new User();
		user.setUid(userDto.getUid());
		user.setName(userDto.getName());
		user.setPwd(userDto.getPwd());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		
		return user;

	}
	
	private UserDto userToDto(User user)
	{
		UserDto userDto = new UserDto();
		userDto.setUid(user.getUid());
		userDto.setName(user.getName());
		userDto.setPwd(user.getPwd());
		userDto.setEmail(user.getEmail());
		userDto.setAbout(user.getAbout());
		
		return userDto;

	}
	
}
