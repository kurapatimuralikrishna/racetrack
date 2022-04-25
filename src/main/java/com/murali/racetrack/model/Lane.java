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
		public String getVehicleNo() {
			return vehicleNo;
		}
		public LocalTime getEntryTime() {
			return entryTime;
		}
		public LocalTime getExitTime() {
			return exitTime;
		}
		public void setVehicleNo(String vehicleNo) {
			this.vehicleNo = vehicleNo;
		}
		public void setEntryTime(LocalTime entryTime) {
			this.entryTime = entryTime;
		}
		public void setExitTime(LocalTime exitTime) {
			this.exitTime = exitTime;
		}
	}
	public List<Booking> getBookings() {
		return bookings;
	}
	public boolean addBooking(Booking request) {
		for(Booking b:bookings) {
			b.entryTime.compareTo(request)
		}
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
}
