package com.hcl.bhoomi.model;

public class Users {
	
	String userId,userName,password;
	public Users()
	{
		
	}
	public Users( String userName,String password)
	{
		this.password=password;
		this.userName = userName;
	}
	public Users(String userId, String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
		this.userId=userId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Users [userId=" + userId + ", userName=" + userName + ", password=" + password
				+ "]";
	}
	
	
	
	
	
	

}
