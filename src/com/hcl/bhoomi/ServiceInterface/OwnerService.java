package com.hcl.bhoomi.ServiceInterface;

import java.io.IOException;

import com.hcl.bhoomi.model.Owner;
import com.hcl.bhoomi.model.Ride;
import com.hcl.bhoomi.model.Vehicle;

public interface OwnerService {
	
	public void OwnerRegister(Owner owner) throws IOException;
	public void allVehicles(String ownerId);
	public void deleteVehicle(String vehicleId);
	public void updateVehicleDetails(Vehicle vehicle);
	public void addRideDateTime(Ride ride);
	public void addRidePickDrop(Ride ride);
	

}
