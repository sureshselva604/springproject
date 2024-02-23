package com.hotel.booking.Pojo;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPojo {
	
	 public String getUserName() {
			return userName;
		}
		public void setUserName(String username) {
			this.userName = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		private String userName; 
		 private String password;
		 private String roles;
		public String getRoles() {
			return roles;
		}
		public void setRoles(String roles) {
			this.roles = roles;
		}
		

	

}
