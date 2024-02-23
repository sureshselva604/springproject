package com.hotel.booking.Model;

import java.time.LocalDateTime;
import java.time.temporal.Temporal;

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
@Table(name="cancellation_details")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Cancellation {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	@Column(name="cancelllation_id")
	private Long cancelllationId;
	
	@Column(name="cancellation_date")
	private LocalDateTime cancellationDate;
	
	@Column(name="cancellation_charges")
	private Double cancellationCharges;
	
	@OneToOne
	@JoinColumn(name="booking_id")
	private Booking bookingId;

	public Long getCancelllationId() {
		return cancelllationId;
	}

	public void setCancelllationId(Long cancelllationId) {
		this.cancelllationId = cancelllationId;
	}

	public LocalDateTime getCancellationDate() {
		return cancellationDate;
	}

	public Temporal setCancellationDate(LocalDateTime cancellationDate) {
		return this.cancellationDate = cancellationDate;
	}

	public double getCancellationCharges() {
		return cancellationCharges;
	}

	public void setCancellationCharges(Double cancellationCharges) {
		this.cancellationCharges = cancellationCharges;
	}

	public Booking getBookingId() {
		return bookingId; 
	}

	public void setBookingId(Booking bookingId) {
		this.bookingId = bookingId;
	}
	
	

	
	

}
