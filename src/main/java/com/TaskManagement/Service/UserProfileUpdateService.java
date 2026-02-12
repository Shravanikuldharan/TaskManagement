package com.TaskManagement.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TaskManagement.DTO.UserProfileUpdateDTO;
import com.TaskManagement.Entity.UserProfileUpdate;
import com.TaskManagement.Repository.UserProfileUpdateRepository;

@Service
public class UserProfileUpdateService {
	
	@Autowired
	private UserProfileUpdateRepository userProfileUpdateRepo;
	
	public UserProfileUpdateDTO updateUserProfile(UserProfileUpdateDTO updateProfile) {
		
		if(userProfileUpdateRepo.findByUserOfficialEmail(updateProfile.userOfficialEmail).isPresent()) {
			throw new RuntimeException("Profile already exists" + updateProfile.userOfficialEmail);
		}
		UserProfileUpdate profileUpdate = new UserProfileUpdate();
		
		profileUpdate.setUserName(updateProfile.userName);
		profileUpdate.setUserOfficialEmail(updateProfile.userOfficialEmail);
		profileUpdate.setDepartment(updateProfile.department);
		profileUpdate.setDesignation(updateProfile.designation);
		profileUpdate.setUserOfficialEmail(updateProfile.userOfficialEmail);
		profileUpdate.setOrganizationName(updateProfile.organizationName);
		profileUpdate.setActive(true);
		
		userProfileUpdateRepo.save(profileUpdate);
		return toDTO(profileUpdate);
	}
	
	public List<UserProfileUpdateDTO>getAllProfile(){
		return userProfileUpdateRepo.findAll().stream().map(this::toDTO).collect(Collectors.toList());
	}
	
	public UserProfileUpdateDTO getProfileByUserEmail(String userOfficialEmail) {
		UserProfileUpdate userUpdate= userProfileUpdateRepo.findByUserOfficialEmail(userOfficialEmail).orElseThrow(()-> new RuntimeException("User not found"));
		return toDTO(userUpdate);
	}
	
	
	private UserProfileUpdateDTO toDTO(UserProfileUpdate profile) {
		
		UserProfileUpdateDTO dto = new UserProfileUpdateDTO();
		dto.setUserName(profile.getUserName());
		dto.setUserOfficialEmail(profile.getUserOfficialEmail());
		dto.setDepartment(profile.getDepartment());
		dto.setDesignation(profile.getDesignation());
		dto.setOrganizationName(profile.getOrganizationName());
		dto.setActive(profile.isActive());
		return dto;	
	}
		
}
