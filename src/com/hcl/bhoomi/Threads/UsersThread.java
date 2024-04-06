package com.hcl.bhoomi.Threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import com.hcl.bhoomi.Exceptions.InputInvalidException;
import com.hcl.bhoomi.mainClasses.Main;
import com.hcl.bhoomi.model.Users;

public class UsersThread extends Thread{
	
	
	Users user;
	public UsersThread()
	{
		
	}
	public UsersThread(Users user)
	{
		this.user=user;
	}
	@Override
	public void run() {
		
		try
		{
			Main.menuForUsers(user);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	

}
