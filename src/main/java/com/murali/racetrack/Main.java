package com.murali.racetrack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.murali.racetrack.model.Command;
import com.murali.racetrack.model.RaceTrack;

public class Main {
	public static void main(String[] args)  {
    	Scanner sc = new Scanner(System.in);
    	List<String[]> commands = new ArrayList<>();
    	Map<String,RaceTrack> tracks = new HashMap<>();
    	RaceTrack track = new RaceTrack(null, null, null, 0, 0, null);
    	tracks.put("REGULAR_BIKE", null);
    	tracks.put("REGULAR_CAR", null);
    	tracks.put("REGULAR_SUV", null);
    	tracks.put("", null);
    	tracks.put("REGULAR_BIKE", null);
    	while(sc.hasNextLine()) {
    		String[] command = sc.nextLine().split(" ");
    		commands.add(command);
    	}
    	for(String[] command:commands) {
    		Command cd = Command.valueOf(command[0]);
    		switch(cd) {
    		case BOOK:
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
