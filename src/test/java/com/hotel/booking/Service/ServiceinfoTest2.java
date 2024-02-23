package com.hotel.booking.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hotel.booking.Model.Bill;
import com.hotel.booking.Model.Booking;
import com.hotel.booking.Model.Cancellation;
import com.hotel.booking.Model.Discount;
import com.hotel.booking.Model.Location;
import com.hotel.booking.Model.Room;
import com.hotel.booking.Model.RoomBooking;
import com.hotel.booking.Model.User;
import com.hotel.booking.Repo.BillRepo;
import com.hotel.booking.Repo.BookingRepo;
import com.hotel.booking.Repo.CancellationRepo;
import com.hotel.booking.Repo.DiscountRepo;
import com.hotel.booking.Repo.RoomBookingRepo;

class ServiceinfoTest2 {
	
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
	public void testUpdatebilldetails() {
		
        //With Cancellation
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
    	
    	Cancellation cancell=new Cancellation();
    	cancell.setBookingId(booking);
    	cancell.setCancellationCharges(200.0);
    	Bill bills=new Bill();
    	bills.setBookingId(booking);

       
        when(billrepo.findByBookingId(any())).thenReturn(Collections.singletonList(bills));
        when(cancellationrepo.findAll()).thenReturn(Collections.singletonList(cancell));
        
        ResponseEntity<String> response = serviceinfo.updatebilldetails(bills);
        verify(billrepo, times(2)).save(any(Bill.class));
        double actual=serviceinfo.Cancellationcharges;
        assertEquals(200.0, actual);
	}
	
	@Test
	public void testUpdatebilldetailsWithoutCancellation() {
		
        //WithOut Cancellation For Updating Bill Details
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
    	
    	Cancellation cancell=new Cancellation();
    	cancell.setCancellationCharges(0.0);
    	Bill bills=new Bill();
    	bills.setBookingId(booking);
    	
    	Room rooms2=new Room();
    	rooms2.setRoomId(12l);
    	rooms2.setRoomType("Single Bed Room");
    	rooms2.setCapacity(4);
    	rooms2.setPricePerDay(150.0);
    	rooms2.setRoomNumber(121);
    	rooms2.setStatus("Available");
    	
    	Room rooms1=new Room();
    	rooms1.setRoomId(11l);
    	rooms1.setRoomType("Double Bed Room");
    	rooms1.setCapacity(4);
    	rooms1.setPricePerDay(250.0);
    	rooms1.setRoomNumber(122);
    	rooms1.setStatus("Available");
    			
    	List<Room> rooms = Arrays.asList(rooms1, rooms2);
    	
    	RoomBooking roomBooking=new RoomBooking();
    	roomBooking.setBookingId(booking);
    	roomBooking.setActiveStatus(true);
    	roomBooking.setRoomId(rooms2);
      
    	RoomBooking roomBooking1=new RoomBooking();
    	roomBooking1.setBookingId(booking);
    	roomBooking1.setActiveStatus(true);
    	roomBooking1.setRoomId(rooms1);
    	
    	List<RoomBooking> roomBookings = Arrays.asList(roomBooking1, roomBooking);
    	
    	Discount discount=new Discount();
    	discount.setDiscountId(1l);
    	discount.setDiscountDetails("Long Stay");
    	discount.setDiscountPercentage(10.0);
    	
    	Discount discount1=new Discount();
    	discount1.setDiscountId(2l);
    	discount1.setDiscountDetails("Group Members");
    	discount1.setDiscountPercentage(15.0);
    	
    	Discount discount3=new Discount();
    	discount3.setDiscountId(3l);
    	discount3.setDiscountDetails("Long Stay");
    	discount3.setDiscountPercentage(10.0);
    	
    	Discount discount4=new Discount();
    	discount4.setDiscountId(4l);
    	discount4.setDiscountDetails("No Discount");
    	discount4.setDiscountPercentage(0.0);
    	
    	List<Discount> discounts = Arrays.asList(discount3, discount1,discount);

        when(billrepo.findByBookingId(any())).thenReturn(Collections.singletonList(bills));
        when(cancellationrepo.findAll()).thenReturn(Collections.emptyList());
       // when(bookingrepo.findByBookingId(booking)).thenReturn(Collections.singletonList(booking));
        when(roombookingrepo.findAllBybookingId(booking)).thenReturn(roomBookings);
        when(discountrepo.findAll()).thenReturn(discounts);

       ResponseEntity<String> actual = serviceinfo.updatebilldetails(bills);
       verify(billrepo, times(2)).save(any(Bill.class));
       double actualtotalAmount=serviceinfo.totalAmount;
       int actualtotalNoOfPeoples=serviceinfo.totalPeoples;
       int actualtotalNoOfdays=serviceinfo.totaltotalNoOfdays;
       double actualdiscount=serviceinfo.actualdiscount;
       assertEquals(6, actualtotalNoOfPeoples);
       assertEquals(800,actualtotalAmount);
       assertEquals(2,actualtotalNoOfdays);
       assertEquals(680,actualdiscount);
       assertEquals("Bill Details Updated Successfully",actual.getBody());
	}
	
	@Test
	public void testBillDetails_ExceptionHandling()
	{
		Booking booking = new Booking();
    	booking.setBookingId(555l);
    	Bill bills=new Bill();
    	bills.setBookingId(booking);
        lenient().when(billrepo.save(any())).thenThrow(new RuntimeException("Test exception"));
    	
    	ResponseEntity<String> actual = serviceinfo.updatebilldetails(bills);
    	assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actual.getStatusCode());
    	assertEquals("An error occurred while updating bill details.", actual.getBody());
    	
		
	}
	

}
