package com.hotel.booking.Service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;


public interface TokenBlacklist {
	
	void addToBlacklist(String token);
    boolean isBlacklisted(String token);
    
    @Service
    public class InMemoryTokenBlacklist implements TokenBlacklist {
        private Set<String> blacklist = new HashSet<>();

        @Override
        public void addToBlacklist(String token) {
        	try
        	{
            blacklist.add(token);
        	}
        	catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public boolean isBlacklisted(String token) {
        	try
        	{
            return blacklist.contains(token);
        	}
        	catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        	
        }

}

}
