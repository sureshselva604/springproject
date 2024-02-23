package com.hotel.booking.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.booking.Model.Location;

@Repository
public interface LocationRepo extends JpaRepository<Location, Long> {

	Location findByLocationId(Long locationId);

	boolean existsByPinCode(Long pinCode);

}
