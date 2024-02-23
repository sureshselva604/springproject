package com.hotel.booking.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hotel.booking.Model.User;
import com.hotel.booking.Repo.UserRepo;

@Service
public class UserService {
	
	@Autowired
	private final UserRepo userRepository;
	
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void updatePassword(User user, String newPassword) {
    	try
    	{
    		
        // Encode the new password
        String encodedPassword = passwordEncoder.encode(newPassword);
        
        // Set the new password for the user
        user.setPassword(encodedPassword);
        
        // Save the updated user object
        userRepository.save(user);
    	}
    	catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to update user password.", e);
        }
    }

}
