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
@Table(name="rooms")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Room {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="room_id")
	private Long roomId;
	
	@Column(name="room_type")
	private String roomType;
	
	@Column(name="capacity")
	private Integer capacity;
	
	@Column(name="room_number")
	private Integer roomNumber;
	
	@Column(name="price_per_day")
	private Double pricePerDay;
	
	public Long getRoomId() {
		return roomId;
	}


	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}


	public String getRoomType() {
		return roomType;
	}


	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}


	public Integer getCapacity() {
		return capacity;
	}


	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}


	public Integer getRoomNumber() {
		return roomNumber;
	}


	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}


	public Double getPricePerDay() {
		return pricePerDay;
	}


	public void setPricePerDay(Double pricePerDay) {
		this.pricePerDay = pricePerDay;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}

	public Hotels getHotelId() {
		return hotelId;
	}


	public void setHotelId(Hotels hotelId) {
		this.hotelId = hotelId;
	}

	@Column(name="status")
	private String status;
	
	@ManyToOne
	@JoinColumn(name="hotel_id")
	private Hotels hotelId;

}
