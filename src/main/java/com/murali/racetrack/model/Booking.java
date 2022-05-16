package com.murali.racetrack.model;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Booking {
	private Vehicle vehicle;
	private LocalTime entryTime;
	private LocalTime exitTime;
	public Vehicle getVehicle() {
		return vehicle;
	}
	public LocalTime getExitTime() {
		return exitTime;
	}
	public void setExitTime(LocalTime exitTime) {
		this.exitTime = exitTime;
	}
	public Booking(Vehicle vehicle, LocalTime entryTime, LocalTime exitTime) {
		super();
		this.vehicle = vehicle;
		this.entryTime = entryTime;
		this.exitTime = exitTime;
	}
	public boolean clashesWith(Booking b) {
		int entry = this.entryTime.compareTo(b.getExitTime());
		int exit = this.getExitTime().compareTo(b.entryTime);
		if(entry>=0||exit<=0) return false;
		else return true;
	}
	public boolean canBeExtendedTo(Booking b, LocalTime newExitTime) {
		if(this.equals(b)) return true;
		LocalTime newEntryTime = this.getExitTime();
		Booking extention = new Booking(null, newEntryTime, newExitTime);
		return extention.clashesWith(b);
	}
	public long getChargedHours() {
		LocalTime totalTime = exitTime.minus(entryTime.toNanoOfDay(),ChronoUnit.NANOS);
		long chargedHours = 3;
		if(totalTime.isAfter(LocalTime.of(3, 15))) {
			chargedHours = totalTime.getHour();
			if((totalTime.getMinute()%60)>0) chargedHours++;
		}
		return chargedHours;
	}
}
