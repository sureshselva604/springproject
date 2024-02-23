package com.hotel.booking.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.booking.Model.Hotels;

@Repository
public interface HotelRepo extends JpaRepository<Hotels, Long>{

	Hotels findByHotelId(Long hotelId);

}
