package com.hotel.booking.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.hotel.booking.Model.User;

@Service
public class EmailServiceImpl implements EmailService {
	     @Autowired
	     public JavaMailSender javaMailSender;
         
	     @Override
	     public void sendPasswordResetEmail(User user,String token) {
	    	try
	    	{
	        String recipientEmail = user.getEmailId();
	        System.out.println("Receipeint EmailId:"+recipientEmail);
	        
	        String subject = "Password Reset Request";
	        String resetLink = "http://your-app-url/reset-password?token="+token; // Replace with your actual reset password link

	        String emailContent = "Hello " + user.getUserName() + ",\n\n"
	                + "You recently requested to reset your password for your account. Click the link below to reset it:\n\n"
	                + resetLink + "\n\n"
	                + "If you didn’t request this, please ignore this email.\n\n"
	                + "Thanks,\nYour App Team";

	        sendEmail(recipientEmail, subject, emailContent);
	    }
         catch(Exception e) {
             e.printStackTrace();
             System.out.println("Failed to send password reset email to user: " + user.getEmailId());
         }
	     }
	    private void sendEmail(String to, String subject, String body) {
	    	try
	    	{
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(to);
	        message.setSubject(subject);
	        message.setText(body);
	        javaMailSender.send(message);
	    	}
	    	catch (MailException e) {
	            e.printStackTrace();
	            System.out.println("Failed to send email to: " + to);
	        }
	    }

}
