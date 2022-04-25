package com.murali.racetrack.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.murali.racetrack.model.Lane.Booking;

public class RaceTrack {
	private String trackName;
	private TrackType trackType;
	private VehicleType vehicleType;
	private Lane[] lanes;
	private int costPerHour;
	
	public RaceTrack(String trackName, TrackType trackType, VehicleType vehicleType, int capacity, int costPerHour) {
		super();
		this.trackName = trackName;
		this.trackType = trackType;
		this.vehicleType = vehicleType;
		this.lanes = new Lane[capacity];
		this.costPerHour = costPerHour;
	}
	
	
	public String addBooking(Booking request){
		if(request.getEntryTime().isBefore(LocalTime.of(13, 0)) ||
				request.getEntryTime().isAfter(LocalTime.of(17, 0)))
			return ("INVALID_ENTRY_TIME");
		for(Lane lane:lanes) {
			if(lane.addBooking(request)) return ("SUCCESS");
		}
		return ("RACETRACK_FULL");
	}

	public int revenue() {
		int revenue=0;
		for(Lane lane:lanes) {
			revenue += lane.revenue(costPerHour);
		}
		return revenue;
	}
}
