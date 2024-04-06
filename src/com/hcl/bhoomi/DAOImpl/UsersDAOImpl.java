package com.hcl.bhoomi.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.hcl.bhoomi.Connection.DatabaseConnection;
import com.hcl.bhoomi.DAO.UsersDAO;
import com.hcl.bhoomi.Exceptions.AlreadyExistOrNotException;
import com.hcl.bhoomi.Exceptions.InvalidLoginCrendentialException;
import com.hcl.bhoomi.Exceptions.UserAlreadyExistException;
import com.hcl.bhoomi.model.Ride;
import com.hcl.bhoomi.model.Users;



public class UsersDAOImpl implements UsersDAO{
	
	private static  PreparedStatement prepareStatement,prepareStatement2;
	private static ResultSet resultSet ;
	private static final String SELECT_USER_QUERY="select * from users";
	private static final String INSERT_USER_QUERY="insert into users values(?,?,?)";
	private static final String SELECT_USER_BOOKINGS_QUERY="select * from userbookings";
	private static final String INSERT_USER_BOOKINGS_QUERY="insert into userbookings(user_Id,date_time) values(?,?)";
	private static final String SELECT_USER_BOOKINGS_USERID_QUERY="select * from userbookings where user_id=?";
	private static final String UPDATE_USER_BOOKINGS_LOCATION_QUERY="Update userbookings set pickup_location=?,drop_location=? where booking_id=?";
	private static final String SELECT_USER_RIDE_QUERY="select ride_id,vehicle_id from ride where date_time=? and pickup_location=? and drop_location=?";
	private static final String INSERT_USER_RIDE_QUERY="insert into userbookings(user_id,date_time,pickup_location,drop_location,ride_id,vehicle_id) values(?,?,?,?,?,?)";
	static Users user=null;
	int count=0;
	@Override
	public Users usersLogin(String userName, String password) {
		
		boolean userflag=false;
		try(Connection conn=DatabaseConnection.getConnectionDb())
		{
			prepareStatement= conn.prepareStatement(SELECT_USER_QUERY);
			resultSet = prepareStatement.executeQuery();
			
			while(resultSet.next())
			{
				 if(resultSet.getString(2).equalsIgnoreCase(userName) && resultSet.getString(3).equals(password)  )
				{
					userflag=true;	
					break;
				}	
			}
			
			if(userflag==true)
			{
				user=new Users(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3));
				System.out.println("\""+userName+" successfully logged in \"");
			}
			else
			{
				throw new InvalidLoginCrendentialException("you are  a invalid user");
			}
			
		}
		catch(Exception e)
		{
			e.getMessage();
		}
		
