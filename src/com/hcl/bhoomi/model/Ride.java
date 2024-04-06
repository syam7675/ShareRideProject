package com.hcl.bhoomi.model;


public class Ride {
	
	private String rideId,pickupLocation,dropLocation,vehicleId,userId,ownerId;
	String dateTime;
	
	public Ride()
	{
		
	}
	
	
	public Ride(String rideId, String dateTime, String vehicleId, String ownerId) {
		super();
		this.rideId = rideId;
		this.vehicleId = vehicleId;
		this.ownerId = ownerId;
		this.dateTime = dateTime;
	}
	

	public Ride(String rideId, String pickupLocation, String dropLocation) {
		super();
		this.rideId = rideId;
		this.pickupLocation = pickupLocation;
		this.dropLocation = dropLocation;
	}
	
	public Ride(String userId, String dateTime) {
		super();
		this.userId=userId;
		this.dateTime = dateTime;
	}

	
	public Ride(String rideId, String pickupLocation, String dropLocation, String vehicleId,
			String ownerId, String dateTime) {
		super();
		this.rideId = rideId;
		this.pickupLocation = pickupLocation;
		this.dropLocation = dropLocation;
		this.vehicleId = vehicleId;
		this.ownerId = ownerId;
		this.dateTime = dateTime;
	}

	public String getRideId() {
		return rideId;
	}

	public void setRideId(String rideId) {
		this.rideId = rideId;
	}

	public String getPickupLocation() {
		return pickupLocation;
	}

	public void setPickupLocation(String pickupLocation) {
		this.pickupLocation = pickupLocation;
	}

	public String getDropLocation() {
		return dropLocation;
	}

	public void setDropLocation(String dropLocation) {
		this.dropLocation = dropLocation;
	}

	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	

	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	@Override
	public String toString() {
		return "Ride [rideId=" + rideId + ", pickupLocation=" + pickupLocation + ", dropLocation=" + dropLocation
				+ ", vehicleId=" + vehicleId + ", ownerId=" + ownerId +  ", userId=" + userId +", dateTime=" + dateTime
				+ "]";
	}
	
	

	
	
	

}
