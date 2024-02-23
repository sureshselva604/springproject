package com.hotel.booking.Service;

import org.springframework.stereotype.Service;

import com.hotel.booking.Model.PasswordResetToken;
import com.hotel.booking.Model.User;

@Service
public interface PasswordResetTokenService {
	
	 PasswordResetToken createToken(User user);

	 PasswordResetToken findByToken(String token);

	 void deleteToken(PasswordResetToken token);

}
