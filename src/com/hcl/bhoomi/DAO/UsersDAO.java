package com.hcl.bhoomi.DAO;

import com.hcl.bhoomi.model.Ride;
import com.hcl.bhoomi.model.Users;

public interface UsersDAO {
	
	public Users usersLogin(String userName,String password);
	public int usersRegister(Users users);
	public int insertUserRideDateTime(Ride ride);
	public int insertUserRidePickupDrop(String userId,String pickUp,String drop);
	public int insertUserRideDetails(String userId,String dateTime,String pickUp,String drop);


}
