package com.hotel.booking.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.hotel.booking.Model.User;
import com.hotel.booking.Repo.UserRepo;

@Component
@Primary
public class CustomerInfoService  implements UserDetailsService{
	

	
	 String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$";
     String nameRegex = "^[A-Za-z\\s]+$";
     String PassWordRegex="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
     String AadharRegex="^\\d{12}$";
     String phoneNumberRegex = "^[0-9]{10}$";
	
    @Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UserRepo userinforepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("Load By UserName:"+username);
		User userDetail1 = userinforepo.findByUserName(username); 
		Optional<User>  userDetail=userinforepo.findByUserId(userDetail1.getUserId()); 
		
        // Converting userDetail to UserDetails 
        return userDetail.map(CustomerDetailsService::new) 
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username)); 
	}
	                                                                              

	 public String addUser(User userInfo) { 
	     List<User>userdet=new ArrayList<>();
	     userdet.add(userInfo);
	     
		    
		   boolean result= userdet.stream().allMatch(userd->userd.getEmailId().matches(emailRegex)
		    		&& userd.getPassword().matches(PassWordRegex)
		    		&& userd.getUserName().matches(nameRegex)
		    		&& String.valueOf(userd.getAadharNumber()).matches(AadharRegex)
		    		&& String.valueOf(userd.getContactNumber()).matches(phoneNumberRegex));
		   
		 System.out.println("Result of a Boolean:"+result);
		  
		 if(result)
		 {
			 System.out.println("Hiiii");
			 System.out.println("pass:"+userInfo.getPassword());
			 userInfo.setPassword(this.encoder.encode(userInfo.getPassword())); 
	         userinforepo.save(userInfo); 
			 System.out.println("Encoding:"+this.encoder.encode(userInfo.getPassword()));
	         return "User Added Successfully"; 
		 }
		 else
		 {
			 return "Invalid Credentials";
		 }
	    }

}
