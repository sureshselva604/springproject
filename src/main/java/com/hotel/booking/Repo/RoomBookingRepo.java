package com.hotel.booking.Repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.booking.Model.Bill;
import com.hotel.booking.Model.Booking;
import com.hotel.booking.Model.RoomBooking;

@Repository
public interface RoomBookingRepo extends JpaRepository<RoomBooking, Long>{

	//List<RoomBooking> findByBookingId(Booking bookingId);

	//List<RoomBooking> findAllByBookingId(Booking bookingId);

	List<RoomBooking> findAllBybookingId(Booking bookingId);

	//List<RoomBooking> findByBillId(Bill billId);

	List<RoomBooking> findByBookingId(Booking bookingId);



}
