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
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Entity
@Table(name="hotel_facilities")
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Data
public class HotelFacilities {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	@Column(name="hotelfac_Id")
	private Long hotelfacId;
	
	@Column(name="free_car_parking")
	private boolean freeCarParking;
	
	public Long getHotelfacId() {
		return hotelfacId;
	}

	public void setHotelfacId(Long hotelfacId) {
		this.hotelfacId = hotelfacId;
	}

	public boolean isFreeCarParking() {
		return freeCarParking;
	}

	public void setFreeCarParking(boolean freeCarParking) {
		this.freeCarParking = freeCarParking;
	}

	public boolean isRestranaunt() {
		return restranaunt;
	}

	public void setRestranaunt(boolean restranaunt) {
		this.restranaunt = restranaunt;
	}

	public boolean isFreeWifi() {
		return freeWifi;
	}

	public void setFreeWifi(boolean freeWifi) {
		this.freeWifi = freeWifi;
	}

	public boolean isCctv() {
		return cctv;
	}

	public void setCctv(boolean cctv) {
		this.cctv = cctv;
	}

	public boolean isSwimingPool() {
		return swimingPool;
	}

	public void setSwimingPool(boolean swimingPool) {
		this.swimingPool = swimingPool;
	}

	public boolean isLaundaryService() {
		return laundaryService;
	}

	public void setLaundaryService(boolean laundaryService) {
		this.laundaryService = laundaryService;
	}

	public boolean isFitnessAllowed() {
		return fitnessAllowed;
	}

	public void setFitnessAllowed(boolean fitnessAllowed) {
		this.fitnessAllowed = fitnessAllowed;
	}

	public boolean isPetsAllowed() {
		return petsAllowed;
	}

	public void setPetsAllowed(boolean petsAllowed) {
		this.petsAllowed = petsAllowed;
	}

	public Hotels getHotelId() {
		return hotelId;
	}

	public void setHotelId(Hotels hotelId) {
		this.hotelId = hotelId;
	}

	@Column(name="restranaunt")
	private boolean restranaunt;
	
	@Column(name="free_wifi")
	private boolean freeWifi;
	
	@Column(name="cctv")
	private boolean cctv;
	
	@Column(name="swiming_pool")
	private boolean swimingPool;
	
	@Column(name="laundary_service")
	private boolean laundaryService;
	
	@Column(name="fitness_allowed")
	private boolean fitnessAllowed;
	
	@Column(name="pets_allowed")
	private boolean petsAllowed;
	
	@OneToOne
	@JoinColumn(name="hotel_id")
	private Hotels hotelId;
	

}
