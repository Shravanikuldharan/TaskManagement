package com.TaskManagement.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.TaskManagement.DTO.UserProfileUpdateDTO;
import com.TaskManagement.Service.UserProfileUpdateService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/update_profile")
@RequiredArgsConstructor
public class UserProfileUpdateController {
	
	@Autowired
	private UserProfileUpdateService userProfileUpdateService;
	
	@PutMapping("/updateProfile/{email}")
	public ResponseEntity<UserProfileUpdateDTO> updateProfile(@RequestBody UserProfileUpdateDTO userUpdate) {
		return ResponseEntity.ok(userProfileUpdateService.updateUserProfile(userUpdate));
	}
	
	@GetMapping("/allProfile")
	public ResponseEntity<List<UserProfileUpdateDTO>> getAllProfile() {
		return ResponseEntity.ok(userProfileUpdateService.getAllProfile());
	}
	
	@GetMapping("/{email}")
	public ResponseEntity<UserProfileUpdateDTO> getProfileByUserEmail(@PathVariable String userOfficialEmail) {
		return ResponseEntity.ok(userProfileUpdateService.getProfileByUserEmail(userOfficialEmail));
	}
}