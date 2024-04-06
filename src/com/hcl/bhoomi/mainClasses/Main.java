package com.hcl.bhoomi.mainClasses;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

import com.hcl.bhoomi.DAO.OwnerDAO;
import com.hcl.bhoomi.DAO.UsersDAO;
import com.hcl.bhoomi.DesignPatterns.OwnerFactory;
import com.hcl.bhoomi.DesignPatterns.UsersFactory;
import com.hcl.bhoomi.Exceptions.AlreadyExistOrNotException;
import com.hcl.bhoomi.Exceptions.InputInvalidException;
import com.hcl.bhoomi.ServiceInterface.OwnerService;
import com.hcl.bhoomi.ServiceInterface.UsersService;
import com.hcl.bhoomi.Threads.OwnerThread;
import com.hcl.bhoomi.Threads.UsersThread;
import com.hcl.bhoomi.model.Owner;
import com.hcl.bhoomi.model.Ride;
import com.hcl.bhoomi.model.Users;
import com.hcl.bhoomi.model.Vehicle;

public class Main {

	//Scanner class is used to read Number values
	static Scanner sc=new Scanner(System.in);
	//BufferedReader class is used to read Strings
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static String userId,userName,password,ownerId,ownerName;
	static OwnerDAO ownerDAO=OwnerFactory.getOwnerDao();
	static OwnerService ownerService=OwnerFactory.getOwnerService();
	static UsersService usersService=UsersFactory.getUserService();
	static UsersDAO usersDAO=UsersFactory.getUserDAO();
	static String vehicleId,type,model,rideId,dateTime,pickup,drop;
	static Vehicle vehicle;
	static Ride ride;
	static Owner owner;
	static Users user;
	static int count=0;
	static boolean flag;
	
	
	public static void main(String[] args) throws IOException {
		
		mainOptions();

	}
	
