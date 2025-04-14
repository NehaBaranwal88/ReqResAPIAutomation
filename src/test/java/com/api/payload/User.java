package com.api.payload;


public class User {
	//Request payloads
	String user_name;
	String user_job;
	String email;
	String password;
	
	public User(String email, String user_name, String user_job, String password) {
		this.user_name=user_name;
		this.user_job=user_job;
        this.email = email;
        this.password = password;
    }
	
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_job() {
		return user_job;
	}
	public void setUser_job(String user_job) {
		this.user_job = user_job;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String user_email) {
		this.email = user_email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
	
	
	