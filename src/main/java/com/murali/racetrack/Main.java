package com.murali.racetrack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.murali.racetrack.model.Command;
import com.murali.racetrack.model.RaceTrack;
import com.murali.racetrack.model.TrackType;
import com.murali.racetrack.model.VehicleType;

public class Main {
	public static void main(String[] args)  {
    	Scanner sc = new Scanner(System.in);
    	List<String[]> commands = new ArrayList<>();
    	Map<String,RaceTrack> tracks = new HashMap<>();
    	RaceTrack track = new RaceTrack("REGULAR_BIKE", TrackType.REGULAR, VehicleType.BIKE, 4, 60);
    	tracks.put("REGULAR_BIKE", track);
    	track = new RaceTrack("REGULAR_CAR", TrackType.REGULAR, VehicleType.CAR, 2, 120);
    	tracks.put("REGULAR_CAR", track);
    	track = new RaceTrack("REGULAR_SUV", TrackType.REGULAR, VehicleType.SUV, 2, 200);
    	tracks.put("REGULAR_SUV", track);
    	track = new RaceTrack("VIP_CAR", TrackType.VIP, VehicleType.CAR, 1, 250);
    	tracks.put("VIP_CAR", track);
    	track = new RaceTrack("VIP_SUV", TrackType.VIP, VehicleType.SUV, 1, 300);
    	tracks.put("VIP_SUV", track);
    	while(sc.hasNextLine()) {
    		String[] command = sc.nextLine().split(" ");
    		commands.add(command);
    	}
    	for(String[] command:commands) {
    		Command cd = Command.valueOf(command[0]);
    		switch(cd) {
    		case BOOK:
    			BookingsController.book(command,tracks);
    			break;
    		case ADDITIONAL:
    			break;
    		case REVENUE:
    			break;
//    		default:
//    			System.out.println("INCORRECT COMMAND");
    		}
    	sc.close();	
    	}
	}
}
