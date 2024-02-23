package com.hotel.booking.Model;
import java.time.LocalDateTime;
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
@Table(name="booking")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Booking {
	
	public Long getBookingId() {
		return bookingId;
	}


	public void setBookingId(Long bookingId) {
		this.bookingId =  bookingId;
	}


	public LocalDateTime getCheckInDate() {
		return checkInDate;
	}


	public void setCheckInDate(LocalDateTime checkInDate) {
		this.checkInDate = checkInDate;
	}


	public LocalDateTime getCheckOutDate() {
		return checkOutDate;
	}


	public void setCheckOutDate(LocalDateTime checkOutDate) {
		this.checkOutDate = checkOutDate;
	}


	public Integer getNoOfRooms() {
		return noOfRooms;
	}


	public void setNoOfRooms(Integer noOfRooms) {
		this.noOfRooms = noOfRooms;
	}


	public Integer getNoOfAdults() {
		return noOfAdults;
	}


	public void setNoOfAdults(Integer noOfAdults) {
		this.noOfAdults = noOfAdults;
	}


	public Integer getNoOfChildrens() {
		return noOfChildrens;
	}


	public void setNoOfChildrens(Integer noOfChildrens) {
		this.noOfChildrens = noOfChildrens;
	}


	public LocalDateTime getBookingDate() {
		return bookingDate;
	}


	public void setBookingDate(LocalDateTime bookingDate) {
		this.bookingDate = bookingDate;
	}


	public User getUserId() {
		return userId;
	}


	public void setUserId(User userId) {
		this.userId = userId;
	}


	public Location getLocationId() {
		return locationId;
	}


	public void setLocationId(Location locationId) {
		this.locationId = locationId;
	}

	@Id 
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	@Column(name="booking_id")
	private Long bookingId;
	
	@Column(name="check_in_date")
	private LocalDateTime checkInDate;
	
	@Column(name="check_out_date")
	private LocalDateTime checkOutDate;
	
	@Column(name="no_of_rooms")
	private Integer noOfRooms;
	
	@Column(name="no_of_adults")
	private Integer noOfAdults;
	
	@Column(name="no_of_children")
	private Integer noOfChildrens;
	
	@Column(name="booking_date")
	private LocalDateTime bookingDate;

	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User userId;
	
	
	@ManyToOne
	@JoinColumn(name="location_id")
	private Location locationId;


}
