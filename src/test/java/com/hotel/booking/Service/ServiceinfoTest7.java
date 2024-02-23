package com.hotel.booking.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Base64;

import org.json.JSONObject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.hotel.booking.Controller.Controller;
import com.hotel.booking.Model.Booking;
import com.hotel.booking.Model.User;
import com.hotel.booking.Repo.BookingRepo;
import com.hotel.booking.Repo.UserRepo;

class ServiceinfoTest7 {
	
	@InjectMocks
	private Serviceinfo serviceInfo;
	
	@Mock
	private Controller controller;
	
	@Mock
	private jwtService jwtService;
	
	@Mock
	private UserRepo userRepo;
	private String UserName,token,tokens;
	
	@Mock
	private BookingRepo bookingrepo;

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
		//this.bookingrepo = mock(BookingRepo.class); 
		//this.userRepo = mock(UserRepo.class);
		//BookingRepo bookingrepo = Mockito.mock(BookingRepo.class);
		token="Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJUaGFuZ2FtIiwiaWF0IjoxNzA3ODkwNTUyLCJleHAiOjE3MDc4OTIzNTJ9.J9PqQMxPjaYF2zZMEAwLGzv8QfGqleIRODAXN9pQhkc";
		String[] tokenParts = token.split("\\.");
        String payload = new String(Base64.getDecoder().decode(tokenParts[1]));
        JSONObject jsonPayload = new JSONObject(payload);
        String expectedUserName = jsonPayload.getString("sub");
		when(jwtService.extractUsername(anyString())).thenReturn(expectedUserName);
		UserName=serviceInfo.extractUserName(token);
        
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testEditUserDetails_Sucess() {
           User Existinguser=new User();
           Existinguser.setUserName(UserName);
           Existinguser.setAddress("mumbai");
           when(userRepo.findByUserName(UserName)).thenReturn(Existinguser);
           User UpdatedUSer=new User();
           UpdatedUSer.setAddress("Chennai");
           
           String actual = serviceInfo.editUserDetails(UpdatedUSer, UserName);
           assertEquals("Sucessfully Edited the details"+" "+UserName, actual);
           verify(userRepo, times(1)).save(Existinguser);
	}
	
	@Test
    void testEditUserDetails_Failure() {
		String NotexistingUser="Suresh";
		User NewUSer=new User();
		NewUSer.setUserName(NotexistingUser);
        when(userRepo.findByUserName(UserName)).thenReturn(null);
        
        String actual = serviceInfo.editUserDetails(NewUSer, UserName);
        assertEquals("UnAuthorized:User Not Found for"+" "+UserName, actual);
        //verify(userRepo, times(1)).save(NewUSer);
	}
	
	@Test
	void testEditUSerDetails_ExceptionHandling()
	{
		String userName = "existingUser";
        when(userRepo.findByUserName(userName)).thenThrow(new RuntimeException("Database error"));

        User user = new User();
        user.setUserName(userName);
        
        String result = serviceInfo.editUserDetails(user, userName);
        assertEquals("An error occurred while editing user details", result);

	}
	
	@Test
	void testEditBookingDetails_Sucess()throws Exception
	{
		User user=new User();
		user.setUserId(1l);
		user.setUserName(UserName);
		Booking existstingbookdetails=new Booking();
		existstingbookdetails.setBookingId(11l);
		existstingbookdetails.setUserId(user);
		existstingbookdetails.setNoOfAdults(1);
		existstingbookdetails.setNoOfChildrens(1);
		
		Booking updatingbookdetails=new Booking();
		updatingbookdetails.setNoOfAdults(4);
		updatingbookdetails.setNoOfChildrens(4);
		
		when(bookingrepo.findByBookingId(existstingbookdetails.getBookingId())).thenReturn(existstingbookdetails);
		when(userRepo.findByUserName(UserName)).thenReturn(user);
		String actual=serviceInfo.editBookingDetails(11l, updatingbookdetails, UserName);
		assertEquals("Booking details edited Succesfully for"+" "+UserName, actual);
		verify(bookingrepo, times(1)).save(existstingbookdetails);
		
	}

}
