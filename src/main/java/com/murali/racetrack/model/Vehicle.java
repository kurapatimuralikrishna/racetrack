package com.murali.racetrack.model;

public class Vehicle {
	private String vehicleType;
	private String vehicleNumber;
	private int hashCode;
	public Vehicle(String vehicleType, String vehicleNumber) {
		super();
		this.vehicleType = vehicleType;
		this.vehicleNumber = vehicleNumber;
		this.hashCode = vehicleNumber.hashCode();
	}
	public String getVehicleNumber() {
		return vehicleNumber;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	@Override
	public int hashCode() {
		return hashCode;
	}
	@Override
	public boolean equals(Object obj) {
		if(this==obj) return true;
		if(obj==null) return false;
		if(this.getClass().equals(obj.getClass())) {
			Vehicle v = (Vehicle) obj;
			if(this.vehicleNumber.equals(v.vehicleNumber)) return true;
		}
		return false;
	}
}
