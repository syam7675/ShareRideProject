package com.hcl.bhoomi.Threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

import com.hcl.bhoomi.DAO.OwnerDAO;
import com.hcl.bhoomi.DesignPatterns.OwnerFactory;

import com.hcl.bhoomi.Exceptions.AlreadyExistOrNotException;
import com.hcl.bhoomi.Exceptions.InputInvalidException;
import com.hcl.bhoomi.mainClasses.Main;
import com.hcl.bhoomi.model.Owner;
import com.hcl.bhoomi.model.Ride;
import com.hcl.bhoomi.model.Vehicle;

public class OwnerThread  extends Thread{
	
	Owner owner;
	public OwnerThread()
	{
		
	}
	public OwnerThread(Owner owner)
	{
		super();
		this.owner=owner;
	}
	@Override
	public void run() {
		
		try
		{
			Main.menuForOwner(owner);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		
	}
	
}
