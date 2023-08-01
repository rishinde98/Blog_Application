 package com.example.demo.payloads;

public class CategoryDto {

	private Integer catId;
	private String catTitle;
	private String catDesc;
	public CategoryDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getCatId() {
		return catId;
	}
	public void setCatId(Integer catId) {
		this.catId = catId;
	}
	public String getCatTitle() {
		return catTitle;
	}
	public void setCatTitle(String catTitle) {
		this.catTitle = catTitle;
	}
	public String getCatDesc() {
		return catDesc;
	}
	public void setCatDesc(String catDesc) {
		this.catDesc = catDesc;
	}
	
	
}
