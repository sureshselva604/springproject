package com.hotel.booking.Repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.booking.Model.Bill;
import com.hotel.booking.Model.Booking;
import com.hotel.booking.Repo.BookingRepo;

@Repository
public interface BillRepo extends JpaRepository<Bill, Long>{

	//List<Bill> findAllBybookingId(Booking bookingId);

	//List<Bill> findAllBybookingId(Booking booking);

	//List<Bill> findByBookingId(Booking bookingId);

	//List<Bill> findAllByBookingId(Booking bookingId);

	List<Bill> findByBookingId(Booking bookingId);

	

	
	



}
