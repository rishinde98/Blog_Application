package com.example.demo.payloads;

public class ApiResponse {

	private String message;
	private boolean success;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isSucess() {
		return success;
	}
	public void setSucess(boolean sucess) {
		this.success = sucess;
	}
	public ApiResponse(String message, boolean sucess) {
		super();
		this.message = message;
		this.success = sucess;
	}
	public ApiResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
