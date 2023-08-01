package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.payloads.CategoryDto;


public interface CategoryService {

	//create
	 CategoryDto createCategory(CategoryDto categoryDto);
	 
	 //update
	 CategoryDto updateCataegoty(CategoryDto categoryDto, Integer categoryId);
	
	 //delete
	 void deleteCategory(Integer catId);
	 
	 //get
	 CategoryDto getCategory(Integer catId);
	 
	 //get all'
	 List<CategoryDto> getCategories();
	
}
