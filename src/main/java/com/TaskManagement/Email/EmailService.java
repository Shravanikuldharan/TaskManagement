package com.TaskManagement.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender javamailSender;
	
	public void passwordMail(String to, String resetLink) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setTo(to);
		message.setSubject("Reset your password");
		message.setText("Click the link below to reset your password:\n" + resetLink + "\n\nThis link will expire in 10 minutes.");
		
		javamailSender.send(message);
	}
	
}