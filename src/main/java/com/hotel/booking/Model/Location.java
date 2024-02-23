package com.hotel.booking.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Table(name="location")
public class Location {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="location_id")
	private Long locationId;
	
	@Column(name="location_name")
	private String locationName;
	
	@Column(name="location_district")
	private String locationDistrict;
	
	@Column(name="location_state")
	private String locationState;
	
	@Column(name="location_country")
	private String locationCountry;
	
	@Column(name="pin_code")
	private Long pinCode;

	public Long getPinCode() {
		return pinCode;
	}

	public void setPinCode(Long pinCode) {
		this.pinCode = pinCode;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getLocationDistrict() {
		return locationDistrict;
	}

	public void setLocationDistrict(String locationDistrict) {
		this.locationDistrict = locationDistrict;
	}

	public String getLocationState() {
		return locationState;
	}

	public void setLocationState(String locationState) {
		this.locationState = locationState;
	}

	public String getCountry() {
		return locationCountry;
	}

	public void setCountry(String locationCountry) {
		this.locationCountry = locationCountry;
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

}
