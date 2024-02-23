package com.hotel.booking.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Collections;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hotel.booking.Model.Bill;
import com.hotel.booking.Model.Booking;
import com.hotel.booking.Model.Cancellation;
import com.hotel.booking.Model.Location;
import com.hotel.booking.Model.User;
import com.hotel.booking.Repo.BillRepo;
import com.hotel.booking.Repo.BookingRepo;
import com.hotel.booking.Repo.CancellationRepo;
import com.hotel.booking.Repo.DiscountRepo;
import com.hotel.booking.Repo.LocationRepo;
import com.hotel.booking.Repo.UserRepo;
import com.hotel.booking.Service.Serviceinfo;

@ExtendWith(MockitoExtension.class)
class ServiceinfoTest {
	
	@Mock
    private LocationRepo locationRepo;
	
	@Mock
	private BookingRepo bookingrepo;
	
	@Mock
	private CancellationRepo cancellationrepo;
	
	@Mock
	private BillRepo billRepo;
	
	@Mock
	private UserRepo userRepo;
	
	@Mock
	private DiscountRepo discountrepo;
	
	@InjectMocks
	private Serviceinfo serviceInfo;
	
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
	    public void testCancellationdetails_AlreadyCancelled()
	    {
	    	Booking booking = new Booking();
	    	booking.setBookingId(1l);
	    	Cancellation cancell = new Cancellation();
	    	cancell.setBookingId(booking);
	    	cancell.setBookingId(booking);
	    	
	    	when(cancellationrepo.existsByBookingId(any())).thenReturn(true);
	    	ResponseEntity<String> actual = serviceInfo.updatecancellationdetails(cancell);
	    	assertEquals("Already Cancelled",actual.getBody());
	    }
	    
	    @Test
	    public void testCancellationDetails_SuccessfullCancellation() {
	    	
	    	Booking booking=new Booking();
	    	booking.setBookingId(22l);
	    	booking.setCheckInDate(LocalDateTime.now().plusDays(4));
	    	booking.setCheckOutDate(LocalDateTime.now().plusDays(6));
	        
	    	Cancellation cancell=new Cancellation();
	        cancell.setBookingId(booking);
	     
	        when(cancellationrepo.existsByBookingId(any())).thenReturn(false);
	        when(cancellationrepo.findByBookingIdBookingId(any())).thenReturn(cancell);
	        when(bookingrepo.findByBookingId(cancell.getBookingId().getBookingId())).thenReturn(booking);
	        
	        ResponseEntity<String> actual= serviceInfo.updatecancellationdetails(cancell);
	        assertEquals("Successfully Cancelled",actual.getBody());
	    	
	    }
	    
	    @Test
	    public void testCancellationDetails_CancellationOver()
	    {
	    	Booking booking=new Booking();
	    	booking.setBookingId(23l);
	    	booking.setCheckInDate(LocalDateTime.now().plusDays(1));
	    	booking.setCheckOutDate(LocalDateTime.now().plusDays(6));
	        
	    	Cancellation cancell=new Cancellation();
	        cancell.setBookingId(booking);
	        
	        when(cancellationrepo.existsByBookingId(any())).thenReturn(false);
	        when(cancellationrepo.findByBookingIdBookingId(cancell.getBookingId().getBookingId())).thenReturn(cancell);
	        when(bookingrepo.findByBookingId(cancell.getBookingId().getBookingId())).thenReturn(booking);
	        
	        ResponseEntity<String> actual= serviceInfo.updatecancellationdetails(cancell);
	        assertEquals("Cancellation time is over",actual.getBody());

       }
	    
	   @Test
	   public void testCancellationDetails_ExceptionHandling()
	   {
		   
		    Booking booking=new Booking();
	    	booking.setBookingId(23l);
	    	booking.setCheckInDate(LocalDateTime.now().plusDays(1));
	    	booking.setCheckOutDate(LocalDateTime.now().plusDays(6));
	        
	    	Cancellation cancell=new Cancellation();
	        cancell.setBookingId(booking);
	        
	        lenient().when(cancellationrepo.existsByBookingId(any())).thenReturn(false);
	        lenient().when(cancellationrepo.findByBookingIdBookingId(any())).thenReturn(null);
	        lenient().when(bookingrepo.findByBookingId(cancell.getBookingId().getBookingId())).thenThrow(new RuntimeException("Something went wrong"));
            
	        ResponseEntity<String> actual = serviceInfo.updatecancellationdetails(cancell);
	        assertEquals(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while processing the cancellation request."),
                    actual);
		   
	   }
	    
	   
	    
	    
}
