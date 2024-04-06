package com.hcl.bhoomi.model;

public class Owner {
	
	String ownerId,ownerName;
	String password;
	
	public Owner()
	{
		
	}
	public Owner(String ownerName, String password) {
		super();
		this.ownerName = ownerName;
		this.password = password;
	}
	
	public Owner( String ownerId,String ownerName, String password) {
		super();
		this.ownerName = ownerName;
		this.password = password;
		this.ownerId = ownerId;
	}
	public String getownerName() {
		return ownerName;
	}
	public void setownerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getownerId() {
		return ownerId;
	}
	public void setownerId(String ownerId) {
		this.ownerId = ownerId;
	}
	@Override
	public String toString() {
		return "Owner [userId=" + ownerId + ", userName=" + ownerName + ", password=" + password 
				+ "]";
	}
	
	
	

}
