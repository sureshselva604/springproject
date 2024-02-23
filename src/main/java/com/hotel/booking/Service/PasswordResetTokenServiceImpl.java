package com.hotel.booking.Service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.booking.Model.PasswordResetToken;
import com.hotel.booking.Model.User;
import com.hotel.booking.Repo.PasswordResetTokenRepository;

@Service
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService {

	
	    @Autowired
	    private PasswordResetTokenRepository tokenRepository;

	    @Override
	    public PasswordResetToken createToken(User user) {
	    	try
	    	{
	           PasswordResetToken token = new PasswordResetToken();
	           token.setToken(generateToken());
	           token.setUser(user);
	           token.setExpiryDate(LocalDateTime.now().plusMinutes(300)); // Token expires in 1 hour
	        return tokenRepository.save(token);
	    	}
	    	catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException("Failed to create password reset token.", e);
	        }
	    }

	    @Override
	    public PasswordResetToken findByToken(String token) {
	            return tokenRepository.findByToken(token);
	    }

	    @Override
	    public void deleteToken(PasswordResetToken token) {
	            tokenRepository.delete(token);
	    }

	    private String generateToken() {
	            return java.util.UUID.randomUUID().toString();
	    }
}
