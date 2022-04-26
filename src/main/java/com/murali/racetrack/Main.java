package com.murali.racetrack;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.murali.racetrack.controller.BookingsController;
import com.murali.racetrack.model.RaceTrack;

public class Main {
	public static void main(String[] args)  {
		String result;
		try {
			System.setIn(new FileInputStream("./sample_input/input1.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    	Scanner sc = new Scanner(System.in);
    	List<String[]> commands = new ArrayList<>();
    	
    	Map<String,RaceTrack> tracks = new HashMap<>();
    	tracks.put("REGULAR_BIKE", new RaceTrack(4, 60));
    	tracks.put("REGULAR_CAR", new RaceTrack(2,120));
    	tracks.put("REGULAR_SUV", new RaceTrack(2,200));
    	tracks.put("VIP_CAR", new RaceTrack(1,250));
    	tracks.put("VIP_SUV", new RaceTrack(1,300));
    	
    	while(sc.hasNext()) {
    		String[] command = sc.nextLine().split(" ");
    		commands.add(command);
    	}
    	for(String[] command:commands) {
    		String cd = command[0];
    		switch(cd) {
    		case "BOOK":
    			result = BookingsController.book(command,tracks);
    			System.out.println(result);
    			break;
    		case "ADDITIONAL":
    			result = BookingsController.changeBooking(command,tracks);
    			System.out.println(result);
    			break;
    		case "REVENUE":
    			int[] revenue= {0,0};
    			revenue[0]+=tracks.get("REGULAR_BIKE").revenue();
    			revenue[0]+=tracks.get("REGULAR_CAR").revenue();
    			revenue[0]+=tracks.get("REGULAR_SUV").revenue();
    			revenue[1]+=tracks.get("VIP_CAR").revenue();
    			revenue[1]+=tracks.get("VIP_SUV").revenue();
    			System.out.println(revenue[0]+" "+revenue[1]);
    			break;
    		}
    	sc.close();	
    	}
	}
}
