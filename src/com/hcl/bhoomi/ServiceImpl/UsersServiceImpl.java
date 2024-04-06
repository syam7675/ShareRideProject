package com.hcl.bhoomi.ServiceImpl;

import java.io.IOException;

import com.hcl.bhoomi.DAO.UsersDAO;
import com.hcl.bhoomi.DesignPatterns.UsersFactory;
import com.hcl.bhoomi.ServiceInterface.UsersService;
import com.hcl.bhoomi.mainClasses.Main;
import com.hcl.bhoomi.model.Ride;
import com.hcl.bhoomi.model.Users;

public class UsersServiceImpl implements UsersService
{
	static UsersDAO usersDAO=UsersFactory.getUserDAO();
	int count=0;
	@Override
	public void usersRegister(Users users) throws IOException {
		count=usersDAO.usersRegister(users);
		if(count>0)
		{
			System.out.println("\""+users.getUserName()+" successfully registered \"");
			Main.mainOptions();
		}
	}
	@Override
	public void addUserRideDateTime(Ride ride) {
		//To add users date and time of the ride 
		count=usersDAO.insertUserRideDateTime(ride);
		if(count>0)
		{
			System.out.println("Successfully added");
		}
		
	}
	@Override
	public void addUserRidePickupDrop(String userId, String pickup, String drop) {
		// To  add users pickup and drop of the ride
		count=usersDAO.insertUserRidePickupDrop(userId,pickup,drop);
		if(count>0)
		{
			System.out.println("Successfully added");
		}
		
	}
	@Override
	public void addUserRideDetails(String userId, String dateTime, String pickup, String drop) {
		// TODO Auto-generated method stub
		count=usersDAO.insertUserRideDetails(userId,dateTime,pickup,drop);
		if(count>0)
		{
			System.out.println("Successfully added");
		}
		
		
	}

}
