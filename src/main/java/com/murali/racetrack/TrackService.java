package com.murali.racetrack;

import java.time.LocalTime;

import com.murali.racetrack.model.Booking;
import com.murali.racetrack.model.Track;
import com.murali.racetrack.model.Vehicle;

public class TrackService {
	private static final LocalTime MAX_ENTRY = LocalTime.of(17, 0);
	private static final LocalTime MIN_ENTRY = LocalTime.of(13, 0);
	public static void book(String[] command) {
		String vehicleType = command[1];
		String VehicleNumber = command[2];
		Vehicle vehicle = new Vehicle(vehicleType,VehicleNumber);
		
		LocalTime entryTime = TrackService.getTime(command[3]);
		LocalTime exitTime = entryTime.plusHours(3);
		Booking newBooking = new Booking(vehicle,entryTime,exitTime);
		
		if(entryTime.isBefore(MIN_ENTRY) || entryTime.isAfter(MAX_ENTRY)) {
			System.out.println("INVALID_ENTRY_TIME");
			return;
		}
		
		boolean bookedRegular = TrackService.bookRegular(vehicleType,newBooking);
		if(bookedRegular) System.out.println("SUCCESS");
		else if (vehicleType.equals("BIKE")) System.out.println("RACETRACK_FULL");
		else System.out.println(bookVip(vehicleType,newBooking));
	}
	private static String bookVip(String vehicleType, Booking newBooking) {
		String trackType = "VIP";
		Track track = getTrack(trackType,vehicleType);
		if(track.addBooking(newBooking)) return "SUCCESS";
		else return "RACETRACK_FULL";
	}
	private static boolean bookRegular(String vehicleType, Booking newBooking) {
		String trackType = "REGULAR";
		Track track = getTrack(trackType,vehicleType);
		return track.addBooking(newBooking);
	}
	private static Track getTrack(String trackType, String vehicleType) {
		for(Track track:Main.tracks) {
			boolean isTrackType=track.getTrackType().equals(trackType);
			boolean isVehicleType=track.getVehicleType().equals(vehicleType);
			if(isTrackType&&isVehicleType) return track;
		}
		return null;
	}
	public static void changeBooking(String[] command) {
		LocalTime newExitTime = getTime(command[2]);
		if(newExitTime.isAfter(LocalTime.of(20, 0))) {
			System.out.println("INVALID_EXIT_TIME");
			return;
		}
		String result = "continue";
		for(Track track:Main.tracks) {
			result = track.changeBooking(command[1],newExitTime);
			if(result.equals("continue")) continue;
			System.out.println(result);
			return;
		}
	}
	public static void showRevenue() {
		long regularRevenue = 0;
		long vipRevenue = 0;
		for(Track track:Main.tracks) {
			if(track.getTrackType().equals("REGULAR")) regularRevenue += track.getRevenue();
			else vipRevenue += track.getRevenue();
		}
		System.out.println(regularRevenue+" "+vipRevenue);
	}
	private static LocalTime getTime(String time) {
		String[] splitTime = time.split(":");
		LocalTime entryTime = LocalTime.of(Integer.parseInt(splitTime[0]),Integer.parseInt(splitTime[1]));
		return entryTime;
	}
}