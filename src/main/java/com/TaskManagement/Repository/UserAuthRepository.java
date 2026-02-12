package com.TaskManagement.Repository;

import java.util.Optional;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import com.TaskManagement.Entity.UserAuth;

@Repository
//@Controller
//@SpringBootApplication
public interface UserAuthRepository extends JpaRepository<UserAuth, Long> {
	Optional<UserAuth>findById(Long id);
	Optional<UserAuth>findByUserOfficialEmail(String userOfficialEmail);
	Optional<UserAuth>findByResetToken(String resetToken);
}