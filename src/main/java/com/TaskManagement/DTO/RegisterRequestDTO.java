package com.TaskManagement.DTO;

import com.TaskManagement.Enum.Role;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequestDTO {
	
	public String userName;
	public String userOfficialEmail;
	public String password;
	public Role role;
}