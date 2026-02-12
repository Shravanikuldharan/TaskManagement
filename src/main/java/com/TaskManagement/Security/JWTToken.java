package com.TaskManagement.Security;

import java.security.Key;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.TaskManagement.Entity.UserAuth;
import com.TaskManagement.Enum.Permission;
import com.TaskManagement.Security.PermissionConfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTToken {
	
	private final Key key;
	private final long expairToken=1000L*60*60*24;
	
	public JWTToken() {
		
		String secret= System.getenv("JWT_SECRET");
		if(secret==null ||secret.isEmpty() ) {
			//secret="ReplaceThisWithSomeSecretKey";
			secret = "ReplaceThisWithSomeVeryStrongJWTSecretKey12345";
		}
		key=Keys.hmacShaKeyFor(secret.getBytes());
	}
	
	public String generateToken(UserAuth user) {
		
		Set<Permission> permissions = 
				PermissionConfig.getRoleBasedPermission().getOrDefault(user.getRole(), Set.of());
		
		Date now =new Date();
		Date expire= new Date(now.getTime()+expairToken);
		
		return Jwts.builder()
				.setSubject(user.getUserOfficialEmail())
				.claim("role", user.getRole().name())
				.claim("permission", permissions.stream().map(Enum::name).collect(Collectors.toList()))
				.setIssuedAt(now)
				.setExpiration(expire)
				.signWith(key,SignatureAlgorithm.HS256)
				.compact();
	}	

	public boolean validToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			return true;
			
		} catch (JwtException e) {
			return false;
			
		}
		
	}
	
	public Claims getClaim(String token) {
		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
	}
	public String getUserEmail(String token) {
		return getClaim(token).getSubject();
	}
	
	public String extractToken(String header) {
		if(header!=null && header.startsWith("Bearer ") ) {
			return header.substring(7);
		}
		return null;
	}	
}