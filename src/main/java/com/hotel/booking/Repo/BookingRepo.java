package com.hotel.booking.Repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hotel.booking.Model.Booking;


@Repository
public interface BookingRepo extends JpaRepository<Booking, Long>{

	

	Booking findByBookingId(Long bookingId);

	//List<Booking> findByBookingId(Booking bookingId);

	List<Booking> findBybookingId(Long bookingId);

	//List<Booking> findById(Long bookingId);

	//List<Booking> findByBookingIdBooKingId(Long bookingId);

	

}
