package com.hotel.booking.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.booking.Model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

	User findByUserName(String username);

	
	User findByEmailId(String userEmail);

	Optional<User> findByUserId(Long userId);

	//User findByUserName(String username);
    
	//User findByUserNames(String userName);

	

}
