package com.hotel.booking.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="roombooking")
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Data
public class RoomBooking {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	@Column(name="roombooking_Id")
	private Long roombookingId;
	
	@Column(name="active_status")
	private boolean activeStatus;
	
	@ManyToOne
	@JoinColumn(name="room_id")
	private Room roomId;
	
	@ManyToOne
	@JoinColumn(name="booking_id")
	private Booking bookingId;

	public Long getRoombookingId() {
		return roombookingId;
	}

	public void setRoombookingId(Long roombookingId) {
		this.roombookingId = roombookingId;
	}

	public boolean isActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(boolean activeStatus) {
		this.activeStatus = activeStatus;
	}

	public Room getRoomId() {
		return roomId;
	}

	public void setRoomId(Room roomId) {
		this.roomId = roomId;
	}

	public Booking getBookingId() {
		return bookingId;
	}

	public void setBookingId(Booking bookingId) {
		this.bookingId = bookingId;
	}

}
