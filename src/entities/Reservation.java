package entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import Exception.ReservationException;

public class Reservation {
	
	public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	private Integer roomNumber;
	private Date checkin;
	private Date checkout;
	
	public Reservation() {
	}

	public Reservation(Integer roomNumber, Date checkin, Date checkout) {
		this.roomNumber = roomNumber;
		this.checkin = checkin;
		this.checkout = checkout;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Date getCheckin() {
		return checkin;
	}


	public Date getCheckout() {
		return checkout;
	}
	
	public long duration() {
		long diff = checkout.getTime() - checkin.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}

	public void updateDates(Date checkin, Date checkout) throws ReservationException{
		if (!checkout.after(checkin)) {
			throw new ReservationException("Error in reservation: Check-out date must be after Check-in date");
		}
		Date now = new Date();
		if (checkin.before(now) || checkout.before(now)) {
			throw new ReservationException("Error in reservation: Reservation date for uptade must be future dates");
		}
		this.checkin = checkin;
		this.checkout = checkout;
	}
	
	@Override
	public String toString() {
		return "Reservation: Room " 
				+ roomNumber 
				+ ", check-in: " 
				+ sdf.format(checkin) 
				+ ", check-out: " 
				+ sdf.format(checkout) 
				+ ", " 
				+ duration() 
				+ " nights";
	}
}
