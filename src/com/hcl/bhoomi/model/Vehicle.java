package com.hcl.bhoomi.model;

public class Vehicle {
	
	private String vehicleId,type,model,ownerID;
	
	public Vehicle()
	{
		
	}

	public Vehicle(String vehicleId, String type, String model, String ownerID) {
		super();
		this.vehicleId = vehicleId;
		this.type = type;
		this.model = model;
		this.ownerID = ownerID;
	}

	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getOwnerID() {
		return ownerID;
	}

	public void setOwnerID(String ownerID) {
		this.ownerID = ownerID;
	}

	@Override
	public String toString() {
		return "Vehicle [vehicleId=" + vehicleId + ", type=" + type + ", model=" + model + ", ownerID=" + ownerID + "]";
	}
	
	

}