	public static void mainOptions() throws IOException
	{
		int choice;
		System.out.println("****Welcome to Share Ride App****");
		while(true)
		{
			//first user has to select their role
			System.out.print("Choose your Role:\n1.Owner\n2.User\n3.Exit\nEnter your choice:");
			choice=sc.nextInt();
			switch(choice)
			{
			case 1:
				ownerOptions();
				break;
			case 2:
				userOptions();
				break;
			case 3:
				System.out.println("***Exiting from Application****");
				System.out.println("***Thank you****");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid choice");
			}
		}
	}
	public static void ownerOptions() throws IOException
	{
		
		int option;
		System.out.println("****Welcome to Owners Page****");
		while(true)
		{
			//if user is owner then he has to select from below menu
			System.out.print("Menu:\n1.Login\n2.Register\n3.Show users\n4.Logout\nEnter your choice:");
			option=sc.nextInt();
			switch(option)
			{
			case 1:
				//if owner selects login he has to enter username and password
				System.out.println("Enter ownerName:");
				ownerName=br.readLine();
				System.out.println("Enter password:");
				password=br.readLine();
				//it returns true if login successfully otherwise it returns false
				owner=ownerDAO.ownerLogin(ownerName, password);
				OwnerThread ownerThread=new OwnerThread(owner);
				ownerThread.start();
					try
					{
						ownerThread.join();
					}
					catch(Exception e)
					{
						System.out.println(e.getMessage());
					}
				
				break;
			case 2:
				//if admin slects register he has to enter his details
				System.out.println("Enter ownerId:");
				ownerId=br.readLine();
				System.out.println("Enter ownerName:");
				ownerName=br.readLine();
				System.out.println("Enter password:");
				password=br.readLine();
				ownerService.OwnerRegister(new Owner(ownerId,ownerName,password));
				break;
			case 3:
				break;
			case 4:
				System.out.println("***Thank You visit again***");
				mainOptions();
				break;
			default:
				System.out.println("Invalid choice");
			}
		}
		
		
	}
	public static void userOptions() throws IOException
	{
		int option;
		System.out.println("****Welcome to User Page****");
		while(true)
		{
			//if user is normal user then he has to select from below menu
			System.out.print("Menu:\n1.Login\n2.Register\n3.Logout\nEnter your choice:");
			option=sc.nextInt();
			switch(option)
			{
			case 1:
				//if user slects login he has to enter username and password
				System.out.println("Enter username:");
				userName=br.readLine();
				System.out.println("Enter password:");
				password=br.readLine();
				//it returns true if login successfully otherwise it returns false
				user=usersDAO.usersLogin(userName, password);
				UsersThread userThread=new UsersThread(user);
				userThread.start();
				try
				{
					userThread.join();
				}
				catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
				break;
			case 2:
				//if user slects register he has to enter his details
				System.out.println("Enter userId:");
				userId=br.readLine();
				System.out.println("Enter username:");
				userName=br.readLine();
				System.out.println("Enter password:");
				password=br.readLine();
				usersService.usersRegister(new Users(userId,userName,password));
				break;
			case 3:
				System.out.println("***Thank You visit again***");
				mainOptions();
				break;
			default:
				System.out.println("Invalid choice");
			}
		}
	}
	public static void menuForOwner(Owner owner) throws IOException
	{
		System.out.println("****Welcome \""+owner.getownerName()+"\" !!! ****");
		int choice,count=0;
		boolean flag=false;
		while(true)
		{
			System.out.println("Menu:\n1.View My Vehicles\n2.Add a Vehicle\n3.Update Vehicle Details\n4.Delete a Vehicle\n5.Enter date and time of Ride\n6.Enter pickup and drop locations\n7.Exit\nEnter your choice:");
			choice=sc.nextInt();
			try
			{
				switch(choice)
				{
					case 1:
						ownerService.allVehicles(owner.getownerId());
						break;
					case 2:
						System.out.println("Enter vehicle details u want to add:\nEnter vehicleId:");
						vehicleId=br.readLine();
						System.out.println("Enter type(2-wheeler/4-wheeler):");
						type=br.readLine();
						if(type.equalsIgnoreCase("2-wheeler") || type.equalsIgnoreCase("4-wheeler"))
						{	
						}
						else
						{
							throw new InputInvalidException("type must be 2-wheeler/4-wheeler");
						}
						System.out.println("Enter model:");
						model=br.readLine();
						count=ownerDAO.insertVehicle(new Vehicle(vehicleId,type,model,owner.getownerId()));
						if(count>0)
						{
							System.out.println(vehicleId+"  Vehicle inserted Successfully");
						}
						else
						{
							throw new AlreadyExistOrNotException(vehicleId+" already exist so can't add");
						}
						break;
					case 3:
						System.out.println("Enter vehicle id u want to update:");
						vehicleId=br.readLine();
						flag=ownerDAO.checkVehicleById(vehicleId);
						if(flag==true)
						{
							System.out.println("Enter vehicle details u want to update:");
							System.out.println("Enter type(2-wheeler/4-wheeler):");
							type=br.readLine();
							if(type.equalsIgnoreCase("2-wheeler") || type.equalsIgnoreCase("4-wheeler"))
							{
								
							}
							else
							{
								throw new InputInvalidException("type must be 2-wheeler/4-wheeler");
							}
							System.out.println("Enter model:");
							model=br.readLine();
							ownerService.updateVehicleDetails(new Vehicle(vehicleId,type,model,owner.getownerId()));
						}
						else
						{
							throw new AlreadyExistOrNotException(vehicle.getVehicleId()+" not exist so can't update");
						}
						break;
					case 4:
						System.out.println("Enter vehicle id u want to delete:");
						vehicleId=br.readLine();
						ownerService.deleteVehicle(vehicleId);
						break;
					case 5:
						System.out.println("Enter ride id u want to add date and time:");
						rideId=br.readLine();
						System.out.println("Enter date and time(YYYY-MM-DD HH:MM:SS):");
						dateTime=br.readLine();
						System.out.println("Enter vehicle id u want to add ride:");
						vehicleId=br.readLine();
						ownerService.addRideDateTime(new Ride(rideId,dateTime,vehicleId,owner.getownerId()));
						break;
					case 6:
						System.out.println("Enter ride id u want add pickup and drop location:");
						rideId=br.readLine();
						System.out.println("Enter pickup location:");
						pickup=br.readLine();
						System.out.println("Enter drop location:");
						drop=br.readLine();
						ownerService.addRidePickDrop(new Ride(rideId,pickup,drop));
						break;
					case 7:
						System.out.println("****Logout Successfully \""+owner.getownerName()+"\" Thank You****");
						Main.mainOptions();
						break;
					default:
							throw new InputInvalidException("-------Invalid input------");
				
				}
		}
		catch(InputInvalidException e)
		{
			System.out.println(e.getMessage());
		}
		catch(AlreadyExistOrNotException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
}
	public static void menuForUsers(Users user) throws IOException
	{
		int choice;
		while(true)
		{
			System.out.println("Menu:\n1.To Enter details of ride\n2.Enter pickup and drop locations\n3.List of vehicles available\n4.Book the vehicle\n5.Cancel the Vehicle\n6.Exit\nEnter your choice: ");
			choice=sc.nextInt();
			try
			{
				switch(choice)
				{
				case 1:
					System.out.println("Enter date and time of the ride:");
					dateTime=br.readLine();
					System.out.println("Enter pickup location:");
					pickup=br.readLine();
					System.out.println("Enter drop location:");
					drop=br.readLine();
					usersService.addUserRideDetails(user.getUserId(),dateTime,pickup,drop);

					//usersService.addUserRideDateTime(new Ride(user.getUserId(),dateTime));
					break;
				case 2:
					System.out.println("Enter pickup location:");
					pickup=br.readLine();
					System.out.println("Enter drop location:");
					drop=br.readLine();
					usersService.addUserRidePickupDrop(user.getUserId(), pickup, drop);
					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
					break;
				case 6:
					System.out.println("****Logout Successfully \""+user.getUserName()+"\" Thank You****");
					Main.mainOptions();
					break;
				default:
					throw new InputInvalidException("-------Invalid input------"); 
					
				}
			}
			catch(InputInvalidException e)
			{
				System.out.println(e.getMessage());
			}
		}
	}

}
