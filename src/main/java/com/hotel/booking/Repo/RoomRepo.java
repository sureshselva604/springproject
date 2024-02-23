package com.hotel.booking.Repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.booking.Model.Hotels;
import com.hotel.booking.Model.Room;

@Repository
public interface RoomRepo extends JpaRepository<Room, Long> {

	Room findByRoomId(Long roomId);

	Optional<Room> findById(Long roomId);

	List<Room> findByHotelId(Hotels hotels);

	Room findByhotelId(Hotels hotelId);

}