	return user;	
	}
	
	@Override
	public int usersRegister(Users users) {
		boolean userflag=false;
		try(Connection conn=DatabaseConnection.getConnectionDb())
		{
			//To check whether user exist or not
			prepareStatement= conn.prepareStatement(SELECT_USER_QUERY);
			resultSet = prepareStatement.executeQuery();
			
			while(resultSet.next())
			{
				
				if(resultSet.getString(2).equalsIgnoreCase(users.getUserName()) && resultSet.getString(3).equals(users.getPassword()))
				{
					userflag=true;
					break;
				}
				else
				{
					
					userflag=false;
				}
			
			}
			if(userflag==true)
			{
				throw new UserAlreadyExistException("user Already exit u can't register again please select login");

			}
			
			else
			{
				//if user not exist new user details inserted into database
				prepareStatement2= conn.prepareStatement(INSERT_USER_QUERY);
				prepareStatement2.setString(1, users.getUserId());
				prepareStatement2.setString(2, users.getUserName());
				prepareStatement2.setString(3, users.getPassword());
				count=prepareStatement2.executeUpdate();
			}
			
			
		}
		catch(UserAlreadyExistException e)
		{
			System.out.println(e.getMessage());
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return count;
	}

	@Override
	public int insertUserRideDateTime(Ride ride) {
		// To insert users date and time of ride into database
		boolean flag=false;
		try(Connection conn=DatabaseConnection.getConnectionDb())
		{
			//To check whether ride exist or not
			prepareStatement= conn.prepareStatement(SELECT_USER_BOOKINGS_QUERY);
			resultSet = prepareStatement.executeQuery();
			
			while(resultSet.next())
			{
				if(resultSet.getString(6).equals(ride.getRideId()))
				{
					flag=true;
				}
			}
			if(flag==true)
			{
				throw new AlreadyExistOrNotException(ride.getRideId()+" has already a ride booked select different time");
			}
			else
			{
				prepareStatement= conn.prepareStatement(INSERT_USER_BOOKINGS_QUERY);
				prepareStatement.setString(1, ride.getUserId());
				prepareStatement.setTimestamp(2, DateTimeConversion(ride.getDateTime()));
				count=prepareStatement.executeUpdate();
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return count;
	}
	public Timestamp DateTimeConversion(String dateTime)
	{
		Timestamp timestamp=null;
		try
		{
			//parse the string into a Timestamp object
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-mm-dd HH:MM:SS");
			Date parsedDate=dateFormat.parse(dateTime);
			timestamp=new Timestamp(parsedDate.getTime());
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return timestamp;
	}

	@Override
	public int insertUserRidePickupDrop(String userId, String pickUp, String drop) {
		
		// To insert users date and time of ride into database
		boolean flag=false;
		try(Connection conn=DatabaseConnection.getConnectionDb())
		{	int id = 0;
			//To check whether ride exist or not
			prepareStatement= conn.prepareStatement(SELECT_USER_BOOKINGS_USERID_QUERY);
			prepareStatement.setString(1,userId);
			System.out.println(userId);
			resultSet=prepareStatement.executeQuery();
			while(resultSet.next())
			{
				if(resultSet.getString(2).equals(userId)  )
				{
					flag=true;
					id=Integer.parseInt(resultSet.getString(1));
					break;
				}
			}
			if(flag==false)
			{
				throw new AlreadyExistOrNotException("It contains vlaues so u can't update");
			}
			else
			{
				prepareStatement= conn.prepareStatement(UPDATE_USER_BOOKINGS_LOCATION_QUERY);
				prepareStatement.setString(1, pickUp);
				prepareStatement.setString(2, drop);
				prepareStatement.setInt(3,id );

				count=prepareStatement.executeUpdate();	
			}
		}
		catch(Exception e)
		{
			//e.printStackTrace();
			System.out.println(e.getMessage());
		}
				
		return count;
	}

	@Override
	public int insertUserRideDetails(String userId, String dateTime, String pickUp, String drop) {
		// TODO Auto-generated method stub
		boolean flag=false;
		int count=0;
		try(Connection conn=DatabaseConnection.getConnectionDb())
		{	int id = 0;
			//To check whether ride exist or not
			prepareStatement= conn.prepareStatement(SELECT_USER_RIDE_QUERY);
			prepareStatement.setTimestamp(1, DateTimeConversion(dateTime));
			prepareStatement.setString(2, pickUp);
			prepareStatement.setString(3, drop);
			resultSet=prepareStatement.executeQuery();
			resultSet.next();
			System.out.println(resultSet.getString(1));
			/*if(resultSet.next())
			{
				prepareStatement=conn.prepareStatement(INSERT_USER_RIDE_QUERY);
				prepareStatement.setString(2, userId);
				prepareStatement.setTimestamp(3, DateTimeConversion(dateTime));
				prepareStatement.setString(4, pickUp);
				prepareStatement.setString(5, drop);
				prepareStatement.setString(6, resultSet.getString(1));
				prepareStatement.setString(7, resultSet.getString(2));
				count=prepareStatement.executeUpdate();
			}
			else
			{
				System.out.println("Failed");
			}*/

			
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		
		return count;
	}

}
