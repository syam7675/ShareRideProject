package com.hcl.bhoomi.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hcl.bhoomi.Connection.DatabaseConnection;
import com.hcl.bhoomi.DAO.OwnerDAO;
import com.hcl.bhoomi.Exceptions.InvalidLoginCrendentialException;
import com.hcl.bhoomi.Exceptions.UserAlreadyExistException;
import com.hcl.bhoomi.model.Owner;
import com.hcl.bhoomi.model.Ride;
import com.hcl.bhoomi.model.Vehicle;


public class OwnerDAOImpl implements OwnerDAO{
	
	private static  PreparedStatement prepareStatement;
	private static ResultSet resultSet;
	private static final String SELECT_OWNER_QUERY="select * from owner";
	private static final String INSERT_OWNER_QUERY="insert into owner values(?,?,?)";
	private static final String INSERT_VEHICLE_QUERY="insert into vehicle values(?,?,?,?)";
	private static final String SELECT_VEHICLE_QUERY="select * from vehicle";
	private static final String SELECT_VEHICLE_OWNER_QUERY="select * from vehicle where owner_id=?";
	private static final String DELETE_VEHICLE_QUERY="delete from vehicle where vehicle_id=?";
	private static final String UPDATE_VEHICLE_QUERY="update vehicle set type=?,model=?,owner_id=? where vehicle_id=?";
	private static final String INSERT_RIDE_DATETIME_QUERY="insert into ride(ride_Id,date_time,vehicle_id,owner_id,status) values(?,?,?,?,?)";
	private static final String UPDATE_RIDE_PICKUPDROP_QUERY="update  ride set pickup_location=?,drop_location=? where ride_id=?";
	private static final String SELECT_RIDEID_QUERY="select ride_id from ride where ride_id=?";
	static Owner owner=null;
	static Vehicle vehicle=null;
	@Override
	public Owner ownerLogin(String ownerName,String password) {
		
		boolean ownerFlag=false;
		try(Connection conn=DatabaseConnection.getConnectionDb())
		{
			prepareStatement= conn.prepareStatement(SELECT_OWNER_QUERY);
			resultSet = prepareStatement.executeQuery();
			
			while(resultSet.next())
			{
				 if(resultSet.getString(2).equalsIgnoreCase(ownerName) && resultSet.getString(3).equals(password)  )
				{
					 ownerFlag=true;	
					 break;
				}
				
			}
			
			if(ownerFlag==true)
			{
				owner=new Owner(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3));
				System.out.println("\""+ownerName+" successfully logged in \"");
			}
			else
			{
				throw new InvalidLoginCrendentialException("you are  a invalid owner try to login with correct Crendentials");
			}
			
		}
		catch(InvalidLoginCrendentialException e)
		{
			System.out.println(e.getMessage());
		}
		catch(Exception e)
		{
			e.getMessage();
		}
		
