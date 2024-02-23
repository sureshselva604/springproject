package com.hotel.booking.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="room_facilities")
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class roomFacilities {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	@Column(name="roomfac_id")
	private Long roomfacId;
	
	public Long getRoomfacId() {
		return roomfacId;
	}

	public void setRoomfacId(Long roomfacId) {
		this.roomfacId = roomfacId;
	}

	public boolean isRefrigerator() {
		return refrigerator;
	}

	public void setRefrigerator(boolean refrigerator) {
		this.refrigerator = refrigerator;
	}

	public boolean isAirConditioning() {
		return airConditioning;
	}

	public void setAirConditioning(boolean airConditioning) {
		this.airConditioning = airConditioning;
	}

	public boolean iswashingMachine() {
		return washingMachine;
	}

	public void setwashingMachine(boolean washingMachine) {
		this.washingMachine = washingMachine;
	}

	public boolean isKitchen() {
		return kitchen;
	}

	
	public void setKitchen(boolean kitchen) {
		this.kitchen = kitchen;
	}

	public boolean isExtraBeds() {
		return extraBeds;
	}

	public void setExtraBeds(boolean extraBeds) {
		this.extraBeds = extraBeds;
	}

	public boolean isBalcony() {
		return balcony;
	}

	public void setBalcony(boolean balcony) {
		this.balcony = balcony;
	}

	public Room getRoomId() {
		return roomId;
	}

	public void setRoomId(Room roomId) {
		this.roomId = roomId;
	}

	@Column(name="refrigerator")
	private boolean refrigerator;
	
	@Column(name="air_conditioning")
	private boolean airConditioning;
	
	@Column(name="washing_machine")
	private boolean washingMachine;
	
	@Column(name="kitchen")
	private boolean kitchen;
	
	@Column(name="extra_Beds")
	private boolean extraBeds;
	
	@Column(name="balcony")
	private boolean balcony;
	
	@OneToOne
	@JoinColumn(name="room_id")
	private Room roomId;

}
