package com.hcl.bhoomi.Exceptions;

public class InvalidLoginCrendentialException extends Exception {
	
	String msg;
	public InvalidLoginCrendentialException()
	{
		
	}
	public InvalidLoginCrendentialException(String msg)
	{
		super(msg);
		this.msg=msg;	
	}

}
