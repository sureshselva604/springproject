package com.hotel.booking.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.hotel.booking.Model.Booking;
import com.hotel.booking.Model.Discount;
import com.hotel.booking.Model.HotelFacilities;
import com.hotel.booking.Model.Hotels;
import com.hotel.booking.Model.Location;
import com.hotel.booking.Model.Room;
import com.hotel.booking.Model.RoomBooking;
import com.hotel.booking.Model.User;
import com.hotel.booking.Model.roomFacilities;
import com.hotel.booking.Repo.BillRepo;
import com.hotel.booking.Repo.BookingRepo;
import com.hotel.booking.Repo.CancellationRepo;
import com.hotel.booking.Repo.DiscountRepo;
import com.hotel.booking.Repo.HotelFacRepo;
import com.hotel.booking.Repo.HotelRepo;
import com.hotel.booking.Repo.LocationRepo;
import com.hotel.booking.Repo.RoomBookingRepo;
import com.hotel.booking.Repo.RoomFacRepo;
import com.hotel.booking.Repo.RoomRepo;
import com.hotel.booking.Repo.UserRepo;


class ServiceInfoTest3 {
	
	@Mock
	private BillRepo billRepo;
	
	@Mock
	private BookingRepo bookRepo;
	
	@Mock
	private CancellationRepo cancellationRepo;
	
	@Mock
	private DiscountRepo discountRepo;
	
	@Mock
	private HotelFacRepo hotelfacRepo;
	
	@Mock
	private HotelRepo hotelRepo;
	
	@Mock
	private RoomRepo roomRepo;
	
	@Mock
	private RoomFacRepo roomFacRepo;;
	
	@Mock
	private RoomBookingRepo roomBookingRepo;
	
	@Mock
	private UserRepo userRepo;
	
	@Mock
	private LocationRepo locationRepo;
	
	@InjectMocks
	private Serviceinfo serviceInfo;
	
	@InjectMocks
	private CustomerInfoService controller;
	
	@Mock
	private PasswordEncoder encoder;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@SuppressWarnings("deprecation")
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testSaveLocation() {
		//Saving an Location Details..
		Location location=new Location();
		location.setLocationId(22l);
		location.setLocationName("Light House");
		location.setLocationState("Tamil Nadu");
		location.setLocationDistrict("Chennai");
		location.setPinCode(678908l);
		location.setCountry("India");
		
		
        when(locationRepo.save(any(Location.class))).thenReturn(location);
        String actual=serviceInfo.createlocationdetails(location);
        assertEquals("Locations Added successfully",actual);  		
	}
	@Test
	void testSaveLocation_Null()
	{
		Location location1=null;
        when(locationRepo.save(any(Location.class))).thenReturn(null);
        String actual=serviceInfo.createlocationdetails(location1);
        assertEquals("Location Details cannot be Null",actual);  		
	}
	
	@Test
	void testSaveLocation_ExceptionHandling()
	{
		Location location=new Location();
        when(locationRepo.save(any())).thenThrow(new DataAccessException("Test exception") {});
        String actual=serviceInfo.createlocationdetails(location);
        assertEquals("An error occurred while adding the location",actual);
	}
	@Test
	void testSaveHotel()
	{
		Hotels hotel=new Hotels();
		hotel.setHotelId(111l);
		hotel.setHotelName("Ats");
		when(hotelRepo.save(any(Hotels.class))).thenReturn(hotel);
        String actual=serviceInfo.createhoteldetails(hotel);
        assertEquals("Hotel Added successfully",actual);  
	}
	@Test
	void testSaveHotel_Null()
	{
		Hotels hotels=null;
        when(hotelRepo.save(any(Hotels.class))).thenReturn(null);
        String actual=serviceInfo.createhoteldetails(hotels);
        assertEquals("Hotel Details cannot be Null",actual); 
	}
	@Test
	void testSaveHotel_ExceptionHandling()
	{
		Hotels hotel=new Hotels();
		when(hotelRepo.save(any())).thenThrow(new DataAccessException("Test exception") {});
        String actual=serviceInfo.createhoteldetails(hotel);
        assertEquals("An error occurred while adding the hotel",actual);
	}
	
