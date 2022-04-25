package com.murali.racetrack.model;

import java.time.LocalTime;
import java.util.List;

public class RaceTrack {
	private String trackName;
	private TrackType trackType;
	private VehicleType vehicleType;
	private int capacity;
	private int costPerHour;
	public String getTrackName() {
		return trackName;
	}

	public TrackType getTrackType() {
		return trackType;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public int getCapacity() {
		return capacity;
	}

	public int getCostPerHour() {
		return costPerHour;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}

	public void setTrackType(TrackType trackType) {
		this.trackType = trackType;
	}

	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public void setCostPerHour(int costPerHour) {
		this.costPerHour = costPerHour;
	}

	private List<Booking> bookings;
	class Booking{
		private String vehicleNo;
		private LocalTime entryTime;
		private LocalTime exitTime;
	}
	public RaceTrack(String trackName, TrackType trackType, VehicleType vehicleType, int capacity, int costPerHour,
			List<Booking> bookings) {
		super();
		this.trackName = trackName;
		this.trackType = trackType;
		this.vehicleType = vehicleType;
		this.capacity = capacity;
		this.costPerHour = costPerHour;
		this.bookings = bookings;
	}

	public double revenue() {
		double revenue=0;
		for(Booking booking:bookings) {
			LocalTime time = LocalTime.of(booking.exitTime.getHour(), booking.exitTime.getMinute());
			time = time.minusMinutes(booking.entryTime.getMinute());
			time = time.minusHours(booking.entryTime.getHour());
			double hours = time.getHour()+(time.getMinute()/60);
			revenue += hours*costPerHour;
		}
		return revenue;
	}
}
