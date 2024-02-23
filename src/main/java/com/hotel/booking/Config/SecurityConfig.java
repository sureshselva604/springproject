package com.hotel.booking.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.hotel.booking.Filter.jwtFilter;
import com.hotel.booking.Service.CustomerInfoService;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@SecurityScheme(

        name = "spring_security",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat="JWT",
        in=SecuritySchemeIn.HEADER

)

public class SecurityConfig {
	
	 
	     @Autowired
	     private jwtFilter authFilter; 
	 
	
	     @Bean
	    public UserDetailsService userDetailsService() { 
	        return new CustomerInfoService(); 
	    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { 
			System.out.println("Has Authority");
	        return http.csrf(AbstractHttpConfigurer::disable)
                    .cors(AbstractHttpConfigurer::disable)
                    .authorizeHttpRequests((authorizeRequests)->
							authorizeRequests
							.requestMatchers("/api/hotelbooking/welcome","/api/hotelbooking/AvailableRoomsForHotelId","/api/hotelbooking/totalRoomsForHotelId",
							        "/api/hotelbooking/addNewUser", "/api/hotelbooking/login","api/hotelbooking//totalrooms/{hotelId}",
							        "/api/hotelbooking/logout", "/api/hotelbooking/getallhoteldetails",
							        "/api/hotelbooking/getbookingdetails", "/api/hotelbooking/forgotpassword",
							        "/api/hotelbooking/resetpassword",
							        "/swagger-resources/**", "/swagger-ui.html",
							        "/v3/api-docs/**", "/webjars/**", "/swaggerfox.js", "/swagger-ui/**").permitAll()
							.requestMatchers("/api/hotelbooking/user/**").hasAuthority("ROLE_USER")
							.requestMatchers("/api/hotelbooking/admin/**").hasAuthority("ROLE_ADMIN")
							.requestMatchers("/api/hotelbooking/hotelowner/**").hasAuthority("ROLE_HOTELOWNER"))
					
                    .sessionManagement(management -> management
                            .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .authenticationProvider(authenticationProvider())
                    .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                    .build(); 
	    } 
	  
	    @Bean
	    public PasswordEncoder passwordEncoder() { 
	        return new BCryptPasswordEncoder(); 
	    } 
	  
	  
	    @Bean
	    public AuthenticationProvider authenticationProvider() { 
	    
	        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(); 
	        authenticationProvider.setUserDetailsService(userDetailsService()); 
	        authenticationProvider.setPasswordEncoder(passwordEncoder()); 
	        return authenticationProvider; 
	    } 
	  
	    @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception { 
	        return config.getAuthenticationManager(); 
	    } 
	  

}
