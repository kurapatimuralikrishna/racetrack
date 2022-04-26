package com.murali.racetrack.controller;

import java.time.LocalTime;
import java.util.Map;

import com.murali.racetrack.model.RaceTrack;

public class BookingsController {
	public static String book(String[] command, Map<String,RaceTrack> tracks) {
		String result = bookRegular(command,tracks);
		boolean b1 = result.equals("RACETRACK_FULL");
		boolean b2 = command[1].equals("BIKE");
		if(b1&&!(b2))
			result = bookVip(command,tracks);
		return result;
	}

	private static String bookVip(String[] command, Map<String, RaceTrack> tracks) {
		String result;
		
		int hours = Integer.parseInt(command[3].split(":")[0]);
		int minutes = Integer.parseInt(command[3].split(":")[1]);
		
		LocalTime entryTime = LocalTime.of(hours, minutes);
		LocalTime exitTime = LocalTime.of(hours+3, minutes);
		
		switch(command[1]) {
		case "CAR":
			result = tracks.get("VIP_CAR").addBooking(command[2],entryTime,exitTime);
			return result;
		case "SUV":
			result = tracks.get("VIP_SUV").addBooking(command[2],entryTime,exitTime);
			return result;
		default:
			return null;
		}
	}

	private static String bookRegular(String[] command, Map<String, RaceTrack> tracks) {
		String result;
		
		int hours = Integer.parseInt(command[3].split(":")[0]);
		int minutes = Integer.parseInt(command[3].split(":")[1]);
		
		LocalTime entryTime = LocalTime.of(hours, minutes);
		LocalTime exitTime = LocalTime.of(hours+3, minutes);
		
		switch(command[1]) {
		case "BIKE":
			result = tracks.get("REGULAR_BIKE").addBooking(command[2],entryTime,exitTime);
			return result;
		case "CAR":
			result = tracks.get("REGULAR_CAR").addBooking(command[2],entryTime,exitTime);
			return result;
		case "SUV":
			result = tracks.get("REGULAR_SUV").addBooking(command[2],entryTime,exitTime);
			return result;
		default:
			return null;
		}
	}

	public static String changeBooking(String[] command, Map<String, RaceTrack> tracks) {
		
		String time = command[2];
		if(time.compareTo("20:00")>0) return "INVALID_EXIT_TIME";
		for(RaceTrack track:tracks.values()) {
			int result = track.changeBooking(command);
			if(result==2) return ("INVALID_EXIT_TIME");
			else if(result==3) return ("RACETRACK_FULL");
			else if(result==1) return ("SUCCESS");
		}
		return "RACETRACK_FULL";
	}
}
