package com.TaskManagement.Entity;

import java.time.LocalDateTime;
import com.TaskManagement.Enum.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_auth")

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Getter
//@Setter
@Builder
public class UserAuth {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String userName;
	
	@Column(nullable=false,unique=true)
	private String userOfficialEmail;
	
	@Column(nullable=false)
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Role role;

//	private LocalDateTime createdAt = LocalDateTime.now();
	
	private String resetToken;
	private LocalDateTime resetTokenExpire;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserOfficialEmail() {
		return userOfficialEmail;
	}

	public void setUserOfficialEmail(String userOfficialEmail) {
		this.userOfficialEmail = userOfficialEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

//	public LocalDateTime getCreatedAt() {
//		return createdAt;
//	}
//
//	public void setCreatedAt(LocalDateTime createdAt) {
//		this.createdAt = createdAt;
//	}

	public String getResetToken() {
		return resetToken;
	}

	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}

	public LocalDateTime getResetTokenExpire() {
		return resetTokenExpire;
	}

	public void setResetTokenExpire(LocalDateTime resetTokenExpire) {
		this.resetTokenExpire = resetTokenExpire;
	}
}
