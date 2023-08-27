package com.example.demo.payloads;

import java.sql.Date;

import com.example.demo.entities.Category;
import com.example.demo.entities.User;
import com.example.demo.entities.UserDto;

public class PostDto {

	private Integer id;
	
	private String title;
	
	private String content;

	private String imageName;
	
	public PostDto(Integer id) {
		super();
		this.id = id;
	}

	private Date addedDate;
	
	private CategoryDto categoryDto;
	
	private UserDto userDto;

	public PostDto() {
		super();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	public CategoryDto getCategoryDto() {
		return categoryDto;
	}

	public void setCategoryDto(CategoryDto categoryDto) {
		this.categoryDto = categoryDto;
	}

	public UserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}

	
	
	
}
