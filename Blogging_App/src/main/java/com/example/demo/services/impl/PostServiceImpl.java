package com.example.demo.services.impl;


import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Category;
import com.example.demo.entities.Post;
import com.example.demo.entities.User;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.payloads.PostDto;
import com.example.demo.payloads.PostResponse;
import com.example.demo.repositories.CategoryRepo;
import com.example.demo.repositories.PostRepo;
import com.example.demo.repositories.UserRepo;
import com.example.demo.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer catId) {
		
		User user= this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user", "user id",userId));
		
		Category category = this.categoryRepo.findById(catId).orElseThrow(()->new ResourceNotFoundException("Category", "cat id",catId));
		
		Post post= this.modelMapper.map(postDto, Post.class);
	    post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post newPost = this.postRepo.save(post);
		
		return this.modelMapper.map(newPost,PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto,Integer postId) {
		
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "post id", postId));

		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		
		Post updatedPost = this.postRepo.save(post);
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "post id", postId));

		this.postRepo.delete(post);
	} 

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy, String sortDir) {
	
		Sort sort=null;
		
		if(sortDir.equalsIgnoreCase("asc"))
		{
			sort = Sort.by(sortBy).ascending();
			
		}else
			sort = Sort.by(sortBy).descending();
		
		Pageable p = PageRequest.of(pageNumber,pageSize,sort );
		
		Page<Post> pagePost = this.postRepo.findAll(p);
		
		List<Post> allPosts = pagePost.getContent();
		
		List<PostDto> postDtos = allPosts.stream().map((post)->this.modelMapper.map(post, PostDto.class))
								.collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
	
		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		// TODO Auto-generated method stub
		
		Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "post id", postId));
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer catId) {

		//fetch cat using catid --> used this category to search all posts --> covert this post to postDto
		Category cat = this.categoryRepo.findById(catId).orElseThrow(()->new ResourceNotFoundException("Category", "category id",catId));
	
		List<Post> posts = this.postRepo.findByCategory(cat);
		
		List<PostDto> postDtos = posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return postDtos;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		
			User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "user id",userId));
			
			List<Post> posts = this.postRepo.findByUser(user);

			List<PostDto> postDtos = posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

			return postDtos ;
	
	}

	@Override
	public List<PostDto> searchPosts(String keyword){
		List<Post> posts = this.postRepo.searchByTitle("%"+keyword+"%");
		
		List<PostDto> postDtos  = posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return postDtos;
	}




}
