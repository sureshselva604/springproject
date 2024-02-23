package com.hotel.booking.MockMVC;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotel.booking.Controller.Controller;
import com.hotel.booking.Model.Location;
import com.hotel.booking.Model.User;
import com.hotel.booking.Pojo.UserPojo;
import com.hotel.booking.Service.CustomerInfoService;
import com.hotel.booking.Service.PasswordResetTokenServiceImpl;
import com.hotel.booking.Service.ServiceInterface;
import com.hotel.booking.Service.UserService;
import com.hotel.booking.Service.jwtService;
import com.hotel.booking.Repo.HotelRepo;
import com.hotel.booking.Repo.LocationRepo;
import com.hotel.booking.Repo.RoomRepo;
import com.hotel.booking.Repo.UserRepo;

@WebMvcTest(Controller.class)
@ExtendWith(MockitoExtension.class)
//@WithMockUser(username = "testUser", roles = {"USER", "ADMIN"})
public class AddNewUserTest {
	
    String token;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerInfoService userService;
    
    @MockBean
    private ServiceInterface service;
    
    @MockBean
    private PasswordResetTokenServiceImpl passwordresetTokenservice;

    @MockBean
    private UserRepo userRepo;
    
    @MockBean
    private LocationRepo locationRepo;
    
    @MockBean
    private HotelRepo hotelrepo;
    
    @MockBean
    private RoomRepo roomRepo;

    @Autowired
    private WebApplicationContext context;
    
    @MockBean
    private AuthenticationManager authenticationManager;
    
    @InjectMocks
    private jwtService jwtservice;
    
    @MockBean
    private UserService userservice;
    
    @MockBean
    private Authentication authentication; 
    
    @MockBean
    private UserDetails userDetails;
    
    
//    private String generateToken() {
//        
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
//        System.out.println("Authentication:"+authentication);
//        when(userService.loadUserByUsername(authRequest.getUserName())).thenReturn(userDetails);
//        when(userRepo.findByUserName(authRequest.getUserName())).thenReturn(user);
//        return jwtservice.generateToken(authRequest.getUserName(),userDetails.getAuthorities());
//        
//    }
  

