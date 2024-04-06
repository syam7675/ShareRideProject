package com.hcl.bhoomi.ServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.hcl.bhoomi.DAO.OwnerDAO;
import com.hcl.bhoomi.DesignPatterns.OwnerFactory;
import com.hcl.bhoomi.Exceptions.AlreadyExistOrNotException;
import com.hcl.bhoomi.ServiceInterface.OwnerService;
import com.hcl.bhoomi.mainClasses.Main;
import com.hcl.bhoomi.model.Owner;
import com.hcl.bhoomi.model.Ride;
import com.hcl.bhoomi.model.Vehicle;

public class OwnerServiceImpl implements OwnerService {
	static OwnerDAO ownerDAO=OwnerFactory.getOwnerDao();
	int count=0;
	
	@Override
	public void OwnerRegister(Owner owner) throws IOException {
		//To register the owner
		count=ownerDAO.OwnerRegister(owner);
		if(count>0)
		{
			System.out.println("\""+owner.getownerName()+" successfully registered \"");
			Main.mainOptions();
		}
	}
	@Override
	public void allVehicles(String ownerId) {
		// To get all the vehicles details
		List<Vehicle> list=new ArrayList<>();
		list=ownerDAO.getAllVehicles(ownerId);
		if(list.isEmpty())
		{
			System.out.println("You don't have any vehicles");
		}
		else
		{
			System.out.println("Your Vehicles are.........");
			list.forEach(a->System.out.println(a));
		}
		
	}
	@Override
	public void deleteVehicle(String vehicleId) {
		// To delete the vehicle
		
		try
		{
			count=ownerDAO.deleteVehicle(vehicleId);
			if(count>0)
			{
				System.out.println(vehicleId+" Successfully deleted");
			}
			else
			{
				throw new AlreadyExistOrNotException(vehicleId+" is not exist so can't delete");
			}
		}
		catch(AlreadyExistOrNotException e)
		{
			System.out.println(e.getMessage());
		}
	}
	@Override
	public void updateVehicleDetails(Vehicle vehicle) {
		// To update the details of vehicle
		
		count=ownerDAO.updateVehicleDetails(vehicle);
		if(count>0)
		{
			System.out.println(vehicle.getVehicleId()+" details successfully updated");
		}
	}
	@Override
	public void addRideDateTime(Ride ride) {
		// TODO Auto-generated method stub
		try
		{
			count=ownerDAO.addRideDateTime(ride);
			if(count>0)
			{
				System.out.println(ride.getRideId()+" successfully details added ");
			}
			else
			{
				throw new AlreadyExistOrNotException(ride.getRideId()+" already exist so can't add");
			}
		}
		catch(AlreadyExistOrNotException e)
		{
			System.out.println(e.getMessage());
		}
	}
	@Override
	public void addRidePickDrop(Ride ride) {
		// TODO Auto-generated method stub
		try
		{
			count=ownerDAO.addRidePickDrop(ride);
			if(count>0)
			{
				System.out.println(ride.getRideId()+" successfully details added ");

			}
			else
			{
				throw new AlreadyExistOrNotException(ride.getRideId()+" doesn't exist so can't add");

			}
		}
		catch(AlreadyExistOrNotException e)
		{
			System.out.println(e.getMessage());
		}
		
		
	}
	


}
