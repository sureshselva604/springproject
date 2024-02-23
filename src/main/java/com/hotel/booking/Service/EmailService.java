package com.hotel.booking.Service;

import org.springframework.stereotype.Service;

import com.hotel.booking.Model.User;

@Service
public interface EmailService {
	 

	void sendPasswordResetEmail(User user, String token);

}