	@Test
	void testSaveDiscount()
	{
		Discount discount=new Discount();
        discount.setDiscountId(11l);
        discount.setDiscountDetails("Long Stay");
        when(discountRepo.save(any(Discount.class))).thenReturn(discount);
        String actual=serviceInfo.creatediscountdetails(discount);
        assertEquals("Discount Added successfully",actual);
	}
	@Test
	void tetstSaveDiscount_Null()
	{
		Discount discounts=null;
        when(discountRepo.save(any(Discount.class))).thenReturn(discounts);
        String actual=serviceInfo.creatediscountdetails(discounts);
        assertEquals("Discount Details cannot be Null",actual); 
	}
	@Test
	void testSaveDiscount_ExceptionHandling()
	{
		Discount discount=new Discount();
        when(discountRepo.save(any())).thenThrow(new DataAccessException("Test exception") {});
        String actual=serviceInfo.creatediscountdetails(discount);
        assertEquals("An error occurred while adding the discount",actual);
	}
	
	@Test
    void testSaveRoom()
    {
			Room rooms=new Room();																			
			rooms.setRoomId(11l);
			rooms.setRoomNumber(112);
			rooms.setRoomType("Double Bed Room");
			when(roomRepo.save(any(Room.class))).thenReturn(rooms);
	        String actual=serviceInfo.roomdetails(rooms);
	        assertEquals("Rooms Added successfully",actual);  
    }
	@Test
    void testSaveRoom_Null()
    {
        Room rooms=null;	
        when(roomRepo.save(any(Room.class))).thenReturn(rooms);	
        String actual=serviceInfo.roomdetails(rooms);
        assertEquals("Rooms Details cannot be Null",actual); 
    }
	@Test
	void testSaveRoom_Exceptionhandling()
	{
		Room rooms=new Room();
        when(roomRepo.save(any())).thenThrow(new DataAccessException("Test exception") {});
        String actual=serviceInfo.roomdetails(rooms);
        assertEquals("An error occurred while adding the room",actual);
	}
	@Test
	void testSaveHotelFacilities()
	{
		HotelFacilities hotelfac=new HotelFacilities();
		hotelfac.setHotelfacId(11l);
		hotelfac.setSwimingPool(true);
		when(hotelfacRepo.save(any(HotelFacilities.class))).thenReturn(hotelfac);
		String actual=serviceInfo.createhotelfacilities(hotelfac);
        assertEquals("HotelFacilities Added successfully",actual);  
	}
	@Test
    void testSaveHotelFacilities_Null()
    {
		HotelFacilities hotelfac=null;
		when(hotelfacRepo.save(any(HotelFacilities.class))).thenReturn(hotelfac);
		String actual=serviceInfo.createhotelfacilities(hotelfac);
		assertEquals("Hotel Facilities cannot be Null",actual);
    }
	
    @Test
    void testSaveHotelFacilities_Exceptionhandling()
    {
    		HotelFacilities hotelfac=new HotelFacilities();
            when(hotelfacRepo.save(any())).thenThrow(new DataAccessException("Test exception") {});
            String actual=serviceInfo.createhotelfacilities(hotelfac);
            assertEquals("An error occurred while adding the hotel Facilities",actual);	
    }
	
	@Test
    void testSaveRoomFacilities()
    {
		roomFacilities roomfacilities=new roomFacilities();
		roomfacilities.setRoomfacId(11l);
		roomfacilities.setKitchen(true);
		when(roomFacRepo.save(any(roomFacilities.class))).thenReturn(roomfacilities);
		String actual=serviceInfo.createroomfacdetails(roomfacilities);
		assertEquals("RoomsFacilities Added successfully",actual);	
    }
	
	@Test
	void testSaveRoomFacilities_Null()
	{
		roomFacilities roomfacilities=null;
        when(roomFacRepo.save(any(roomFacilities.class))).thenReturn(roomfacilities);
        String actual=serviceInfo.createroomfacdetails(roomfacilities);
        assertEquals("RoomsFacilities Details cannot be Null",actual);
	}
	
	@Test
    void testSaveRoomFacilities_Exceptionhandling()
    {
		roomFacilities roomfacilities=new roomFacilities();
		when(roomFacRepo.save(any())).thenThrow(new DataAccessException("Test exception") {});
		String actual=serviceInfo.createroomfacdetails(roomfacilities);
		assertEquals("An error occurred while adding the rooms facilities",actual);
    }
	@Test
    void testSaveRoomBooking()
    {
		Room rooms=new Room();
		rooms.setRoomId(11l);
		Booking booking=new Booking();
		booking.setBookingId(99l);
		RoomBooking roomBooking=new RoomBooking();
		roomBooking.setRoombookingId(14l);
		roomBooking.setRoomId(rooms);
		roomBooking.setActiveStatus(true);
		roomBooking.setBookingId(booking);
		when(roomBookingRepo.save(any(RoomBooking.class))).thenReturn(roomBooking);
		String actual=serviceInfo.createroombookingdetails(roomBooking);
		assertEquals("RoomBooking Details Added successfully",actual);
		
    }
	
