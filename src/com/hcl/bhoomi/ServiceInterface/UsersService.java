package com.hcl.bhoomi.ServiceInterface;

import java.io.IOException;

import com.hcl.bhoomi.model.Ride;
import com.hcl.bhoomi.model.Users;

public interface UsersService {
	
	public void usersRegister(Users users) throws IOException;
	public void addUserRideDateTime(Ride ride);
	public void addUserRidePickupDrop(String userId,String pickup,String drop);
	public void addUserRideDetails(String userId,String dateTime,String pickup,String drop);

}
