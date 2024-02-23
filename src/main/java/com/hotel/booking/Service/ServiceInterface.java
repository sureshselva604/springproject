package com.hotel.booking.Service;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hotel.booking.Model.Bill;
import com.hotel.booking.Model.Booking;
import com.hotel.booking.Model.Cancellation;
import com.hotel.booking.Model.Discount;
import com.hotel.booking.Model.HotelFacilities;
import com.hotel.booking.Model.Hotels;
import com.hotel.booking.Model.Location;
import com.hotel.booking.Model.Payment;
import com.hotel.booking.Model.Room;
import com.hotel.booking.Model.RoomBooking;
import com.hotel.booking.Model.User;
import com.hotel.booking.Model.roomFacilities;

@Service
public interface ServiceInterface {

	
	public String createlocationdetails(Location location);
	
	public String createhoteldetails(Hotels hotel);
	
	public String roomdetails(Room room);
	
	public String createroomfacdetails(roomFacilities roomfac);
	
	public String createhotelfacilities(HotelFacilities hotelfac);
	
	public String createBooking(Booking booking);
	
	public List<User> getalluserdetails();
	
	public List<Hotels> getallhoteldetails();
	
	public List<Location> getalllocationdetails();
	
	public List<Room> getallRoomdetails();
	
	public List<roomFacilities> getallroomfacilities();
	
	public List<HotelFacilities> gethotelallfacilities();
	
	public ResponseEntity<String> updatebilldetails(Bill bills);
	
	public ResponseEntity<String> updatecancellationdetails(Cancellation cancell);
	
	public List<Booking> getBookingdetails();
	
	public String creatediscountdetails(Discount discount);
	
	public String createroombookingdetails(RoomBooking roombooking);
	
	public List<RoomBooking> getAllBookingDetails();
	
	public ResponseEntity<String> updatePaymentDetails(Payment payment);
	
	public String editUserDetails(User user,String userName);

	public String editLocationDetails(Long locationId, Location location);

	public String editBookingDetails(Long bookingId, Booking booking,String userName);

	public String editDiscountDetails(Long discountId, Discount discount);

	public String edithoteldetails(Long hotelId, Hotels hotel,String userName);

	public String editroomdetails(Long roomId, Room rooms,String userName);

	public String editHotelFacilities(Long hotelfacId, HotelFacilities hotelfacilities,String userName);

	public String editroomFacilities(Long roomFacId, roomFacilities roomfacilities);

	public List<Discount> getalldiscountdetails();
	
	public String extractUserName(String token);

	public List<Room> AvailableRoomsForHotelId(Room rooms);

	public List<Room> totalRoomsForHotelId(Room rooms);

	public List<roomFacilities> roomFacForRoomId(roomFacilities rooms);

	public List<HotelFacilities> hotelFacForHotelId(HotelFacilities hotelfac);
	
	
	

	

	

	
	
}
