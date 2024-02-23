package com.hotel.booking.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.booking.Model.HotelFacilities;
import com.hotel.booking.Model.Hotels;

@Repository
public interface HotelFacRepo extends JpaRepository<HotelFacilities, Long> {

	//HotelFacilities findByHotelFacId(Long hotelfacId);

	HotelFacilities findByhotelfacId(Long hotelfacId);

	List<HotelFacilities> findByHotelId(Hotels hotelId);

}
