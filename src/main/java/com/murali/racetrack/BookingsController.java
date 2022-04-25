package com.murali.racetrack;

import java.time.LocalTime;
import java.util.Map;

import com.murali.racetrack.model.RaceTrack;
import com.murali.racetrack.model.VehicleType;

public class BookingsController {
	public static String book(String[] command, Map<String,RaceTrack> tracks) {
		String result = bookRegular(command,tracks);
		if(result.equals("TRACK_NOT_AVAILABLE"))
			result = bookVip(command,tracks);
		return result;
	}

	private static String bookVip(String[] command, Map<String, RaceTrack> tracks) {
		return null;
	}

	private static String bookRegular(String[] command, Map<String, RaceTrack> tracks) {
		VehicleType type = VehicleType.valueOf(command[1]);
		int hours = Integer.parseInt(command[3].split(":")[0]);
		int minutes = Integer.parseInt(command[3].split(":")[1]);
		
		LocalTime entryTime = LocalTime.of(hours, minutes);
		LocalTime exitTime = LocalTime.of(hours+3, minutes);
		
		RaceTrack.Booking request;
		String result;
		
		switch(type) {
		case BIKE:			
			request = tracks.get("REGULAR_BIKE").new Booking(command[2],entryTime,exitTime);
			result = tracks.get("REGULAR_BIKE").addBooking(request);
			return result;
		case CAR:
			request = tracks.get("REGULAR_CAR").new Booking(command[2],entryTime,exitTime);
			result = tracks.get("REGULAR_CAR").addBooking(request);
			return result;
		case SUV:
			request = tracks.get("REGULAR_SUV").new Booking(command[2],entryTime,exitTime);
			result = tracks.get("REGULAR_SUV").addBooking(request);
			return result;
		}
		return null;
	}
}
