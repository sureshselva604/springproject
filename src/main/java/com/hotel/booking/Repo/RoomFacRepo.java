package com.hotel.booking.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hotel.booking.Model.Room;
import com.hotel.booking.Model.roomFacilities;


@Repository
public interface RoomFacRepo extends JpaRepository<roomFacilities, Long>{

	roomFacilities findByRoomfacId(Long roomFacId);

	List<roomFacilities> findByRoomId(Room roomFacId);

}
