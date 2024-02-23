package com.hotel.booking.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.booking.Model.Bill;
import com.hotel.booking.Model.Booking;
import com.hotel.booking.Model.Cancellation;

@Repository
public interface CancellationRepo extends JpaRepository<Cancellation, Long> {

	Boolean existsByBookingId(Booking bookingId);

	
	List<Cancellation> findByBookingId(Booking booking);

	Cancellation findByBookingIdBookingId(Long bookingId);


	void save(Bill bill);


}
