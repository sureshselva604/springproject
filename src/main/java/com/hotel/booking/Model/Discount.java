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
@Table(name="discount")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Discount {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	@Column(name="discount_id")
	private Long discountId;
	
	@Column(name="discount_details")
	private String discountDetails;
	
	public String getDiscountDetails() {
		return discountDetails;
	}

	public void setDiscountDetails(String discountDetails) {
		this.discountDetails = discountDetails;
	}

	@Column(name="discount_percentage")
	private Double discountPercentage;

	public Double getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(Double discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public Long getDiscountId() {
		return discountId;
	}

	public void setDiscountId(Long discountId) {
		this.discountId = discountId;
	}

	
	
}
