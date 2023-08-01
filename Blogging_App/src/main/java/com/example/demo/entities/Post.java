package com.example.demo.entities;



import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="post")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	//used identity instead of auto because auto create two table 
	private Integer postId;
	
	@Column(name="post_title", length=20, nullable=false)
	private String title;
	
	@Column(length=1000)
	private String content;
	
	@Column
	private String imageName;
	
	@Column
	private Date addedDate;
	
	@ManyToOne
	private Category category;
	
	@ManyToOne
	private User user;
}
 