package com.murali.racetrack.model;

import java.time.LocalTime;

public class RaceTrack {
	private Lane[] lanes;
	private int costPerHour;
	
	public RaceTrack(int capacity, int costPerHour) {
		super();
		this.lanes = new Lane[capacity];
		for(int i=0;i<lanes.length;i++) {
			lanes[i] = new Lane();
		}
		this.costPerHour = costPerHour;
	}
	
	
	public Lane[] getLanes() {
		return lanes;
	}


	public String addBooking(String vehicleNo, LocalTime entryTime, LocalTime exitTime){
		if(entryTime.isBefore(LocalTime.of(13, 0)) ||
				entryTime.isAfter(LocalTime.of(17, 0)))
			return ("INVALID_ENTRY_TIME");
		for(Lane lane:lanes) {
			if(lane.addBooking(vehicleNo,entryTime,exitTime)) return ("SUCCESS");
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


	public int changeBooking(String[] command) {
		int result = 0;
		for(Lane lane:lanes) {
			result=lane.changeBooking(command);
			if(result!=0) return result;
		}
		return result;
	}
}
