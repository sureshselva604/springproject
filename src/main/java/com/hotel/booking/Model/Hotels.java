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
@Table(name="hotel")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Hotels {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="hotel_id")
	private Long hotelId;
	
	@Column(name="hotel_name")
	private String hotelName;
	
	@Column(name="hotel_street")
	private String hotelStreet;
	@Column(name="hotel_city")
	private String hotelCity;
	@Column(name="hotel_state")
	private String hotelState;
	@Column(name="hotel_country")
	private String hotelCountry;
	@Column(name="hotel_type")
	private String hotelType;
	public Long getHotelId() {
		return hotelId;
	}
	public void setHotelId(Long hotelid) {
		this.hotelId = hotelid;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getHotelStreet() {
		return hotelStreet;
	}
	public void setHotelStreet(String hotelStreet) {
		this.hotelStreet = hotelStreet;
	}
	public String getHotelCity() {
		return hotelCity;
	}
	public void setHotelCity(String hotelCity) {
		this.hotelCity = hotelCity;
	}
	public String getHotelState() {
		return hotelState;
	}
	public void setHotelState(String hotelState) {
		this.hotelState = hotelState;
	}
	public String getHotelCountry() {
		return hotelCountry;
	}
	public void setHotelCountry(String hotelCountry) {
		this.hotelCountry = hotelCountry;
	}
	public String getHotelType() {
		return hotelType;
	}
	public void setHoteltype(String hoteltype) {
		this.hotelType = hoteltype;
	}
	public Double getRating() {
		return starRating;
	}
	public void setRating(Double starrating) {
		this.starRating = starrating;
	}
	public Location getLocationId() {
		return locationId;
	}
	public void setLocationId(Location locationId) {
		this.locationId = locationId;
	}
	@Column(name="star_rating")
	private Double starRating;
	
	@ManyToOne
	@JoinColumn(name="location_id")
	private Location locationId;
	
	public Double getStarRating() {
		return starRating;
	}
	public void setStarRating(Double starRating) {
		this.starRating = starRating;
	}
	public User getUserId() {
		return userId;
	}
	public void setUserId(User userId) {
		this.userId = userId;
	}
	public void setHotelType(String hotelType) {
		this.hotelType = hotelType;
	}
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User userId;

}
