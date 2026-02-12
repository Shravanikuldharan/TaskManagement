package com.TaskManagement.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TaskManagement.DTO.AuthResponseDTO;
import com.TaskManagement.DTO.ForgotPasswordRequestDTO;
import com.TaskManagement.DTO.LoginRequestDTO;
import com.TaskManagement.DTO.RegisterRequestDTO;
import com.TaskManagement.DTO.ResetPasswordRequestDTO;
import com.TaskManagement.Service.UserAuthService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserAuthController {
	
	@Autowired
	private UserAuthService userAuth;
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegisterRequestDTO register) {
		return ResponseEntity.ok(userAuth.register(register));		
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequestDTO login) {
		return ResponseEntity.ok(userAuth.login(login));
	}
	
	@PostMapping("/logOut")
	public ResponseEntity<String> loggedOut(HttpServletRequest request) {
		return ResponseEntity.ok(userAuth.loggedOut(request));
	}
	
	@PostMapping("/forgotPassword")
	public ResponseEntity<String> forgotPassword(
	        @RequestBody ForgotPasswordRequestDTO request) {

	    userAuth.forgotPassword(request);
	    return ResponseEntity.ok("Reset link sent successfully");
	}
	
	@PostMapping("/resetPassword")
	public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequestDTO resetPassword) {
		userAuth.resetPassword(resetPassword);
		return ResponseEntity.ok("Password reset successfully");
	}
}