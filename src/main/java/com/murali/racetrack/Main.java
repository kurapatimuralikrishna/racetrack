package com.murali.racetrack;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.murali.racetrack.model.Track;

public class Main {
	public static Set<Track> tracks;
	static {
		tracks = new HashSet<>();
		Track track;
		track = new Track("REGULAR","BIKE",4,60);
		tracks.add(track);
		track = new Track("REGULAR","CAR",2,120);
		tracks.add(track);
		track = new Track("REGULAR","SUV",2,200);
		tracks.add(track);
		track = new Track("VIP","CAR",1,250);
		tracks.add(track);
		track = new Track("VIP","SUV",1,300);
		tracks.add(track);
	}
	public static void main(String[] args) {
		
		InputStream ioStream = null;
		try {
			ioStream = new FileInputStream(args[0]);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Scanner sc = new Scanner(ioStream);
		List<String[]> commands = new ArrayList<>();
		while (sc.hasNext()) {
			String[] command = sc.nextLine().split(" ");
			commands.add(command);
		}
		
		for (String[] command : commands) {
			String cd = command[0];
			switch (cd) {
			case "BOOK":
				TrackService.book(command);
				break;
			case "ADDITIONAL":
				TrackService.changeBooking(command);
				break;
			case "REVENUE":
				TrackService.showRevenue();
				break;
			}
			sc.close();
		}
	}
}
