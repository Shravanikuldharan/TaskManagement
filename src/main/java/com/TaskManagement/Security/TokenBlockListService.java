package com.TaskManagement.Security;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class TokenBlockListService {
	private final Set<String>killedToken = ConcurrentHashMap.newKeySet();
	
	public void blockToken(String token) {
		killedToken.add(	token);
	}
	
	public boolean isblockToken(String token) {
		return killedToken.contains(token);
	}
}