	return owner;	
	
		
	}

	@Override
	public int OwnerRegister(Owner owner) {

		int count=0;
		boolean ownerFlag=false;
		try(Connection conn=DatabaseConnection.getConnectionDb();)
		{
			//To check whether user exist or not
			prepareStatement= conn.prepareStatement(SELECT_OWNER_QUERY);
			resultSet = prepareStatement.executeQuery();
			
			while(resultSet.next())
			{
				
				if(resultSet.getString(2).equalsIgnoreCase(owner.getownerName()) && resultSet.getString(3).equals(owner.getPassword()))
				{
					ownerFlag=true;
					break;
				}
				else
				{
					
					ownerFlag=false;
				}
			
			}
			if(ownerFlag==true)
			{
				throw new UserAlreadyExistException("Owner Already exit u can't register again please select login");

			}
			
			else
			{
				//if user not exist new user details inserted into database
				prepareStatement= conn.prepareStatement(INSERT_OWNER_QUERY);
				prepareStatement.setString(1, owner.getownerId());
				prepareStatement.setString(2, owner.getownerName());
				prepareStatement.setString(3, owner.getPassword());
				count=prepareStatement.executeUpdate();
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
	public List<Vehicle> getAllVehicles(String ownerId) {
		
		//To get List of vehicles
		List<Vehicle> list=new ArrayList<>();
		
		try(Connection conn=DatabaseConnection.getConnectionDb();)
		{
			//To check whether vehicle exist or not
			prepareStatement= conn.prepareStatement(SELECT_VEHICLE_OWNER_QUERY);
			prepareStatement.setString(1, ownerId);
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next())
			{
				
				if(resultSet.getString(4).equals(ownerId) )
				{
					vehicle=new Vehicle();
					vehicle.setVehicleId(resultSet.getString(1));
					vehicle.setType(resultSet.getString(2));
					vehicle.setModel(resultSet.getString(3));
					vehicle.setOwnerID(resultSet.getString(4));
					list.add(vehicle);
				}
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		return list;
	}
	@Override
	public int insertVehicle(Vehicle vehicle) {
		int count=0;
		boolean searchFlag=false;
		try(Connection conn=DatabaseConnection.getConnectionDb();)
		{
			//To check whether vehicle exist or not
			prepareStatement= conn.prepareStatement(SELECT_VEHICLE_QUERY);
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next())
			{
				if(resultSet.getString(1).equals(vehicle.getVehicleId()) && resultSet.getString(4).equals(vehicle.getOwnerID()))
				{
					searchFlag=true;
					break;
				}
			}
			if(searchFlag==false)
			{
				//if vehicle doesn't exist insert into database
				prepareStatement= conn.prepareStatement(INSERT_VEHICLE_QUERY);
				prepareStatement.setString(1, vehicle.getVehicleId());
				prepareStatement.setString(2, vehicle.getType());
				prepareStatement.setString(3, vehicle.getModel());
				prepareStatement.setString(4, vehicle.getOwnerID());
				count=prepareStatement.executeUpdate();
			}
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return count;
		
	}

	@Override
	public int deleteVehicle(String vehicleId) {
		int count=0;
		boolean searchFlag=false;
		try(Connection conn=DatabaseConnection.getConnectionDb();)
		{
			//To check whether vehicle exist or not
			searchFlag=checkVehicleById(vehicleId);
			if(searchFlag==true)
			{
				prepareStatement= conn.prepareStatement(DELETE_VEHICLE_QUERY);
				prepareStatement.setString(1, vehicleId);
				count=prepareStatement.executeUpdate();
			}
		
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return count;
	}

	@Override
	public boolean checkVehicleById(String vehicleId) {
		boolean searchFlag=false;
		try(Connection conn=DatabaseConnection.getConnectionDb();)
		{
			//To check whether vehicle exist or not
			prepareStatement= conn.prepareStatement(SELECT_VEHICLE_QUERY);
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next())
			{
				if(resultSet.getString(1).equals(vehicleId) )
				{
					searchFlag=true;
					break;
				}
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return searchFlag;
	}

	@Override
	public int updateVehicleDetails(Vehicle vehicle) {
		// TO update vehicle details
		int count=0;
		try(Connection conn=DatabaseConnection.getConnectionDb();)
		{
			//To check whether vehicle exist or not
			prepareStatement= conn.prepareStatement(UPDATE_VEHICLE_QUERY);
			prepareStatement.setString(1, vehicle.getType());
			prepareStatement.setString(2, vehicle.getModel());
			prepareStatement.setString(3, vehicle.getOwnerID());
			prepareStatement.setString(4, vehicle.getVehicleId());
			count=prepareStatement.executeUpdate();
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return count;
	}

	@Override
	public int addRideDateTime(Ride ride) {
		int count=0;
		boolean searchFlag=false;
		//To search whether the ride exist or not
		try(Connection conn=DatabaseConnection.getConnectionDb();)
		{
			prepareStatement= conn.prepareStatement(SELECT_RIDEID_QUERY);
			prepareStatement.setString(1, ride.getRideId());
			resultSet=prepareStatement.executeQuery();
			while(resultSet.next())
			{
				if(resultSet.getString(1).equalsIgnoreCase(ride.getRideId()))
				{
					searchFlag=true;
					break;
				}
			}
			if(searchFlag==false)
			{
				//To add ride based on dateTime 
				prepareStatement= conn.prepareStatement(INSERT_RIDE_DATETIME_QUERY);
				prepareStatement.setString(1, ride.getRideId());
				prepareStatement.setTimestamp(2, DateTimeConversion(ride.getDateTime()));
				prepareStatement.setString(3,ride.getVehicleId());
				prepareStatement.setString(4,ride.getOwnerId());
				prepareStatement.setString(5, "available");
				count=prepareStatement.executeUpdate();		
			}
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
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
	public int addRidePickDrop(Ride ride) {
		int count=0;
		boolean searchFlag=false;
		//To search whether the ride exist or not
		try(Connection conn=DatabaseConnection.getConnectionDb();)
		{
			prepareStatement= conn.prepareStatement(SELECT_RIDEID_QUERY);
			prepareStatement.setString(1, ride.getRideId());
			resultSet=prepareStatement.executeQuery();
			while(resultSet.next())
			{
				if(resultSet.getString(1).equalsIgnoreCase(ride.getRideId()))
				{
					searchFlag=true;
					break;
				}
			}
			if(searchFlag==true)
			{
				prepareStatement= conn.prepareStatement(UPDATE_RIDE_PICKUPDROP_QUERY);
				prepareStatement.setString(1, ride.getPickupLocation());
				prepareStatement.setString(2, ride.getDropLocation());
				prepareStatement.setString(3,ride.getRideId());	
				count=prepareStatement.executeUpdate();	
			}
			
		}
		catch(Exception e)
		{
			//System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return count;
	}
	

	
	
}
