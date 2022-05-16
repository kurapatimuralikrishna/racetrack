package com.murali.racetrack.model;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class Track {
	private String trackType;
	private String vehicleType;
	private int capacity;
	private int cost;
	private Set<Booking> bookings;
	
	public Track(String trackType, String vehicleType, int capacity, int cost) {
		super();
		this.trackType = trackType;
		this.vehicleType = vehicleType;
		this.capacity = capacity;
		this.cost = cost;
		this.bookings = new HashSet<>();
	}
	
	public String getTrackType() {
		return trackType;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	
	public boolean addBooking(Booking newBooking) {
		int count = 0;
		for(Booking booking:bookings) {
			if(newBooking.clashesWith(booking)) {
				count++;
				if(count==capacity) return false;
			}
		}
		return bookings.add(newBooking);
	}
	private Booking getBooking(String vehicleNumber ) {
		for (Booking booking : bookings) {
			if (booking.getVehicle().getVehicleNumber().equals(vehicleNumber))
				return booking;
		}
		return null;
	}
	public String changeBooking(String vehicleNumber, LocalTime newExitTime) {
		
		Booking booking = null;
		int count = 0;
		
		booking = getBooking(vehicleNumber);
		if(booking==null) return "continue";
		
		if(booking.getExitTime().isAfter(newExitTime)) return "INVALID_EXIT_TIME";
		for(Booking b:bookings) {
			boolean canBeExtended = booking.canBeExtendedTo(b, newExitTime);
			if(canBeExtended) continue;
			else count++;
		}
		if(count<capacity) {
			booking.setExitTime(newExitTime);
			return "SUCCESS";
		}
		else return "RACETRACK_FULL";
	}
	public long getRevenue() {
		long revenue = 0;
		for(Booking b:bookings) {
			revenue+=b.getChargedHours()*cost;
		}
		return revenue;
	}
}
