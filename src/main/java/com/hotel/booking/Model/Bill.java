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
@Table(name="Bill_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Bill {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	@Column(name="bill_id")
	private Long billId;
	
	@Column(name="total_no_of_days")
	private Integer totalNoOfDays;
	
	@Column(name="total_no_of_peoples")
	private Integer totalNoOfPeoples;
	
	@Column(name="total_amount")
	private Double totalAmount;

	@Column(name="amount_to_be_paid")
	private Double amountToBePaid;
	
	public Long getBillId() {
		return billId;
	}

	public void setBillId(Long billId) {
		this.billId = billId;
	}

	public Integer getTotalNoOfDays() {
		return totalNoOfDays;
	}

	public void setTotalNoOfDays(Integer totalNoOfDays) {
		this.totalNoOfDays = totalNoOfDays;
	}

	public Integer getTotalNoOfPeoples() {
		return totalNoOfPeoples;
	}

	public void setTotalNoOfPeoples(Integer totalNoOfPeoples) {
		this.totalNoOfPeoples = totalNoOfPeoples;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Double getAmountToBePaid() {
		return amountToBePaid;
	}

	public void setAmountToBePaid(Double amountPaid) {
		this.amountToBePaid = amountPaid;
	}

	public Booking getBookingId() {
		return bookingId;
	}

	public void setBookingId(Booking bookingId) {
		this.bookingId = bookingId;
	}

	@OneToOne
	@JoinColumn(name="booking_id")
	private Booking bookingId;
	
}
