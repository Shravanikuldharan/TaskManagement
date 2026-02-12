package com.TaskManagement.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.TaskManagement.DTO.AuthResponseDTO;
import com.TaskManagement.DTO.ForgotPasswordRequestDTO;
import com.TaskManagement.DTO.LoginRequestDTO;
import com.TaskManagement.DTO.RegisterRequestDTO;
import com.TaskManagement.DTO.ResetPasswordRequestDTO;
import com.TaskManagement.Email.EmailService;
import com.TaskManagement.Entity.UserAuth;
import com.TaskManagement.Enum.Role;
import com.TaskManagement.Repository.UserAuthRepository;
import com.TaskManagement.Security.JWTToken;
import com.TaskManagement.Security.TokenBlockListService;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class UserAuthService {
	
	@Autowired
	private UserAuthRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JWTToken jwt;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private TokenBlockListService tokenBlock;
	
	public String register(RegisterRequestDTO register) {

	    Optional<UserAuth> existingEmail =
	            userRepo.findByUserOfficialEmail(register.userOfficialEmail);

	    if (existingEmail.isPresent()) {
	        throw new RuntimeException("User already exist.");
	    }

	    UserAuth user = new UserAuth();
	    user.setUserName(register.userName);
	    user.setUserOfficialEmail(register.userOfficialEmail);
	    user.setPassword(passwordEncoder.encode(register.password));
	    user.setRole(Role.DEVELOPER);   

	    userRepo.save(user);

	    return "Register successful";
	}

	
	public AuthResponseDTO login(LoginRequestDTO login) {
	    
	    UserAuth user = userRepo.findByUserOfficialEmail(login.userOfficialEmail)
	            .orElseThrow(() -> new RuntimeException("User not found."));

	    if (!passwordEncoder.matches(login.password, user.getPassword())) {
	        throw new RuntimeException("Invalid credential");
	    }

	    String token = jwt.generateToken(user);
	    return new AuthResponseDTO(token, "LoggedIn successful");
	}

	public void forgotPassword(ForgotPasswordRequestDTO forgotPassword) {

	    UserAuth user = userRepo.findByUserOfficialEmail(forgotPassword.userOfficialEmail)
	            .orElseThrow(() -> new RuntimeException("User not found."));

	    String token = UUID.randomUUID().toString();

	    user.setResetToken(token);
	    user.setResetTokenExpire(LocalDateTime.now().plusMinutes(10)); 

	    userRepo.save(user);

	    String resetLink = "http://localhost:5050/api/auth/resetPassword?token=" + token;

	    emailService.passwordMail(user.getUserOfficialEmail(), resetLink);

	    System.out.println("Reset token: " + token);
	}
	
	public void resetPassword(ResetPasswordRequestDTO resetPassword) {

	    UserAuth user = userRepo.findByResetToken(resetPassword.token)
	            .orElseThrow(() -> new RuntimeException("Invalid token"));

	    if (user.getResetTokenExpire() == null ||
	        user.getResetTokenExpire().isBefore(LocalDateTime.now())) {
	        throw new RuntimeException("Token expired");
	    }

	    user.setPassword(passwordEncoder.encode(resetPassword.newPassword));
	    user.setResetToken(null);
	    user.setResetTokenExpire(null);

	    userRepo.save(user);
	}

	
	public String loggedOut(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		String token = jwt.extractToken(header);
		
		if(token!=null) {
			tokenBlock.blockToken(token);
		}
		return "Logged Out successfully.";
		
	}
}