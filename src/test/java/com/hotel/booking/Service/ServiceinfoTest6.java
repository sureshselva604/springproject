package com.hotel.booking.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoSettings;



class ServiceinfoTest6 {
	
	@InjectMocks
	private Serviceinfo serviceInfo;
	
	//@InjectMocks
	//private jwtService jwtService;
	
	@Mock
	private jwtService jwtService;
	private String username;
	
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
	void testExtractUserName_Sucess() 
	{
		
		String token="Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJUaGFuZ2FtIiwiaWF0IjoxNzA3ODkwNTUyLCJleHAiOjE3MDc4OTIzNTJ9.J9PqQMxPjaYF2zZMEAwLGzv8QfGqleIRODAXN9pQhkc";
		String expectedUserName="Thangam";
		String tokens=token.substring(7);
        when(jwtService.extractUsername(tokens)).thenReturn(expectedUserName);
		String actualUserName=serviceInfo.extractUserName(token);
		System.out.println(actualUserName);
		verify(jwtService).extractUsername("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJUaGFuZ2FtIiwiaWF0IjoxNzA3ODkwNTUyLCJleHAiOjE3MDc4OTIzNTJ9.J9PqQMxPjaYF2zZMEAwLGzv8QfGqleIRODAXN9pQhkc");
        assertEquals(expectedUserName, actualUserName);	
      }
	
	@Test
    void testExtractUserName_NullToken()
    {
		String token=null;
		String actualUserName = serviceInfo.extractUserName(token);
		assertEquals(null, actualUserName);
    }
	
	@Test
    void testExtractUserName_InvalidToken()
    {
		String token="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJUaGFuZ2FtIiwiaWF0IjoxNzA3ODkwNTUyLCJleHAiOjE3MDc4OTIzNTJ9.J9PqQMxPjaYF2zZMEAwLGzv8QfGqleIRODAXN9pQhkc";
		String actualUserName = serviceInfo.extractUserName(token);
		String exceptedUserName=null;
		assertEquals(exceptedUserName, actualUserName);

    }

}
