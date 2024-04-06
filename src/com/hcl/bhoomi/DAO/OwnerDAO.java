package com.hcl.bhoomi.DAO;

import java.util.List;

import com.hcl.bhoomi.model.Owner;
import com.hcl.bhoomi.model.Ride;
import com.hcl.bhoomi.model.Vehicle;


public interface OwnerDAO {
	
	public Owner ownerLogin(String userName,String password);
	public int OwnerRegister(Owner owner);
	public List<Vehicle> getAllVehicles(String ownerId);
	public int insertVehicle(Vehicle vehicle);
	public int deleteVehicle(String vehicleId);
	public int updateVehicleDetails(Vehicle vehicle);
	public boolean checkVehicleById(String vehicleId);
	public int addRideDateTime(Ride ride);
	public int addRidePickDrop(Ride ride);

}
