package com.hotel.booking.Filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.hotel.booking.Service.CustomerInfoService;
import com.hotel.booking.Service.TokenBlacklist;
import com.hotel.booking.Service.jwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class jwtFilter extends OncePerRequestFilter{
	
	@Autowired
	private jwtService jwtService;

    @Autowired
    private CustomerInfoService userDetailsService; 
    
	@Autowired
    private TokenBlacklist tokenBlacklist;

@Override
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
		throws ServletException, IOException {
	
	 String authHeader = request.getHeader("Authorization"); 

		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
	
        String token = null; 
        String username = null; 
        if (authHeader != null && authHeader.startsWith("Bearer ")) { 
       
            token = authHeader.substring(7);
        	
            username = jwtService.extractUsername(token); 
            System.out.println("Do Internal Filter:"+username);
        } 
        else {
            
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null && !tokenBlacklist.isBlacklisted(token)) { 
            
            UserDetails userDetails = userDetailsService.loadUserByUsername(username); 
            if (jwtService.validateToken(token, userDetails)) { 
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()); 
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); 
                SecurityContextHolder.getContext().setAuthentication(authToken); 
            } 
        } 
       
        filterChain.doFilter(request, response); 
	
}



}
