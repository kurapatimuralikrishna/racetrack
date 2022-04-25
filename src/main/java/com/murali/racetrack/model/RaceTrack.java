package com.murali.racetrack.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RaceTrack {
	private String trackName;
	private TrackType trackType;
	private VehicleType vehicleType;
	private List<Lane> lanes;
	private int costPerHour;
	private List<Booking> bookings;
	public class Booking{
		private String vehicleNo;
		private LocalTime entryTime;
		private LocalTime exitTime;
		public Booking(String vehicleNo, LocalTime entryTime, LocalTime exitTime) {
			super();
			this.vehicleNo = vehicleNo;
			this.entryTime = entryTime;
			this.exitTime = exitTime;
		}
		public String getVehicleNo() {
			return vehicleNo;
		}
		public LocalTime getEntryTime() {
			return entryTime;
		}
		public LocalTime getExitTime() {
			return exitTime;
		}
		public void setVehicleNo(String vehicleNo) {
			this.vehicleNo = vehicleNo;
		}
		public void setEntryTime(LocalTime entryTime) {
			this.entryTime = entryTime;
		}
		public void setExitTime(LocalTime exitTime) {
			this.exitTime = exitTime;
		}
	}
	public RaceTrack(String trackName, TrackType trackType, VehicleType vehicleType, int capacity, int costPerHour) {
		super();
		this.trackName = trackName;
		this.trackType = trackType;
		this.vehicleType = vehicleType;
		this.capacity = capacity;
		this.costPerHour = costPerHour;
		this.bookings = new ArrayList<>();
	}
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
	
	public String addBooking(Booking request){
		if(request.entryTime.isBefore(LocalTime.of(13, 0)) ||
				request.entryTime.isAfter(LocalTime.of(17, 0)))
			return ("INVALID_ENTRY_TIME");
		for(Booking i:bookings) {
			if((i.entryTime.compareTo(request.exitTime)>=0) || (i.exitTime.compareTo(request.entryTime)<=0)) {
				continue;
			}
			return ("TRACK_NOT_AVAILABLE");
		}
		bookings.add(request);
		return ("SUCCESS");
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