    @BeforeEach
   public void setup() {
       this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
       .apply(SecurityMockMvcConfigurers.springSecurity())
       .build();
       User user = new User();
       user.setUserName("John Doe");
       user.setEmailId("john.doe@example.com");
       user.setAddress("chennai");
       user.setPassword("Password@123");
       user.setAadharNumber(111122223333L);
       user.setContactNumber("9047262272");
       user.setRoles("ROLE_ADMIN");
       userRepo.save(user);
       
       User user1 = new User();
       user1.setUserName("Thangam");
       user1.setEmailId("john.doe@example.com");
       user1.setAddress("chennai");
       user1.setPassword("Thangam@123");
       user1.setAadharNumber(888877776666L);
       user1.setContactNumber("9034765432");
       user1.setRoles("ROLE_USER");
       userRepo.save(user1);
       
       
       UserPojo authRequest = new UserPojo();
       authRequest.setUserName("John Doe");
       authRequest.setPassword("Password@123");
       when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(),authRequest.getPassword())))
       .thenReturn(authentication);
       System.out.println("Authentication:"+authentication);
       when(authentication.isAuthenticated()).thenReturn(true);
       when(userRepo.findByUserName(authRequest.getUserName())).thenReturn(user);
       System.out.println("User:"+user);
       Optional<User> optionalUser = Optional.of(user);
       when(userRepo.findByUserId(user.getUserId())).thenReturn(optionalUser);
       System.out.println("OptionalUser:"+optionalUser);
       UserDetails userDetails = userService.loadUserByUsername(authRequest.getUserName());
       System.out.println("UserName:"+userDetails.getUsername());
       System.out.println("Authorities"+userDetails.getAuthorities());
       token = jwtservice.generateToken(userDetails.getUsername(),userDetails.getAuthorities());

       System.out.println(" My Token is:"+token);

      
        

   }
      
	  @Test 
	  public void testWelcome() throws Exception { 
		  mockMvc
		        .perform(MockMvcRequestBuilders.get("/api/hotelbooking/welcome")) 
				.andExpect(status().isOk())
	            .andExpect(content().string("Welcome this endpoint is not secure"));
	
	  }
	 
    @Test
    public void testAddNewUser() throws Exception {
        User user = new User();
        user.setUserName("John Doe");
        user.setEmailId("john.doe@example.com");
        user.setAddress("chennai");
        user.setPassword("Password@123");
        user.setAadharNumber(111122223333L);
        user.setContactNumber("9047262272");
        user.setRoles("ROLE_USER");

        String expectedResponse = "User added successfully";

        when(userService.addUser(any(User.class))).thenReturn(expectedResponse);

        mockMvc.perform(post("/api/hotelbooking/addNewUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(user))
                .with(SecurityMockMvcRequestPostProcessors.anonymous()))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));

        verify(userService, times(1)).addUser(any(User.class));
    }
    
    @Test
    public void testAddNewUser_InvalidDetails() throws Exception {
    	User user = new User();
        user.setUserName("JohnDoe123");
        user.setEmailId("john.doe@example.com");
        user.setAddress("chennai");
        user.setPassword("Password@123");
        user.setAadharNumber(111122223333L);
        user.setContactNumber("9047262272");
        user.setRoles("ROLE_USER");
        
        String expectedResponse = "Invalid Credentials";

        when(userService.addUser(any(User.class))).thenReturn(expectedResponse);

        mockMvc.perform(post("/api/hotelbooking/addNewUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));
        
        verify(userService, times(1)).addUser(any(User.class));


    }
    
    
//    @Test
//    public void testAuthenticateAndGetToken_validCredentials_returnsToken() throws Exception {
//
//    	 UserPojo authRequest = new UserPojo();
//         authRequest.setUserName("JohnDoe");
//         authRequest.setPassword("Suresh@123");
//         String ExpectedToken="";
//         
//         
//			
//        MvcResult result = mockMvc.perform(post("/api/hotelbooking/login")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content( new ObjectMapper().writeValueAsString(authRequest)))
//        		 .andDo(print())
//                 .andExpect(status().isOk())
//                 .andReturn();
//        System.out.println("Result:"+result);
//        MockHttpServletResponse responseContent = result.getResponse();
//        System.out.println("ResponseContent:"+responseContent);
//        String token=result.getResponse().getContentAsString();
//        System.out.println("Tokensss:"+token);
//
//    }
    
   @Test
   public void testAddNewLocation()throws Exception
    {
    	Location location=new Location();
    	location.setLocationId(11l);
    	location.setLocationName("Avadi");
    	location.setLocationDistrict("Chennai");
    	location.setLocationState("Tamil Nadu");
    	location.setCountry("India");
    	location.setPinCode(777888l);
    	String expectedResponse="Locations Added successfully";
	    when(service.createlocationdetails(any(Location.class))).thenReturn(expectedResponse);
    	
	 //   String tokens="eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdGllcyI6W3siYXV0aG9yaXR5IjoiUk9MRV9VU0VSIn1dLCJzdWIiOiJUaGFuZ2FtIiwiaWF0IjoxNzA4NjgyMzMyLCJleHAiOjE3MDg2ODQxMzJ9.PXrlqoGGYkEstWKDXp--xy3bsk2bsTE02-IrOrUPcG0";

         System.out.println("The token is"+token);       
                RequestBuilder requestBuilder =
                		 MockMvcRequestBuilders
                        .post("/api/hotelbooking/admin/createlocationdetails")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(location))
                        .header("Authorization", "Bearer " + token);
                        
               MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
               MockHttpServletResponse response = mvcResult.getResponse();
               System.out.println("ResponseStatus:"+response.getStatus());
               assertEquals(HttpStatus.OK.value(), response.getStatus());
               System.out.println("ResponseContentString:"+response.getContentAsString());
               assertEquals(expectedResponse, response.getContentAsString());
     
   	    verify(service,times(1)).createlocationdetails(any(Location.class));
   }
}