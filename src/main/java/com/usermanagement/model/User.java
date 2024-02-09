package com.usermanagement.model;

public class User {
	
	private int id;
	private String name;
	private String email;
	private String country;
	
	public User(int id, String name, String email,String country) {
		this.id=id;
		this.country=country;
		this.email=email;
		this.name=name;
	}
	
	public User( String name, String email,String country) {
		
		//this constructor without id as we are auto-incrementing id in mysql
		
		this.country=country;
		this.email=email;
		this.name=name;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		country = country;
	}
	
	
}
