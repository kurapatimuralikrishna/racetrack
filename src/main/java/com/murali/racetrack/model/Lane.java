package com.murali.racetrack.model;

import java.time.LocalTime;
import java.util.List;

public class Lane {

	private List<Booking> bookings;
	public class Booking{
		private String vehicleNo;
		private LocalTime entryTime;
		private LocalTime exitTime;
		public Booking(String vehicleNo, LocalTime entryTime, LocalTime exitTime) {
			super();
			this.vehicleNo = vehicleNo;
			this.entryTime = entryTime;
			this.exitTime = exitTime;
		}
	}
	public List<Booking> getBookings() {
		return bookings;
	}
	public boolean addBooking(String vehicleNo, LocalTime entryTime, LocalTime exitTime) {
		Booking request = new Booking(vehicleNo,entryTime,exitTime);
		for(Booking b:bookings) {
			int entry = b.entryTime.compareTo(request.exitTime);
			int exit = b.exitTime.compareTo(request.entryTime);
			if(entry>=0||exit<=0) continue;
			else return false;
		}
		return bookings.add(request);
	}
	public double revenue(int costPerHour) {
		int revenue=0;
		for(Booking booking:bookings) {
			LocalTime time = LocalTime.of(booking.exitTime.getHour(), booking.exitTime.getMinute());
			time = time.minusMinutes(booking.entryTime.getMinute());
			time = time.minusHours(booking.entryTime.getHour());
			double hours = time.getHour()+(time.getMinute()/60);
			int chargedHours = 3;
			if(hours>3.25)
				chargedHours = (int)hours+1;
			revenue += chargedHours*costPerHour;
		}
		return revenue;
	}
	public int changeBooking(String[] command) {
		for(Booking b:bookings) {
			if(b.vehicleNo.equals(command[1])) {
				int hours = Integer.parseInt(command[3].split(":")[0]);
				int minutes = Integer.parseInt(command[3].split(":")[1]);
				
				LocalTime newExitTime = LocalTime.of(hours, minutes);
				if(newExitTime.compareTo(b.exitTime)<0) return 2;
				for(Booking c:bookings) {
					int entry = c.entryTime.compareTo(newExitTime);
					if(entry>=0||c.vehicleNo.equals(b.vehicleNo)) continue;
					else return 3;
				}
				return 1;
			}
		}
		return 0;
	}
}