	@Test
    void testSaveRoomBooking_Null()
    {
		RoomBooking roomBooking=null;
		when(roomBookingRepo.save(any(RoomBooking.class))).thenReturn(roomBooking);
		String actual=serviceInfo.createroombookingdetails(roomBooking);
		assertEquals("RoomBooking Details cannot be Null",actual);
    }
	
	@Test
    void testSaveRoomBooking_Exceptionhandling()
    {
		RoomBooking roomBooking=new RoomBooking();
		when(roomBookingRepo.save(any())).thenThrow(new DataAccessException("Test exception") {});
		String actual=serviceInfo.createroombookingdetails(roomBooking);
		assertEquals("An error occurred while adding the RoomBooking Details",actual);
    }
    
	
	@Test
    void testSaveUser()
    {
  	    //Giving Invalid Credentials
		User user=new User();
		user.setUserId(100l);
		user.setUserName("Suresh");
		user.setPassword("sure");
		user.setContactNumber("98765432");
		user.setAddress("chennai");
		user.setEmailId("sure@gmail.com");
		when(userRepo.save(any(User.class))).thenReturn(user);
	//	ResponseEntity<String> actual=controller.addUser(user);
		//assertEquals("Invalid Credentials",actual.getBody());
       // assertEquals(HttpStatus.BAD_REQUEST, actual.getStatusCode());		
    }
	
	@Test
	void testSaverUserSucess()
	{
		//Giving Valid Credentials
		User user=new User();
		user.setUserId(100l);
		user.setUserName("Suresh");
		user.setPassword("Sure@123");
		user.setContactNumber("9876543298");
		user.setAddress("chennai");
		user.setEmailId("Sure123@gmail.com");
		user.setAadharNumber(555566667777l);
		user.setRoles("ROLE_USER");
		
		when(userRepo.save(any(User.class))).thenReturn(user);
		//ResponseEntity<String> actual=controller.addUser(user);
		//assertEquals("User Added Successfully",actual.getBody());
		//assertEquals(HttpStatus.CREATED, actual.getStatusCode());
	}
	
	@Test
	void testSaveBooking()
	{
		    User user=new User();
	    	user.setAadharNumber(111122233344l);
	    	user.setAddress("chennai");
	    	user.setEmailId("tytus123@gmail.com");
	    	user.setRoles("ROLE_USER");
	    	user.setContactNumber("9067543201");
	    	user.setUserName("Tytus");
	    	user.setPassword("Tytus@123");
	    	user.setUserId(101l);
	    	Location location=new Location();
	    	location.setLocationId(6l);
	    	location.setLocationName("Perungaluthur");
	    	location.setLocationState("Tamil Nadu");
	    	location.setPinCode(606009l);
	    	location.setLocationDistrict("Chennai");
	    	location.setCountry("India");
	    	Booking booking = new Booking();
	    	booking.setBookingId(555l);
	    	booking.setLocationId(location);
	    	booking.setNoOfAdults(4);
	    	booking.setNoOfChildrens(2);
	    	booking.setNoOfRooms(2);
	    	LocalDateTime checkIndate=LocalDateTime.of(2024, 2, 14, 15, 30, 0);
	    	LocalDateTime bookingDate=LocalDateTime.of(2024, 2, 12, 15, 30, 0);
	       	LocalDateTime checkOutDate=LocalDateTime.of(2024, 2, 16, 15, 30, 0);
	    	booking.setCheckInDate(checkIndate);
	    	booking.setBookingDate(bookingDate);
	    	booking.setCheckOutDate(checkOutDate);
	    	booking.setUserId(user);
	    	
	    	when(bookRepo.save(any(Booking.class))).thenReturn(booking);
	    	String actualBooking=serviceInfo.createBooking(booking);
	    	assertEquals("Booking Details Added successfully",actualBooking);  	
	}
	
	@Test
    void testSaveBooking_Null()
    {
		Booking booking=null;
		when(bookRepo.save(any(Booking.class))).thenReturn(booking);
		String actual=serviceInfo.createBooking(booking);
		assertEquals("Booking Details cannot be Null",actual);
		
    }
    
	@Test
	void testSaveBooking_Exceptionhandling()
	{
		Booking booking=new Booking();
        when(bookRepo.save(any())).thenThrow(new DataAccessException("Test exception") {});
        String actual=serviceInfo.createBooking(booking);
        assertEquals("An error occurred while adding the Booking Details",actual);
	}
            
}
