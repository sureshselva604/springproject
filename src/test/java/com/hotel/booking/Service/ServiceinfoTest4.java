package com.hotel.booking.Service;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hotel.booking.Model.Booking;
import com.hotel.booking.Model.Discount;
import com.hotel.booking.Model.Hotels;
import com.hotel.booking.Model.Location;
import com.hotel.booking.Model.Room;
import com.hotel.booking.Model.RoomBooking;
import com.hotel.booking.Repo.BillRepo;
import com.hotel.booking.Repo.BookingRepo;
import com.hotel.booking.Repo.CancellationRepo;
import com.hotel.booking.Repo.DiscountRepo;
import com.hotel.booking.Repo.HotelRepo;
import com.hotel.booking.Repo.LocationRepo;
import com.hotel.booking.Repo.RoomBookingRepo;
import com.hotel.booking.Repo.RoomFacRepo;
import com.hotel.booking.Repo.RoomRepo;

class ServiceinfoTest4 {
	
	@InjectMocks
	private Serviceinfo serviceinfo;
	
	@Mock
	private BillRepo billrepo;
	
	@Mock
	private BookingRepo bookingrepo;
	
	@Mock
	private CancellationRepo cancellationrepo;
	
	@Mock
	private RoomBookingRepo roombookingrepo;
	
	@Mock
	private DiscountRepo discountrepo;
	
	@Mock
    private LocationRepo locationRepo;
	
	@Mock
	private RoomBookingRepo roomBookingRepo;
	
	@Mock
    private RoomRepo roomrepo;
    
    @Mock
    private HotelRepo hotelRepo;
    
    @Mock
    private BillRepo billRepo;
    
    @Mock 
    private RoomFacRepo roomFacRepo;
    
   

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
	void testLocationDetails() {
		Location location=new Location();
		location.setLocationId(11l);
		location.setLocationName("Madurai");
		Location location1=new Location();
		location1.setLocationId(12l);
		location1.setLocationName("Salem");
		List<Location>locations=new ArrayList<Location>();
		locations.add(location);
		locations.add(location1);
		
        when(locationRepo.findAll()).thenReturn(locations);
		List<Location>actual=serviceinfo.getalllocationdetails();
		
		assertEquals(locations, actual);
		assertEquals(2,locations.size());
	}
	
	@Test
	void testgetallhoteldetails()
	{
		Hotels hotel1=new Hotels();
		hotel1.setHotelId(1l);
		hotel1.setHotelName("Andhra Meals");
		List<Hotels>hotels=new ArrayList<Hotels>();
		hotels.add(hotel1);
		
        when(hotelRepo.findAll()).thenReturn(hotels);
		List<Hotels>actual=serviceinfo.getallhoteldetails();
		
		assertEquals(hotels, actual);
		assertEquals(1,hotels.size());
		
	}
	
	@Test
	void testgetallroomdetails()
	{
		Room room1=new Room();
        room1.setRoomId(1l);
        room1.setRoomType("Single");
        List<Room>rooms=new ArrayList<Room>();
        rooms.add(room1);
        
        when(roomrepo.findAll()).thenReturn(rooms);
        List<Room>actual=serviceinfo.getallRoomdetails();
        
        assertEquals(rooms, actual);
        assertEquals(1,rooms.size());
	}
	
	@Test
	void testgetalldiscountdetails()
	{
		Discount discount1=new Discount();
        discount1.setDiscountId(1l);
        discount1.setDiscountDetails("Long Stay");
        List<Discount>discounts=new ArrayList<Discount>();
        discounts.add(discount1);
        
        when(discountrepo.findAll()).thenReturn(discounts);
        List<Discount>actual=serviceinfo.getalldiscountdetails();
      //  System.out.println(actual);
        
        assertEquals(discounts, actual);
        assertEquals(1,discounts.size());
	}
	
	@Test
    void testgetallroombookingdetails()
    {
		Room room1=new Room();
        room1.setRoomId(1l);
        Booking booking=new Booking();
        booking.setBookingId(1l);
		RoomBooking	roombook=new RoomBooking();
		roombook.setBookingId(booking);
		roombook.setRoomId(room1);
		roombook.setRoombookingId(12l);
		
		List<RoomBooking>roombookings=new ArrayList<RoomBooking>();
		roombookings.add(roombook);
		
		when(roomBookingRepo.findAll()).thenReturn(roombookings);
		
        List<RoomBooking> actual=serviceinfo.getAllBookingDetails();
        System.out.println(actual);
        assertEquals(roombookings, actual);	
        assertEquals(1,roombookings.size());
    }
	

}
